package demo.domain;

public enum IbanCountry {
    DE("Germany", 22, "NNNNNNNNNNNNNNNNNN"),
    NL("Netherlands", 18, "AAAANNNNNNNNNN"),
    GB("United Kingdom", 22, "AAAANNNNNNNNNNNNNN"),
    FR("France", 27, "NNNNNAAAAANNNNNNNNNNNN"),
    IT("Italy", 27, "ANNNNNNNNNNNNNNNNNNNNNNNN"),
    ES("Spain", 24, "NNNNNNNNNNNNNNNNNNNNNN"),
    BE("Belgium", 16, "NNNNNNNNNNNNNN"),
    CH("Switzerland", 21, "NNNNNNNNNNNNNNNNN"),
    PL("Poland", 28, "NNNNNNNNNNNNNNNNNNNNNNNNNN"),
    SE("Sweden", 24, "NNNNNNNNNNNNNNNNNNNNNNNN"),
    AT("Austria", 20, "NNNNNNNNNNNNNNNN"),
    DK("Denmark", 18, "NNNNNNNNNNNNNNN"),
    FI("Finland", 18, "NNNNNNNNNNNNNNNN"),
    NO("Norway", 15, "NNNNNNNNNNNNN"),
    PT("Portugal", 25, "NNNNNNNNNNNNNNNNNNNNNNN"),
    CZ("Czech Republic", 24, "NNNNNNNNNNNNNNNNNNNNNN"),
    SK("Slovakia", 24, "NNNNNNNNNNNNNNNNNNNNNN"),
    GR("Greece", 27, "NNNNNNNNNNNNNNNNNNNNNNNN"),
    RO("Romania", 24, "AAAANNNNNNNNNNNNNNNNNNN"),
    HR("Croatia", 21, "NNNNNNNNNNNNNNNNNNN");

    private final String fullName;
    private final int ibanLength;
    private final String bbanTemplate;

    IbanCountry(String fullName, int ibanLength, String bbanTemplate) {
        this.fullName = fullName;
        this.ibanLength = ibanLength;
        this.bbanTemplate = bbanTemplate;
    }

    public String getCode() {
        return name();
    }

    public String getBbanTemplate() {
        return bbanTemplate;
    }

    public String toString() {
        return fullName;
    }
}
