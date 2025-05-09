package com.ahold.technl.sandbox.controller;

import com.ahold.technl.sandbox.dto.DeliveryRecord;
import com.ahold.technl.sandbox.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DeliveryControllerTest {

    @MockBean
    DeliveryService deliveryService;


    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @ParameterizedTest
    @MethodSource("getInvalidStatusRequest")
    void testInvalidStatus(MockHttpServletRequestBuilder requestBuilder,DeliveryRecord deliveryRecord) throws Exception {
        String requestBody = objectMapper.writeValueAsString(deliveryRecord);
        mockMvc.perform(requestBuilder.contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest());

    }

    private static Stream<Arguments> getInvalidStatusRequest(){
       return Stream.of(Arguments.of(MockMvcRequestBuilders.post("/deliveries")
               ,new DeliveryRecord(null,"101","Street-abc",null,null,null)),
               Arguments.of(MockMvcRequestBuilders.post("/deliveries")
                       ,new DeliveryRecord(null,"101","Street-abc","test",null,null))
       );
    }



}