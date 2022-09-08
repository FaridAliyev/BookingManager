package entities;

public enum Airport {
    BAKU("GYD"),
    KIEV("KBP"),
    ISTANBUL("IST"),
    ANKARA("ESB"),
    DUBAI("DXB");

    private final String code;

    private Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
