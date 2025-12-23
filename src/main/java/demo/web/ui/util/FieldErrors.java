package demo.web.ui.util;

import com.vaadin.flow.component.popover.Popover;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;

public final class FieldErrors {

    private FieldErrors() {}

    public static void clear(TextField field, Popover popover, Span text) {
        field.setInvalid(false);
        text.setText("");
        popover.close();
    }

    public static void showFieldErrorAutoHide(TextField field, Popover popover, Span text, String msg, int ms) {
        field.setInvalid(true);
        text.setText(msg);
        popover.open();

        popover.getElement().executeJs(
                "const el=this;" +
                        "clearTimeout(el.__mthT);" +
                        "el.__mthT=setTimeout(()=>{ el.opened=false; }, $0);",
                ms
        );
    }
}
