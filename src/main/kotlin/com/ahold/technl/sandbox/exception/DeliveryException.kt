package com.ahold.technl.sandbox.exception

class DeliveryException : RuntimeException {
    val errorCode: Int
    val errorMsg: String

    constructor(errorCode: Int, errorMsg: String) : super(errorMsg) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }

    constructor(cause: Throwable?, errorCode: Int, errorMsg: String) : super(cause) {
        this.errorCode = errorCode
        this.errorMsg = errorMsg
    }
}