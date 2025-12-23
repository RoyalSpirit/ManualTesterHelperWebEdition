package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class OkpoGenerator {

    private OkpoGenerator() {
    }


    /**
     * Генерирует ОКПО юридического лица из 8 цифр (первые 7 - случайные, 8 цифра - контрольная)
     *
     * @return - строковое значение сгенерированного ОКПО
     */
    public static String generateOkpo() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder base = new StringBuilder(7);
        for (int i = 0; i < 7; i++) {
            base.append(r.nextInt(10));
        }
        int k = checksum(base.toString(), 7);
        return base.toString() + k;
    }


    /**
     * Генерирует ОКПО индивидуального предпринимателя из 10 цифр (первые 9 - случайные, 10 цифра - контрольная)
     *
     * @return - строковое значение сгенерированного ОКПО
     */
    public static String generateOkpoIp() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder base = new StringBuilder(9);
        for (int i = 0; i < 9; i++) {
            base.append(r.nextInt(10));
        }
        int k = checksum(base.toString(), 9);
        return base.toString() + k;
    }

    /**
     * Расчёт контрольной цифры по методике Росстата.
     * (Σ d[i] * (i % (n+1))) % 11; если 10 — пересчёт по модулю 10.
     */
    private static int checksum(String digits, int n) {
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            int d = digits.charAt(i) - '0';
            int w = (i % n) + 1;
            sum += d * w;
        }
        int mod = sum % 11;
        if (mod == 10) mod = sum % 10;
        if (mod == 10) mod = 0;
        return mod;
    }

}
