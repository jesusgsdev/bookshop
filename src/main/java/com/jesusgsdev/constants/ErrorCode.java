package com.jesusgsdev.constants;

public enum ErrorCode {

    BOOK_NOT_FOUND ("0001", "Book not found"),
    BOOK_ALREADY_EXISTS ("0002", "Book already exists"),

    CUSTOMER_NOT_FOUND ("1001", "Customer not found"),
    CUSTOMER_ALREADY_EXISTS ("1002", "Customer already exists"),

    PURCHASE_NOT_FOUND ("2001", "Purchase not found"),

    PAYMENT_METHOD_NOT_VALID("7001", "Payment method not allowed");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return code + " - " + message;
    }
}
