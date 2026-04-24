package org.example.tests;

public class TestData {
    public static final String BASE_URL = "https://portal-proxy-pacman-03.pub.cl01.tmagic-dev.telekom.de/ausbau/checkout/availability-check";

    public static final String DEFAULT_PLZ = "34277";
    public static final String DEFAULT_STADT = "Fuldabrück";
    public static final String DEFAULT_STRASSE = "Ahornweg";
    public static final String DEFAULT_HAUSNUMMER = "1";

    public static final String PLZ_ORDER_STADT = DEFAULT_PLZ + ", " + DEFAULT_STADT + ", " + DEFAULT_STADT;

    public static class Address {
        private final String plz;
        private final String stadt;
        private final String strasse;
        private final String hausnummer;

        public Address(String plz, String stadt, String strasse, String hausnummer) {
            this.plz = plz;
            this.stadt = stadt;
            this.strasse = strasse;
            this.hausnummer = hausnummer;
        }

        public String getPlzOrderStadt() {
            return plz + ", " + stadt + ", " + stadt;
        }

        public String getStrasse() { return strasse; }
        public String getHausnummer() { return hausnummer; }

        public static Address defaultAddress() {
            return new Address(DEFAULT_PLZ, DEFAULT_STADT, DEFAULT_STRASSE, DEFAULT_HAUSNUMMER);
        }
    }
}
