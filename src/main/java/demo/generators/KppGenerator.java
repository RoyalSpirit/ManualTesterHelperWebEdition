package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class KppGenerator {

    private KppGenerator() {
    }

    /**
     * Генерирует КПП из 9 цифр
     * Структура:
     * 1–4: код ИФНС (0100-9999)
     * 5–6: причина постановки (01 или 02)
     * 7–9: порядковый номер (000-999)
     *
     * @return - строковое значение сгенерированного КПП
     */
    public static String generateKpp() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        int ifns = r.nextInt(100, 10_000); // всегда 4 цифры
        String part1 = String.format("%04d", ifns);
        String part2 = r.nextBoolean() ? "01" : "02";
        String part3 = String.format("%03d", r.nextInt(1000));
        return part1 + part2 + part3;
    }

}
