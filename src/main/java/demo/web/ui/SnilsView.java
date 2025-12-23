package demo.web.ui;

import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import demo.service.GeneratorService;
import demo.web.ui.components.ResultRow;
import demo.web.ui.components.ResultRowOptions;

import static demo.web.i18n.UiText.*;

@Route(value = "snils", layout = MainLayout.class)
@PageTitle(MENU_SNILS)
public class SnilsView extends VerticalLayout {

    public SnilsView(GeneratorService service) {
        addClassName("content");
        setWidthFull();

        var card = new VerticalLayout();
        card.addClassName("card");
        card.setWidthFull();

        card.add(new H3(CARD_SNILS_TITLE));
        card.add(new Paragraph(SYNTHETIC_WARNING));

        var base = ResultRowOptions.defaults();
        base.generateButtonWidth = "280px";
        base.resultFieldWidth = "350px";

        var opts = base.copy();
        opts.generateText = BTN_GENERATE_SNILS;

        card.add(new ResultRow(opts, service::generateTestSnils));
        add(card);
    }
}
