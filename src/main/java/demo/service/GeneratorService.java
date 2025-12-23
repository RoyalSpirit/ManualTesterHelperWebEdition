package demo.service;

import demo.domain.IbanCountry;
import demo.domain.Language;
import demo.domain.OverdraftPrefixes;

public interface GeneratorService {

    // Меню генератора счетов
    String generateCorrespAccount(String bic, String currencyCode, String bankType, boolean nostroFlag);
    String generateOverdraftAccount(OverdraftPrefixes preset, String corrAccount20, String customPrefixIfNeeded);
    String generateIban(IbanCountry country);

    // Меню генератора реквизитов юрлица
    String generateInn();
    String generateKpp();
    String generateOgrn();
    String generateOkpo();

    // Меню генератора БИК, SWIFT, UUID
    String generateBic();
    String generateSwift(String countryCode);

    // Меню генератора чисел и строк
    String generateRandomNumbers(int length);
    String generateRandomSymbols(int length);
    String generateRandomText(int length, Language language);

    // Меню СНИЛС
    String generateTestSnils();
}
