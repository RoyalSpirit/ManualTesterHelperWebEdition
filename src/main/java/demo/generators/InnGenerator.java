package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class InnGenerator {

    private InnGenerator() {
    }

    // Веса для ИНН (последняя — контрольная)
    private static final int[] W10 = {2, 4, 10, 3, 5, 9, 4, 6, 8};

    /**
     * Генерирует ИНН юридического лица из 10 цифр
     * Структура:
     * 1-2: регион (01-99)
     * 3-10: 7 произвольных цифр (включая последнюю контрольную)
     *
     * @return - строковое значение сгенерированного ИНН
     */
    public static String generateInn() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder base = new StringBuilder(10);
        int region = r.nextInt(1, 100);
        base.append(String.format("%02d", region));
        for (int i = 0; i < 7; i++) base.append(r.nextInt(10));
        int k10 = checksum(base.toString(), W10);
        base.append(k10);
        return base.toString();
    }

    /**
     * Подсчёт контрольной цифры: (Σ d[i]*w[i]) % 11, 10 -> 0.
     */
    private static int checksum(String digits, int[] weights) {
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += (digits.charAt(i) - '0') * weights[i];
        }
        int mod = sum % 11;
        return (mod == 10) ? 0 : mod;
    }

}
