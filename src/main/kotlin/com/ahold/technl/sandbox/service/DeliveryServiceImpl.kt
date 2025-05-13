package com.ahold.technl.sandbox.service

import com.ahold.technl.sandbox.dto.*
import com.ahold.technl.sandbox.entity.Delivery
import com.ahold.technl.sandbox.exception.DeliveryException
import com.ahold.technl.sandbox.repo.DeliveryRepository
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient


@Service
class DeliveryServiceImpl(
    private val deliveryRepository: DeliveryRepository,
    private val restClient: RestClient
) : DeliveryService {

    override fun createDelivery(request: DeliveryRequest): DeliveryResponse {
        val delivery = request.toEntity()
        val savedDelivery =  deliveryRepository.save(delivery)
        return savedDelivery.toResponse()
    }

    override fun sendInvoice(deliveryIdRecord: DeliveryIdRecord): MutableList<DeliveryInvoiceRecord?>? {
        val deliveries: List<Delivery> = deliveryRepository.findAllById(deliveryIdRecord.deliveryIds.toUUIDList())

        val missingIds: List<String> = deliveryIdRecord.deliveryIds.stream()
            .filter { id ->
                deliveries.stream().noneMatch { delivery: Delivery -> delivery.id == id.toUUID() }
            }
            .toList()

        if (!missingIds.isEmpty()) {
            throw DeliveryException(
                HttpStatus.NOT_FOUND.value(),
                "The following delivery IDs were not found: $missingIds"
            )
        }
        return deliveries.parallelStream().map(this::invokeInvoiceApi).toList()
    }

    private fun invokeInvoiceApi(delivery: Delivery): DeliveryInvoiceRecord? {
        val request = InvoiceRequest(delivery.id.toString(), delivery.address)
        val response: ResponseEntity<InvoiceResponse> = restClient.post()
            .uri("/v1/invoices")
            .body(request)
            .retrieve()
            .onStatus({ obj: HttpStatusCode -> obj.isError }, { req, res ->
                val responseBody: String = String(res.getBody().readAllBytes())
                throw DeliveryException(res.getStatusCode().value(), responseBody)
            })
            .toEntity(InvoiceResponse::class.java)
        val invResp: InvoiceResponse? = response.getBody()
        return invResp?.id?.let { DeliveryInvoiceRecord(delivery.id.toString(), it) }
    }


}