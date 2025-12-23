package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class BicGenerator {

    private BicGenerator() {
    }

    /**
     * Генерирует БИК из 9 цифр
     * Структура:
     * 1–2: всегда "04" для России
     * 3–4: код региона (01-99)
     * 5–6: номер РКЦ (00-99)
     * 7–9: код банка в РКЦ (001-999)
     *
     * @return - строковое значение сгенерированного БИК
     */
    public static String generateBic() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder bic = new StringBuilder(9);
        bic.append("04");

        int region = r.nextInt(1, 100);
        bic.append(String.format("%02d", region));

        int rkc = r.nextInt(100);
        bic.append(String.format("%02d", rkc));

        int bankCode;
        do {
            bankCode = r.nextInt(1000);
        } while (bankCode == 0);
        bic.append(String.format("%03d", bankCode));
        return bic.toString();
    }



}
