package entities;

public enum Airline {
    AZAL("J2"),
    TURKISH_AIRLINES("TK"),
    UKRAINE_INTERNATIONAL_AIRLINES("PS"),
    QATAR_AIRWAYS("QR"),
    FLYDUBAI("FZ");

    private final String iataCode;

    private Airline(String code) {
        this.iataCode = code;
    }

    public String getCode() {
        return iataCode;
    }
}
