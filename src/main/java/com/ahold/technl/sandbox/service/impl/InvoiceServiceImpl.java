package com.ahold.technl.sandbox.service.impl;

import com.ahold.technl.sandbox.dto.DeliveryInvoiceRecord;
import com.ahold.technl.sandbox.dto.InvoiceRequest;
import com.ahold.technl.sandbox.dto.InvoiceResponse;
import com.ahold.technl.sandbox.entity.Delivery;
import com.ahold.technl.sandbox.exception.DeliveryException;
import com.ahold.technl.sandbox.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@AllArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final RestClient restClient;

    public DeliveryInvoiceRecord invokeInvoiceApi(Delivery delivery){
        InvoiceRequest request = new InvoiceRequest(delivery.getId(), delivery.getAddress());
        ResponseEntity<InvoiceResponse> response = restClient.post()
                .uri("/v1/invoices")
                .body(request)
                .retrieve()
                .onStatus(HttpStatusCode::isError, (req, res) -> {
                    String responseBody = new String(res.getBody().readAllBytes());
                    throw new DeliveryException(res.getStatusCode().value(), responseBody);
                })
                .toEntity(InvoiceResponse.class);
        InvoiceResponse invResp = response.getBody();
        return new DeliveryInvoiceRecord(delivery.getId(),invResp.id());
    }
}
