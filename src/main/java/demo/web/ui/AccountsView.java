package demo.web.ui;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.popover.PopoverPosition;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import demo.domain.Currency;
import demo.domain.IbanCountry;
import demo.domain.OverdraftPrefixes;
import demo.service.GeneratorService;
import demo.web.ui.components.ResultRow;
import demo.web.ui.components.ResultRowOptions;

import static demo.web.i18n.UiText.*;
import static demo.web.ui.util.FieldErrors.clear;
import static demo.web.ui.util.FieldErrors.showFieldErrorAutoHide;
import static demo.web.ui.util.Fields.value;

@Route(value = "", layout = MainLayout.class)
@PageTitle(MENU_ACCOUNTS)
public class AccountsView extends VerticalLayout {

    public AccountsView(GeneratorService service) {
        addClassName("content");
        setWidthFull();

        add(corrCard(service));
        add(overdraftCard(service));
        add(ibanCard(service));
    }

    private VerticalLayout corrCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_CORR_TITLE));

        TextField bicField = new TextField(BIC_FIELD_TITLE);
        bicField.setWidth("180px");
        bicField.setRequiredIndicatorVisible(true);
        bicField.setAllowedCharPattern("\\d");
        bicField.setMaxLength(9);
        bicField.setClearButtonVisible(true);
        bicField.setValueChangeMode(ValueChangeMode.EAGER);

        Span bicErrorText = new Span();
        bicErrorText.addClassName("field-error-popover");

        Popover bicError = new Popover();
        bicError.setTarget(bicField);
        bicError.setPosition(PopoverPosition.TOP_START);
        bicError.setOpenOnClick(false);
        bicError.setOpenOnHover(false);
        bicError.setOpenOnFocus(false);
        bicError.add(bicErrorText);

        bicField.addValueChangeListener(e -> clear(bicField, bicError, bicErrorText));

        ComboBox<Currency> currencyBox = new ComboBox<>(CURRENCY_COMBOBOX_TITLE);
        currencyBox.setItems(Currency.values());
        currencyBox.setWidth("180px");
        currencyBox.setValue(Currency.values()[0]);

        ComboBox<String> bankTypeBox = new ComboBox<>(CLIENT_TYPE_COMBOBOX_TITLE);
        bankTypeBox.setItems(BANK_TYPE_RESIDENT, BANK_TYPE_NON_RESIDENT);
        bankTypeBox.setWidth("180px");
        bankTypeBox.setValue(BANK_TYPE_RESIDENT);

        Checkbox nostroFlag = new Checkbox(NOSTRO_CHECKBOX);
        nostroFlag.getStyle().set("margin-bottom", "6px");

        HorizontalLayout rowForCorresp = new HorizontalLayout(bicField, currencyBox, bankTypeBox, nostroFlag);
        rowForCorresp.setPadding(false);
        rowForCorresp.setSpacing(true);
        rowForCorresp.setAlignItems(Alignment.END);
        rowForCorresp.setWidthFull();

        var base = ResultRowOptions.defaults();
        base.resultFieldWidth = "350px";
        base.generateButtonWidth = "250px";
        var opts = base.copy();
        opts.generateText = BTN_GENERATE_CORRESP_ACCOUNT;

        ResultRow result = new ResultRow(opts, () -> {

            clear(bicField, bicError, bicErrorText);

            String bic = value(bicField);

            if (bic.isEmpty()) {
                showFieldErrorAutoHide(bicField, bicError, bicErrorText, EMPTY_BIC_ERROR_TEXT, 2000);
                return "";
            }
            if (bic.length() != 9) {
                showFieldErrorAutoHide(bicField, bicError, bicErrorText, NINE_DIGITS_BIC_ERROR_TEXT, 2000);
                return "";
            }

            Currency cur = currencyBox.getValue();
            String curCode = (cur == null) ? "" : cur.code();

            String bankType = bankTypeBox.getValue();
            boolean nostro = nostroFlag.getValue();

            return service.generateCorrespAccount(bic, curCode, bankType, nostro);
        });

        card.add(rowForCorresp, result);
        return card;
    }

    private VerticalLayout overdraftCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_OVERDRAFT_TITLE));

        TextField customPrefix = new TextField(INPUT_PREFIX_FIELD_TITLE);
        customPrefix.setWidth("150px");
        customPrefix.setEnabled(false);
        customPrefix.setAllowedCharPattern("\\d");
        customPrefix.setMaxLength(6);
        customPrefix.getStyle().set("opacity", "0.75");
        customPrefix.setValueChangeMode(ValueChangeMode.EAGER);

        Span prefixErrorText = new Span();
        prefixErrorText.addClassName("field-error-popover");

        Popover prefixError = new Popover();
        prefixError.setTarget(customPrefix);
        prefixError.setPosition(PopoverPosition.TOP_START);
        prefixError.setOpenOnClick(false);
        prefixError.setOpenOnHover(false);
        prefixError.setOpenOnFocus(false);
        prefixError.add(prefixErrorText);

        customPrefix.addValueChangeListener(e -> clear(customPrefix, prefixError, prefixErrorText));

        ComboBox<OverdraftPrefixes> presetBox = new ComboBox<>(SELECT_PREFIX_FIELD_TITLE);
        presetBox.setItems(OverdraftPrefixes.values());
        presetBox.setWidth("270px");
        presetBox.setValue(OverdraftPrefixes.values()[0]);
        presetBox.addValueChangeListener(e -> {
            OverdraftPrefixes value = e.getValue();

            boolean manual = value == OverdraftPrefixes.CUSTOM;
            customPrefix.setEnabled(manual);

            if (!manual) {
                customPrefix.clear();
                clear(customPrefix, prefixError, prefixErrorText);
            }
        });

        TextField corr20 = new TextField(INPUT_CORRESP_ACC_FIELD_TITLE);
        corr20.setWidth("300px");
        corr20.setRequiredIndicatorVisible(true);
        corr20.setAllowedCharPattern("\\d");
        corr20.setMaxLength(20);
        corr20.setClearButtonVisible(true);
        corr20.setValueChangeMode(ValueChangeMode.EAGER);

        Span overdraftAccErrorText = new Span();
        overdraftAccErrorText.addClassName("field-error-popover");

        Popover overdraftAccError = new Popover();
        overdraftAccError.setTarget(corr20);
        overdraftAccError.setPosition(PopoverPosition.TOP_START);
        overdraftAccError.setOpenOnClick(false);
        overdraftAccError.setOpenOnHover(false);
        overdraftAccError.setOpenOnFocus(false);
        overdraftAccError.add(overdraftAccErrorText);

        corr20.addValueChangeListener(e -> clear(corr20, overdraftAccError, overdraftAccErrorText));

        var base = ResultRowOptions.defaults();
        base.resultFieldWidth = "350px";
        base.generateButtonWidth = "250px";

        HorizontalLayout rowForOverdraft = new HorizontalLayout(customPrefix, presetBox, corr20);
        rowForOverdraft.setPadding(false);
        rowForOverdraft.setSpacing(true);
        rowForOverdraft.setAlignItems(Alignment.END);
        rowForOverdraft.setWidthFull();

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_OVERDRAFT_ACCOUNT;

        ResultRow result = new ResultRow(opts, () -> {
            clear(corr20, overdraftAccError, overdraftAccErrorText);
            clear(customPrefix, prefixError, prefixErrorText);

            String ovedraftAccount = value(corr20);
            boolean manual = presetBox.getValue() == OverdraftPrefixes.CUSTOM;

            if (ovedraftAccount.isEmpty()) {
                showFieldErrorAutoHide(corr20, overdraftAccError, overdraftAccErrorText, EMPTY_CORRESP_ACC_ERROR_TEXT, 2000);
                return "";
            }
            if (ovedraftAccount.length() != 20) {
                showFieldErrorAutoHide(corr20, overdraftAccError, overdraftAccErrorText, TWENTY_DIGITS_CORRESP_ACC_ERROR_TEXT, 2000);
                return "";
            }
            if (manual) {
                String prefix = value(customPrefix);
                if (prefix.isEmpty()) {
                    showFieldErrorAutoHide(customPrefix, prefixError, prefixErrorText, EMPTY_PREFIX_ERROR_TEXT, 2000);
                    return "";
                }
            }
            return service.generateOverdraftAccount(presetBox.getValue(), value(corr20), value(customPrefix));
        });

        card.add(rowForOverdraft,result);
        return card;
    }

    private VerticalLayout ibanCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_IBAN_TITLE));

        ComboBox<IbanCountry> countryBox = new ComboBox<>(SELECT_COUNTRY_FIELD_TITLE);
        countryBox.setItems(IbanCountry.values());
        countryBox.setWidth("300px");
        countryBox.setValue(IbanCountry.values()[0]);

        var base = ResultRowOptions.defaults();
        base.resultFieldWidth = "350px";
        base.generateButtonWidth = "250px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_IBAN_ACCOUNT;

        ResultRow result = new ResultRow(opts, () ->
                service.generateIban(countryBox.getValue())
        );

        card.add(countryBox, result);
        return card;
    }

}