package demo.domain;

public enum OverdraftPrefixes {

    SBERBANK("Сбер", "32101"),
    CUSTOM("Ввести префикс вручную", null);

    private final String title;
    private final String prefix;

    OverdraftPrefixes(String title, String prefix) {
        this.title = title;
        this.prefix = prefix;
    }

    public String getTitle()  { return title; }
    public String getPrefix() { return prefix; }

    @Override
    public String toString() {
        return title;
    }

}
