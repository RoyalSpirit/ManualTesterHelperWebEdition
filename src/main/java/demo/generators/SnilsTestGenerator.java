package demo.generators;

import java.security.SecureRandom;

public final class SnilsTestGenerator {

    private SnilsTestGenerator() {

    }

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Генерирует тестовый номер снилс
     * - первые 9 цифр — произвольные
     * - последние 2 цифры — контрольное число
     */
    public static String generateTestSnils() {
        String firstNine = generateFirstNine();
        int checksum = calcChecksum(firstNine);
        String checksumStr = String.format("%02d", checksum);
        return firstNine.substring(0, 3) + "-" + firstNine.substring(3, 6) + "-" + firstNine.substring(6, 9)
                + " " + checksumStr;
    }

    public static String generateFirstNine() {
        int first3 = 21 + RANDOM.nextInt(1000 - 21);
        int last6 = RANDOM.nextInt(1_000_000);
        return String.format("%03d%06d", first3, last6);
    }

    public static int calcChecksum(String digits9) {
        if (digits9 == null || !digits9.matches("\\d{9}")) {
            throw new IllegalArgumentException("Значение должно быть строкой из 9 цифр");
        }

        int S = 0;

        for (int i = 0; i < 9; i++) {
            int d = digits9.charAt(i) - '0';
            int weight = 9 - i;
            S += weight * d;
        }

        int k;
        if (S < 100) {
            k = S;
        } else if (S == 100) {
            k = 0;
        } else {
            k = S % 101;
            if (k == 100) {
                k = 0;
            }
        }
        return k;
    }

}
