package com.jesusgsdev.constants;

import java.util.Arrays;
import java.util.Optional;

public enum Currency {

    GBP ("GBP"),
    USD ("USD"),
    EUR ("EUR");

    private String value;

    Currency(String value) {
        this.value = value;
    }

    public static Optional<Currency> fromValue(final String value){
        return Arrays.stream(values()).filter(v -> v.toString().equalsIgnoreCase(value)).findFirst();
    }

    @Override
    public String toString() {
        return value;
    }
}
