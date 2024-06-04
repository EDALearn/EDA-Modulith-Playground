package io.zenwave360.example.customers.events;

/** Enum for EventType. */
public enum EventType {

    CREATED("1"), UPDATED("1"), DELETED("1"),;

    private final String value;

    private EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
