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
        private final String description;

        public Address(String plz, String stadt, String strasse, String hausnummer) {
            this(plz, stadt, strasse, hausnummer, "Default address");
        }

        public Address(String plz, String stadt, String strasse, String hausnummer, String description) {
            this.plz = plz;
            this.stadt = stadt;
            this.strasse = strasse;
            this.hausnummer = hausnummer;
            this.description = description;
        }

        public String getPlzOrderStadt() {
            return plz + ", " + stadt + ", " + stadt;
        }

        public String getStrasse() { return strasse; }
        public String getHausnummer() { return hausnummer; }
        public String getDescription() { return description; }

        public static Address defaultAddress() {
            return new Address(DEFAULT_PLZ, DEFAULT_STADT, DEFAULT_STRASSE, DEFAULT_HAUSNUMMER, "Default Fuldabrück address");
        }

        public static Address berlinAddress() {
            return new Address("10115", "Berlin", "Alexanderplatz", "1", "Berlin city center");
        }

        public static Address hamburgAddress() {
            return new Address("20095", "Hamburg", "Jungfernstieg", "1", "Hamburg city center");
        }

        public static Address invalidAddress() {
            return new Address("99999", "NonExistentCity", "FakeStreet", "999", "Invalid address for testing");
        }

        public static Address emptyFieldsAddress() {
            return new Address("", "", "", "", "Empty fields for validation testing");
        }
    }

    // Test scenarios for availability check
    public static final Object[][] AVAILABILITY_SCENARIOS = {
        {"Valid Address - Fuldabrück", TestData.Address.defaultAddress(), true},
        {"Valid Address - Berlin", TestData.Address.berlinAddress(), true},
        {"Valid Address - Hamburg", TestData.Address.hamburgAddress(), true},
        {"Invalid Address", TestData.Address.invalidAddress(), false},
        {"Empty Fields", TestData.Address.emptyFieldsAddress(), false}
    };

    public static final Object[][] ADDRESS_VALIDATION_SCENARIOS = {
        { new Address("", DEFAULT_STADT, DEFAULT_STRASSE, DEFAULT_HAUSNUMMER, "Empty PLZ"), false},
        {new Address(DEFAULT_PLZ, "", DEFAULT_STRASSE, DEFAULT_HAUSNUMMER, "Empty Stadt"), false},
        {new Address(DEFAULT_PLZ, DEFAULT_STADT, "", DEFAULT_HAUSNUMMER, "Empty Strasse"), false},
        {new Address(DEFAULT_PLZ, DEFAULT_STADT, DEFAULT_STRASSE, "", "Empty Hausnummer"), false},
        {new Address("", "", "", "", "All fields empty"), false}
    };

    public static Object[][] invalidAddressData = {
            {" ", "Ahornweg", "1", false, "PLZ oder Wohnort ist ungültig."},
            {"34277, Fuldabrück, Fuldabrück", " ", "1", false, "Straße ist ungültig."},
            {"34277, Fuldabrück, Fuldabrück", "Ahornweg", "adf", false, "Die Hausnummer ist ungültig."}
    };

    public static Object[][] validAddressData = {
            {"34277, Fuldabrück, Fuldabrück", "Ahornweg", "1", false, "PLZ oder Wohnort ist ungültig."}
//            {"34277, Fuldabrück, Fuldabrück", "", "1", false, "Straße ist ungültig."},
//            {"34277, Fuldabrück, Fuldabrück", "Ahornweg", "", false, "Die Hausnummer ist ungültig."},
//            {"99999, NonExistentCity, NonExistentCity", "FakeStreet", "999", false, "Die Adresse konnte nicht gefunden werden."}
    };


}
