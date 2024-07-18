package org.study.swaggertest.entity;

public enum CountryEnum {
    UNITED_STATES("US", "United States", "Washington, D.C."),
    CANADA("CA", "Canada", "Ottawa"),
    UNITED_KINGDOM("GB", "United Kingdom", "London"),
    GERMANY("DE", "Germany", "Berlin"),
    FRANCE("FR", "France", "Paris"),
    ITALY("IT", "Italy", "Rome"),
    SPAIN("ES", "Spain", "Madrid"),
    JAPAN("JP", "Japan", "Tokyo"),
    SOUTH_KOREA("KR", "South Korea", "Seoul"),
    CHINA("CN", "China", "Beijing"),
    INDIA("IN", "India", "New Delhi"),
    AUSTRALIA("AU", "Australia", "Canberra"),
    BRAZIL("BR", "Brazil", "Brasilia"),
    MEXICO("MX", "Mexico", "Mexico City"),
    RUSSIA("RU", "Russia", "Moscow"),
    SOUTH_AFRICA("ZA", "South Africa", "Pretoria"),
    NETHERLANDS("NL", "Netherlands", "Amsterdam"),
    SWEDEN("SE", "Sweden", "Stockholm"),
    NORWAY("NO", "Norway", "Oslo"),
    FINLAND("FI", "Finland", "Helsinki"),
    DENMARK("DK", "Denmark", "Copenhagen"),
    SWITZERLAND("CH", "Switzerland", "Bern"),
    AUSTRIA("AT", "Austria", "Vienna"),
    BELGIUM("BE", "Belgium", "Brussels"),
    POLAND("PL", "Poland", "Warsaw"),
    TURKEY("TR", "Turkey", "Ankara"),
    ISRAEL("IL", "Israel", "Jerusalem"),
    SAUDI_ARABIA("SA", "Saudi Arabia", "Riyadh"),
    INDONESIA("ID", "Indonesia", "Jakarta"),
    NEW_ZEALAND("NZ", "New Zealand", "Wellington");

    private final String code;
    private final String name;
    private final String capital;

    CountryEnum(String code, String name, String capital) {
        this.code = code;
        this.name = name;
        this.capital = capital;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }
}
