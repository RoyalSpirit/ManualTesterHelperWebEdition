package demo.generators;

import demo.domain.OverdraftPrefixes;

import java.util.Objects;

public final class OverdraftAccountsGenerator {


    private static final int ACCOUNT_LENGTH = 20;
    private static final int CONTROL_INDEX = 8;

    private OverdraftAccountsGenerator() {
    }

    /**
     * Формирует овердрафтный корреспондентский счёт на основе введенного корреспондентского, с возможностью задать
     * собственный префикс для будущего счета.
     * Структура не отличается от корреспондентского, кроме первых пяти цифр.
     * /**
     *
     * @param preset               - префикс овердрафтного счета (5 цифр)
     * @param corrAccount20        - корреспондентсткий счет (20 цифр)
     * @param customPrefixIfNeeded - необходимость ввода собственного префикса
     * @return
     */
    public static String generateOverdraftAccount(OverdraftPrefixes preset, String corrAccount20, String customPrefixIfNeeded) {
        Objects.requireNonNull(preset, "preset");

        String prefix;
        if (preset.getPrefix() != null) {
            prefix = preset.getPrefix();
        } else {
            if (customPrefixIfNeeded == null || customPrefixIfNeeded.isBlank()) {
                throw new IllegalArgumentException("Требуется ввести префикс");
            }
            prefix = customPrefixIfNeeded.replaceAll("\\s+", "");
        }
        return generateFromPrefix(corrAccount20, prefix);
    }

    public static String generateFromPrefix(String corrAccount20, String prefix) {
        Objects.requireNonNull(corrAccount20, "corrAccount20");
        Objects.requireNonNull(prefix, "prefix");

        String corr = corrAccount20.replaceAll("\\s+", "");
        if (!corr.matches("\\d{" + ACCOUNT_LENGTH + "}")) {
            throw new IllegalArgumentException("Корреспондентский счёт должен состоять из 20 цифр");
        }

        if (!prefix.matches("\\d+")) {
            throw new IllegalArgumentException("Префикс должен состоять только из цифр");
        }
        if (prefix.length() > CONTROL_INDEX) {
            throw new IllegalArgumentException(
                    "Префикс не может затрагивать контрольную цифру (максимум " + CONTROL_INDEX + " символов)");
        }

        char[] result = corr.toCharArray();
        for (int i = 0; i < prefix.length(); i++) {
            result[i] = prefix.charAt(i);
        }
        return new String(result);
    }

}