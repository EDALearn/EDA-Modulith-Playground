package io.zenwave360.example.customers.model;

/** Enum for AddressType. */
public enum AddressType {

    HOME("1"), WORK("1"),;

    private final String value;

    private AddressType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
