package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class SwiftGenerator {

    private SwiftGenerator() {
    }

    /**
     * Генерирует 8-символьный SWIFT (без кода филиала).
     *
     * @param countryCode — буквенный ISO-код страны (2 буквы), например "RU", "US", "DE".
     * @return - строковое значение SWIFT
     */
    public static String generateEightSwift(String countryCode) {
        String country = normalizeCountry(countryCode);
        String bank = randomLetters(4);
        String location = randomAlphaNum(2, true);
        return bank + country + location;
    }

    /**
     * Генерирует 11-символьный SWIFT (с кодом филиала).
     *
     * @param countryCode  — буквенный ISO-код страны (2 буквы), например "RU", "US", "DE".
     * @param randomBranch - флаг генерирования случайного филиала (в значении false будет использоваться код XXX)
     * @return - строковое значение SWIFT
     */
    public static String generateElevenSwift(String countryCode, boolean randomBranch) {
        String baseSwift = generateEightSwift(countryCode);
        String branch = randomBranch ? randomAlphaNum(3, true) : "XXX";
        return baseSwift + branch;
    }

    /**
     * Универсальный метод генерирующий 8 или 11 значный SWIFT
     * При генерировании 11 значного SWIFT будет использоваться код филиала XXX
     *
     * @param countryCode - буквенный ISO-код страны (2 буквы), например "RU", "US", "DE".
     * @param withBranch  - флаг генерирования 11 значного SWIFT
     * @return - строковое значение SWIFT
     */
    public static String generateSwift(String countryCode, boolean withBranch) {
        return withBranch ? generateElevenSwift(countryCode, false) : generateEightSwift(countryCode);
    }

    private static String normalizeCountry(String cc) {
        if (cc == null || !cc.toUpperCase().matches("^[A-Z]{2}$")) {
            throw new IllegalArgumentException("Код страны должен состоять из двух букв (например, RU, US, DE).");
        }
        return cc.toUpperCase();
    }

    private static String randomLetters(int n) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append((char) ('A' + r.nextInt(26)));
        }
        return sb.toString();
    }

    private static String randomAlphaNum(int n, boolean avoidZeros) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String noZeroAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
        String src = avoidZeros ? noZeroAlphabet : alphabet;

        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(src.charAt(r.nextInt(src.length())));
        }
        return sb.toString();
    }

}
