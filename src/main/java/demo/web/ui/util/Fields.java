package demo.web.ui.util;

import com.vaadin.flow.component.textfield.TextField;

public final class Fields {
    private Fields() {}

    public static String value(TextField tf) {
        String s = tf.getValue();
        return s == null ? "" : s.trim();
    }
}
