package demo.service.impl;

import demo.domain.IbanCountry;
import demo.domain.Language;
import demo.domain.OverdraftPrefixes;
import demo.generators.*;
import demo.service.GeneratorService;
import org.springframework.stereotype.Service;

@Service
public class DefaultGeneratorService implements GeneratorService {

    // Счета
    @Override
    public String generateCorrespAccount(String bic, String currencyCode, String bankType, boolean nostroFlag) {
        return AccountGenerator.generateAccount(bic, currencyCode, bankType, nostroFlag);
    }

    @Override
    public String generateOverdraftAccount(OverdraftPrefixes preset, String corrAccount20, String customPrefixIfNeeded) {
        return OverdraftAccountsGenerator.generateOverdraftAccount(preset, corrAccount20, customPrefixIfNeeded);
    }

    @Override
    public String generateIban(IbanCountry country) {
        return IbanGenerator.generateIban(country);
    }

    // Реквизиты юрлица
    @Override
    public String generateInn() {
        return InnGenerator.generateInn();
    }

    @Override
    public String generateKpp() {
        return KppGenerator.generateKpp();
    }

    @Override
    public String generateOgrn() {
        return OgrnGenerator.generateOgrn();
    }

    @Override
    public String generateOkpo() {
        return OkpoGenerator.generateOkpo();
    }


    // БИК, SWIFT, UUID
    @Override
    public String generateBic() {
        return BicGenerator.generateBic();
    }

    @Override
    public String generateSwift(String countryCode) {
        return SwiftGenerator.generateEightSwift(countryCode);
    }


    // Числа и строки
    @Override
    public String generateRandomNumbers(int length) {
        return StringAndNumbersGenerator.randomNumberGenerator(length);
    }

    @Override
    public String generateRandomSymbols(int length) {
        return StringAndNumbersGenerator.randomSymbolsGenerator(length);
    }

    @Override
    public String generateRandomText(int length, Language language) {
        String lang = (language == Language.RUSSIAN) ? "Russian" : "English";
        return StringAndNumbersGenerator.randomStringGenerator(length, lang);
    }


    // СНИЛС
    @Override
    public String generateTestSnils() {
        return SnilsTestGenerator.generateTestSnils();
    }

}
