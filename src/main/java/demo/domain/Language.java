package demo.domain;

public enum Language {
    RUSSIAN("Русский"),
    ENGLISH("Английский");

    private final String language;

    Language(String language) {
        this.language = language;
    }

    public String language() {
        return language;
    }

    public String toString() {
        return language;
    }

}



