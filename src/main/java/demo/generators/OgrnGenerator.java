package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class OgrnGenerator {

    private OgrnGenerator() {
    }

    /**
     * Генерирует ОГРН юридического лица из 13 цифр
     * Структура:
     * 1: признак (обычно '1')
     * 2–3: код региона (01-99)
     * 4–5: номер инспекции (01-99)
     * 6–12: порядковый номер записи
     * 13: контрольная цифра
     *
     * @return - строковое значение сгенерированного ОГРН
     */
    public static String generateOgrn() {
        ThreadLocalRandom r = ThreadLocalRandom.current();

        StringBuilder s = new StringBuilder(13);
        s.append('1');

        s.append(String.format("%02d", r.nextInt(1, 100)));
        s.append(String.format("%02d", r.nextInt(1, 100)));
        s.append(String.format("%07d", r.nextInt(1, 10_000_000)));

        int k = controlDigit(s.substring(0, 12));
        s.append(k);

        return s.toString();
    }

    private static int controlDigit(String first12) {
        long n = Long.parseLong(first12);
        return (int) ((n % 11) % 10);
    }

}
