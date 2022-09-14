package entities;

public enum Airport {
    BAKU("GYD"),
    KIEV("KBP"),
    ISTANBUL("IST"),
    ANKARA("ESB"),
    FRANKFURT("FRA"),
    MILAN("MIL"),
    LONDON("LON"),
    ROMA("ROM"),
    AMSTERDAM("AMS"),
    LISBON("LIS"),
    MANCHESTER("MAN"),
    STOCKHOLM("STO"),
    BERN("BRN"),
    DUBAI("DXB");

    private final String code;

    private Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
