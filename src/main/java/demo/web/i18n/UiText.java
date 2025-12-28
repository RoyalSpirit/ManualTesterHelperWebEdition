package demo.web.i18n;

public final class UiText {
    private UiText() {

    }

    // Application
    public static final String APP_NAME = "ManualTesterHelper";
    public static final String APP_EDITION = "Web Edition";
    public static final String APP_VERSION = "v.0.3";


    // Сайдбар
    public static final String MENU_ACCOUNTS = "Генератор счетов";
    public static final String MENU_LEGAL = "Генератор реквизитов юрлица";
    public static final String MENU_CODES = "Генераторы БИК, SWIFT, UUID";
    public static final String MENU_STRINGS = "Генераторы чисел и строк";
    public static final String MENU_SNILS = "СНИЛС";



    // Генератор счетов
    public static final String CARD_CORR_TITLE = "Генератор корреспондентских счетов";
    public static final String CARD_OVERDRAFT_TITLE = "Генератор овердрафтных счетов";
    public static final String CARD_IBAN_TITLE = "Генератор счетов IBAN";
    // Генератор корреспондентских счетов
    public static final String BIC_FIELD_TITLE = "БИК (9 цифр)";
    public static final String CURRENCY_COMBOBOX_TITLE = "Валюта";
    public static final String CLIENT_TYPE_COMBOBOX_TITLE = "Тип клиента";
    public static final String BANK_TYPE_RESIDENT = "Резидент";
    public static final String BANK_TYPE_NON_RESIDENT = "Нерезидент";
    public static final String NOSTRO_CHECKBOX = "Ностро";
    public static final String BTN_GENERATE_CORRESP_ACCOUNT = "Сгенерировать счёт";
    public static final String EMPTY_BIC_ERROR_TEXT = "БИК не может быть пустым";
    public static final String NINE_DIGITS_BIC_ERROR_TEXT = "БИК должен состоять из 9 цифр";
    // Генератор овердрафтных счетов
    public static final String INPUT_PREFIX_FIELD_TITLE = "Введите префикс";
    public static final String SELECT_PREFIX_FIELD_TITLE = "Выберите префикс";
    public static final String INPUT_CORRESP_ACC_FIELD_TITLE = "Введите коррсчет (20 цифр)";
    public static final String BTN_GENERATE_OVERDRAFT_ACCOUNT = "Сгенерировать счёт";
    public static final String EMPTY_CORRESP_ACC_ERROR_TEXT = "Поле коррсчёта не может быть пустым";
    public static final String TWENTY_DIGITS_CORRESP_ACC_ERROR_TEXT = "Коррсчёт должен состоять из 20 цифр";
    public static final String EMPTY_PREFIX_ERROR_TEXT = "Поле префикса не может быть пустым";
    // Генератор счетов IBAN
    public static final String SELECT_COUNTRY_FIELD_TITLE = "Выберите страну";
    public static final String BTN_GENERATE_IBAN_ACCOUNT = "Сгенерировать счёт";


    // Генератор реквизитов юрлица
    public static final String CARD_LEGAL_TITLE = "Генератор реквизитов юрлица";
    public static final String BTN_GENERATE_ALL = "Сгенерировать всё сразу";
    public static final String BTN_GENERATE_INN = "Сгенерировать ИНН";
    public static final String BTN_GENERATE_KPP = "Сгенерировать КПП";
    public static final String BTN_GENERATE_OGRN = "Сгенерировать ОГРН";
    public static final String BTN_GENERATE_OKPO = "Сгенерировать ОКПО";


    // Генераторы БИК, SWIFT, UUID
    public static final String CARD_BIC_TITLE = "Генератор БИК";
    public static final String CARD_SWIFT_TITLE = "Генератор SWIFT";
    public static final String CARD_UUID_TITLE = "Генератор UUID";
    // Генератор БИК
    public static final String INPUT_COUNTRY_CODE_FIELD_TITLE = "Код страны (RU)";
    public static final String BTN_GENERATE_BIC = "Сгенерировать БИК";
    // Генератор SWIFT
    public static final String BTN_GENERATE_SWIFT = "Сгенерировать SWIFT";
    public static final String EMPTY_COUNTRY_CODE_ERROR_TEXT = "Код страны не может быть пустым";
    public static final String TWO_DIGITS_COUNTRY_CODE_ERROR_TEXT = "Код должен состоять из 2 цифр";
    // Генератор UUID
    public static final String GENERATE_WITHOUT_DASHES_CHECKBOX_TITLE = "Сгенерировать без дефисов";
    public static final String BTN_GENERATE_UUID = "Сгенерировать UUID";


    // Генераторы чисел и строк
    public static final String CARD_DIGITS_TITLE = "Генератор случайных чисел";
    public static final String CARD_STRINGS_TITLE = "Генератор текста";
    public static final String CARD_SYMBOLS_TITLE = "Генератор спецсимволов";
    public static final String FIELD_LENGTH = "Введите длину строки";
    public static final String FIELD_LANGUAGE = "Выберите язык";
    public static final String BTN_GENERATE_TEXT = "Сгенерировать текст";
    public static final String BTN_GENERATE_DIGITS = "Сгенерировать число";
    public static final String BTN_GENERATE_SYMBOLS = "Сгенерировать спецсимволы";
    public static final String EMPTY_STRUNG_ERROR_TEXT = "Длина строки не может быть пустой";
    public static final String CANT_BE_ZERO_ERROR_TEXT = "Строка не может начинаться с 0";


    // СНИЛС
    public static final String CARD_SNILS_TITLE = "Генератор тестовых значений СНИЛС";
    public static final String BTN_GENERATE_SNILS = "Сгенерировать СНИЛС";


    // Общие элементы для всех страниц
    public static final String TOOLTIP_COPY = "Копировать в буфер обмена";
    public static final String TOAST_COPIED = "Скопировано";
    public static final String SYNTHETIC_WARNING =
            "Все данные синтетические и предназначены только для тестирования. Не для реальных операций.";


}
