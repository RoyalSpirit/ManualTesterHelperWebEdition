package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class AccountGenerator {

    private AccountGenerator() {
    }

    /**
     * Генерирует корреспондентский счёт (из 20 цифр) на основе БИК и цифрового кода валюты (3 цифры, например "810").
     * Структура:
     * 1–3: "301"
     * 4–5: "09"  (тип счёта; 09 — открытые для резидентов, 10 - открытые в других Банках резидентах (Ностро),
     *            11 - открытые для нерезидентов, 14 - открытые в других Банках нерезидентах (Ностро))
     * 6–8: код валюты, напр. "840" (USD)
     * 9: контрольная цифра (рассчитывается при помощи метода calcControlDigit)
     * 10–17: произвольные 8 цифр
     * 18–20: последние 3 цифры БИК
     *
     * @param bic          - БИК
     * @param currencyCode - цифровой код валюты (например "810")
     * @param bankType     - тип банка-клиента (Резидент/Нерезидент)
     * @param nostroFlag   - флаг/признак счета Ностро (открытого на балансе в другом банке)
     * @return - строковое значение сгенерированного корреспондентского счета
     */
    public static String generateAccount(String bic, String currencyCode, String bankType, boolean nostroFlag) {
        if (bic == null || !bic.matches("\\d{9}")) {
            throw new IllegalArgumentException("БИК должен состоять из 9 цифр");
        }
        String bikTail = bic.substring(6);

        StringBuilder acc = new StringBuilder(20);
        acc.append("301");
        switch (bankType) {
            case "Резидент" -> {
                if (nostroFlag) {
                    acc.append("10");
                } else acc.append("09");
            }
            case "Нерезидент" -> {
                if (nostroFlag) {
                    acc.append("14");
                } else acc.append("11");
            }
        }
        acc.append(currencyCode);
        acc.append('0');
        acc.append(randDigits(8));
        acc.append(bikTail);

        char k = calcControlDigit(bic, acc.toString());
        acc.setCharAt(8, k);

        return acc.toString();
    }

    /**
     * Рассчитывает контрольную цифру
     *
     * @param bik                - БИК
     * @param accountWithZeroAt9 - счет состоящий из 20 цифр (где на 9 позиции временно передается 0 для последующего
     *                           подбора верной контрольнйо цифры)
     * @return символьное значение контрольной цифры
     */
    private static char calcControlDigit(String bik, String accountWithZeroAt9) {
        String base23 = bik.substring(6) + accountWithZeroAt9;
        final int controlIndex = 3 + 8;
        for (int x = 0; x <= 9; x++) {
            int sum = 0;
            for (int i = 0; i < 23; i++) {
                int d = (i == controlIndex) ? x : (base23.charAt(i) - '0');
                sum += (d * weightAt(i)) % 10;
            }
            if (sum % 10 == 0) return (char) ('0' + x);
        }
        throw new IllegalStateException("Не удалось подобрать контрольную цифру");
    }

    /**
     * Повторяющиеся веса: 7,1,3,7,1,3,…
     */
    private static int weightAt(int idx) {
        int m = idx % 3;
        return (m == 0) ? 7 : (m == 1) ? 1 : 3;
    }

    private static String randDigits(int n) {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        StringBuilder s = new StringBuilder(n);
        for (int i = 0; i < n; i++) s.append(r.nextInt(10));
        return s.toString();
    }

}
