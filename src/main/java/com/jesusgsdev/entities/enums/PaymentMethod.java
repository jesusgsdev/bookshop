package com.jesusgsdev.entities.enums;

import java.util.Arrays;
import java.util.Optional;

public enum PaymentMethod {

    CASH            ("cash"),
    CREDIT_CARD     ("credit card"),
    PAYPAL          ("paypal"),
    CRYPTO_CURRENCY ("crypto currency");

    private String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static Optional<PaymentMethod> fromValue(final String value){
        return Arrays.stream(values()).filter(v -> v.toString().equalsIgnoreCase(value)).findFirst();
    }

    @Override
    public String toString() {
        return value;
    }
}
