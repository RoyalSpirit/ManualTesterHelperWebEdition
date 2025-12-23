package demo.web.ui.components;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

import java.util.function.Supplier;

import static demo.web.i18n.UiText.*;

public class ResultRow extends HorizontalLayout {

    private final TextField field = new TextField();
    private final Supplier<String> generator;

    private final Button btnGenerate;
    private final Icon copyIcon;

    public ResultRow(ResultRowOptions opts, Supplier<String> generator) {
        this.generator = generator;

        addClassName("row");
        setWidthFull();
        setAlignItems(Alignment.CENTER);
        setSpacing(true);

        field.setReadOnly(true);

        btnGenerate = new Button(opts.generateText);
        btnGenerate.addClassName("btn");
        if (opts.generateButtonWidth != null && !opts.generateButtonWidth.isBlank()) {
            btnGenerate.setWidth(opts.generateButtonWidth);
        }
        btnGenerate.addClickListener(e -> generateNow());

        copyIcon = new Icon(VaadinIcon.COPY_O);
        copyIcon.addClassName("copy-icon");
        copyIcon.getElement().setProperty("title", TOOLTIP_COPY);
        copyIcon.addClickListener(e -> copyToClipboard(field.getValue()));

        if (opts.resultFieldWidth != null && !opts.resultFieldWidth.isBlank()) {
            field.setWidth(opts.resultFieldWidth);
        } else {
            field.setWidthFull();
        }

        add(btnGenerate, field, copyIcon);

        if (opts.resultFieldWidth == null || opts.resultFieldWidth.isBlank()) {
            expand(field);
        }
    }


    /** Универсально для любых View: можно дергать из "Сгенерировать всё". */
    public void generateNow() {
        field.setValue(nullToEmpty(generator.get()));
    }

    public String value() {
        return field.getValue();
    }

    private void copyToClipboard(String text) {
        String value = text == null ? "" : text;

        getUI().ifPresent(ui ->
                ui.getPage().executeJs(
                        "navigator.clipboard.writeText($0);",
                        value
                )
        );

        Notification.show(TOAST_COPIED, 1200, Notification.Position.TOP_CENTER);
    }


    private static String nullToEmpty(String s) {
        return s == null ? "" : s;
    }
}
