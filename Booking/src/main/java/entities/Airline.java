package entities;

public enum Airline {
    AZAL("J2"),
    TURKISH_AIRLINES("TK"),
    UKRAINE_INTERNATIONAL("PS"),
    QATAR_AIRWAYS("QR"),
    AIR_BERLIN("AB"),
    AIR_ITALY("IG"),
    BRITISH_AIRWAYS("BA"),
    AIR_HOLLAND("HD"),
    AIR_PORTUGAL("TP"),
    SWEDISH_AIRLINES("SH"),
    SWISS_AIRLINES("LX"),
    AIR_ARABIA("G9"),
    FLYDUBAI("FZ");

    private final String iataCode;

    private Airline(String code) {
        this.iataCode = code;
    }

    public String getCode() {
        return iataCode;
    }
}
