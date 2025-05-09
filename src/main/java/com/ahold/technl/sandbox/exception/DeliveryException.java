package com.ahold.technl.sandbox.exception;

import lombok.Data;

@Data
public class DeliveryException extends RuntimeException{

    private int errorCode;
    private String errorMsg;

    public DeliveryException(int errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DeliveryException(Throwable cause, int errorCode, String errorMsg) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
