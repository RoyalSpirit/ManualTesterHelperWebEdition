package demo.web.ui;

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
import demo.domain.Language;
import demo.service.GeneratorService;
import demo.web.ui.components.ResultRow;
import demo.web.ui.components.ResultRowOptions;

import static demo.web.i18n.UiText.*;
import static demo.web.ui.util.FieldErrors.clear;
import static demo.web.ui.util.FieldErrors.showFieldErrorAutoHide;
import static demo.web.ui.util.Fields.value;

@Route(value = "strings", layout = MainLayout.class)
@PageTitle(MENU_STRINGS)
public class StringsView extends VerticalLayout {

    public StringsView(GeneratorService service) {
        addClassName("content");
        setWidthFull();

        add(numbersCard(service));
        add(textCard(service));
        add(symbolsCard(service));
    }

    private VerticalLayout numbersCard(GeneratorService service) {
        var card = card(CARD_DIGITS_TITLE, service,
                BTN_GENERATE_DIGITS,
                service::generateRandomNumbers,
                320, 500
        );
        return card;
    }

    private VerticalLayout symbolsCard(GeneratorService service) {
        var card = card(CARD_SYMBOLS_TITLE, service,
                BTN_GENERATE_SYMBOLS,
                service::generateRandomSymbols,
                320, 500
        );
        return card;
    }

    private VerticalLayout textCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_STRINGS_TITLE));

        TextField lenField = new TextField(FIELD_LENGTH);
        lenField.setWidth("180px");
        lenField.setMaxLength(6);
        lenField.setAllowedCharPattern("\\d");
        lenField.setValueChangeMode(ValueChangeMode.EAGER);
        lenField.setClearButtonVisible(true);

        Span textLengthErrorText = new Span();
        textLengthErrorText.addClassName("field-error-popover");

        Popover textLengthError = new Popover();
        textLengthError.setTarget(lenField);
        textLengthError.setPosition(PopoverPosition.TOP_START);
        textLengthError.setOpenOnClick(false);
        textLengthError.setOpenOnHover(false);
        textLengthError.setOpenOnFocus(false);
        textLengthError.add(textLengthErrorText);

        lenField.addValueChangeListener(e -> clear(lenField, textLengthError, textLengthErrorText));

        ComboBox<Language> lang = new ComboBox<>(FIELD_LANGUAGE);
        lang.setItems(Language.values());
        lang.setWidth("180px");
        lang.setValue(Language.RUSSIAN);

        HorizontalLayout row = new HorizontalLayout(lenField, lang);
        row.setAlignItems(Alignment.END);

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = "320px";
        base.resultFieldWidth = "500px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_TEXT;

        ResultRow result = new ResultRow(opts, () -> {
            String v = value(lenField);

            if (v.isEmpty()) {
                showFieldErrorAutoHide(lenField, textLengthError, textLengthErrorText, EMPTY_STRUNG_ERROR_TEXT, 2000);
                return "";
            }
            if (!v.matches("[1-9]\\d{0,5}")) {
                showFieldErrorAutoHide(lenField, textLengthError, textLengthErrorText, CANT_BE_ZERO_ERROR_TEXT, 2000);
                return "";
            }
            return service.generateRandomText(Integer.parseInt(v), lang.getValue());
        });
        card.add(row, result);
        return card;
    }

    private VerticalLayout card(
            String title,
            GeneratorService service,
            String buttonText,
            java.util.function.IntFunction<String> gen,
            int btnW,
            int fieldW
    ) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(title));

        TextField lenField = new TextField(FIELD_LENGTH);
        lenField.setWidth("180px");
        lenField.setMaxLength(6);
        lenField.setAllowedCharPattern("\\d");
        lenField.setValueChangeMode(ValueChangeMode.EAGER);
        lenField.setClearButtonVisible(true);

        Span textLengthErrorText = new Span();
        textLengthErrorText.addClassName("field-error-popover");

        Popover textLengthError = new Popover();
        textLengthError.setTarget(lenField);
        textLengthError.setPosition(PopoverPosition.TOP_START);
        textLengthError.setOpenOnClick(false);
        textLengthError.setOpenOnHover(false);
        textLengthError.setOpenOnFocus(false);
        textLengthError.add(textLengthErrorText);

        lenField.addValueChangeListener(e -> clear(lenField, textLengthError, textLengthErrorText));

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = btnW + "px";
        base.resultFieldWidth = fieldW + "px";

        var opts = base.copy();
        opts.generateText = buttonText;

        ResultRow result = new ResultRow(opts, () -> {
            String v = value(lenField);

            if (v.isEmpty()) {
                showFieldErrorAutoHide(lenField, textLengthError, textLengthErrorText, EMPTY_STRUNG_ERROR_TEXT, 2000);
                return "";
            }
            if (!v.matches("[1-9]\\d{0,5}")) {
                showFieldErrorAutoHide(lenField, textLengthError, textLengthErrorText, CANT_BE_ZERO_ERROR_TEXT, 2000);
                return "";
            }
            return gen.apply(Integer.parseInt(v));
        });

        card.add(lenField, result);
        return card;
    }

}
