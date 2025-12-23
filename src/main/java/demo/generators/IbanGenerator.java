package demo.generators;

import demo.domain.IbanCountry;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class IbanGenerator {

    private static final SecureRandom RND = new SecureRandom();
    private static final char[] ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] ALNUM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private IbanGenerator() {
    }

    public static String generateIban(IbanCountry country) {
        String template = country.getBbanTemplate();
        if (template == null || template.isEmpty()) {
            throw new IllegalArgumentException("BBAN template not defined for " + country);
        }
        String bban = generateFromTemplate(template);
        String checksum = computeChecksum(country.getCode(), bban);
        return country.getCode() + checksum + bban;
    }

    private static String generateFromTemplate(String template) {
        StringBuilder sb = new StringBuilder(template.length());
        for (char t : template.toCharArray()) {
            switch (t) {
                case 'A' -> sb.append(ALPHA[RND.nextInt(ALPHA.length)]);
                case 'N' -> sb.append((char) ('0' + RND.nextInt(10)));
                case 'C' -> sb.append(ALNUM[RND.nextInt(ALNUM.length)]);
                default -> sb.append(t); // literal (if any)
            }
        }
        return sb.toString();
    }

    private static String computeChecksum(String cc, String bban) {
        String rearranged = bban + cc + "00";
        String numeric = toNumeric(rearranged);
        int mod = mod97(numeric);
        int check = 98 - mod;
        return (check < 10 ? "0" : "") + check;
    }

    private static String toNumeric(String input) {
        StringBuilder sb = new StringBuilder(input.length() * 2);
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                int val = Character.toUpperCase(ch) - 'A' + 10;
                sb.append(val);
            } else if (Character.isDigit(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    private static int mod97(String numeric) {
        BigInteger bi = new BigInteger(numeric);
        return bi.mod(BigInteger.valueOf(97)).intValue();
    }

}
