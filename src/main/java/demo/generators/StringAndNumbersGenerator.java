package demo.generators;

import java.util.concurrent.ThreadLocalRandom;

public final class StringAndNumbersGenerator {

    public static final String RUSSIANLOWERCASELETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    public static final String ENGLISHLOWERCASELETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:'\",.<>?/`~№§±©®™€£¥₽";

    StringAndNumbersGenerator() {
    }

    /**
     * Генерирует случайное число определенной длины (без ведущего нуля)
     *
     * @param length - длина будущего значения (количество цифр)
     * @return - строковое значие сгенерированного значения
     */
    public static String randomNumberGenerator(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom rnd = ThreadLocalRandom.current();

        sb.append(rnd.nextInt(1, 10));
        for (int i = 1; i < length; i++) {
            sb.append(rnd.nextInt(0, 10));
        }
        return sb.toString();
    }

    /**
     * Генерирует случайный набор букв определенной длины
     *
     * @param length   - длина будущего значения (количество букв)
     * @param language - язык (допустимые значения: Russian или English)
     * @return - строковое значие сгенерированного значения
     */
    public static String randomStringGenerator(int length, String language) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            switch (language) {
                case "Russian" ->
                        sb.append(RUSSIANLOWERCASELETTERS.charAt(random.nextInt(0, RUSSIANLOWERCASELETTERS.length())));
                case "English" ->
                        sb.append(ENGLISHLOWERCASELETTERS.charAt(random.nextInt(0, ENGLISHLOWERCASELETTERS.length())));
            }
        }
        return sb.toString();
    }

    /**
     * Генерирует случайный набор символов определенной длины
     *
     * @param length длина будущего значения (количество спецсимволов)
     * @return - строковое значие сгенерированного значения
     */
    public static String randomSymbolsGenerator(int length) {
        StringBuilder sb = new StringBuilder(length);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < length; i++) {
            sb.append(SYMBOLS.charAt(random.nextInt(0, SYMBOLS.length())));
        }
        return sb.toString();
    }

}
