package demo.domain;

public enum Currency {
    RUR_810("810", "RUR"),
    USD_840("840", "USD"),
    EUR_978("978", "EUR"),
    CNY_156("156", "CNY"),
    KZT_398("398", "KZT"),
    INR_356("356", "INR");

    private final String code;
    private final String currName;

    Currency(String code, String currName) {
        this.code = code;
        this.currName = currName;
    }

    public String code() {
        return code;
    }

    public String toString() {
        return code + " (" + currName + ")";
    }

}



