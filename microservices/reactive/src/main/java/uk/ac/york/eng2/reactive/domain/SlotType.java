package uk.ac.york.eng2.reactive.domain;

public enum SlotType {
    TIMESTAMP("timestamp"),
    TEXT("text"),
    DOUBLE("double"),
    LONG("long");

    private final String dbValue;

    SlotType(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }
}
