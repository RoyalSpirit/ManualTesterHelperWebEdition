package demo.web.ui;

import com.vaadin.flow.component.checkbox.Checkbox;
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
import demo.service.GeneratorService;
import demo.web.ui.components.ResultRow;
import demo.web.ui.components.ResultRowOptions;

import java.util.UUID;

import static demo.web.i18n.UiText.*;
import static demo.web.ui.util.FieldErrors.*;
import static demo.web.ui.util.Fields.value;

@Route(value = "codes", layout = MainLayout.class)
@PageTitle(MENU_CODES)
public class CodesView extends VerticalLayout {

    public CodesView(GeneratorService service) {
        addClassName("content");
        setWidthFull();

        add(uuidCard());
        add(bicCard(service));
        add(swiftCard(service));
    }

    private VerticalLayout uuidCard() {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_UUID_TITLE));

        Checkbox noDashes = new Checkbox(GENERATE_WITHOUT_DASHES_CHECKBOX_TITLE);

        HorizontalLayout optsRow = new HorizontalLayout(noDashes);
        optsRow.setPadding(false);
        optsRow.setSpacing(true);
        optsRow.setAlignItems(Alignment.END);

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = "250px";
        base.resultFieldWidth = "400px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_UUID;

        ResultRow row = new ResultRow(opts, () -> {
            String uuid = UUID.randomUUID().toString();

            if (Boolean.TRUE.equals(noDashes.getValue())) {
                uuid = uuid.replace("-", "");
            }

            return uuid;
        });

        card.add(optsRow, row);
        return card;
    }

    private VerticalLayout bicCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_BIC_TITLE));

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = "250px";
        base.resultFieldWidth = "200px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_BIC;

        card.add(new ResultRow(opts, service::generateBic));
        return card;
    }

    private VerticalLayout swiftCard(GeneratorService service) {
        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_SWIFT_TITLE));

        TextField countryField = new TextField(INPUT_COUNTRY_CODE_FIELD_TITLE);
        countryField.setWidth("180px");
        countryField.setValueChangeMode(ValueChangeMode.EAGER);
        countryField.setMaxLength(2);
        countryField.setAllowedCharPattern("[a-zA-Z]");
        countryField.setClearButtonVisible(true);
        countryField.setRequiredIndicatorVisible(true);

        Span countryErrorText = new Span();
        countryErrorText.addClassName("field-error-popover");

        Popover countryError = new Popover();
        countryError.setTarget(countryField);
        countryError.setPosition(PopoverPosition.TOP_START);
        countryError.setOpenOnClick(false);
        countryError.setOpenOnHover(false);
        countryError.setOpenOnFocus(false);
        countryError.add(countryErrorText);

        countryField.addValueChangeListener(e -> {
            clear(countryField, countryError, countryErrorText);
            String v = e.getValue();
            if (v != null) {
                String up = v.trim().toUpperCase();
                if (!up.equals(v)) countryField.setValue(up);
            }
        });

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = "250px";
        base.resultFieldWidth = "200px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_SWIFT;

        ResultRow row = new ResultRow(opts, () -> {
            String cc = value(countryField);

            if (cc.isEmpty()) {
                showFieldErrorAutoHide(countryField, countryError, countryErrorText, EMPTY_COUNTRY_CODE_ERROR_TEXT, 2000);
                return "";
            }
            if (cc.length() != 2) {
                showFieldErrorAutoHide(countryField, countryError, countryErrorText, TWO_DIGITS_COUNTRY_CODE_ERROR_TEXT, 2000);
                return "";
            }
            return service.generateSwift(cc);
        });

        card.add(countryField, row);
        return card;
    }

}
