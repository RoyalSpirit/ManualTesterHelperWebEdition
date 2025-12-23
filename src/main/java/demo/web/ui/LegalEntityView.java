package demo.web.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import demo.service.GeneratorService;
import demo.web.ui.components.ResultRow;
import demo.web.ui.components.ResultRowOptions;

import static demo.web.i18n.UiText.*;

@Route(value = "requisites", layout = MainLayout.class)
@PageTitle(MENU_LEGAL)
public class LegalEntityView extends VerticalLayout {

    public LegalEntityView(GeneratorService service) {
        addClassName("content");
        setWidthFull();

        VerticalLayout card = new VerticalLayout();
        card.addClassName("card");

        card.add(new H3(CARD_LEGAL_TITLE));


        var baseOpts = ResultRowOptions.defaults();
        baseOpts.resultFieldWidth = "250px";
        baseOpts.generateButtonWidth = "250px";

        var optsInn = baseOpts.copy();
        optsInn.generateText = BTN_GENERATE_INN;

        var optsKpp = baseOpts.copy();
        optsKpp.generateText = BTN_GENERATE_KPP;

        var optsOgrn = baseOpts.copy();
        optsOgrn.generateText = BTN_GENERATE_OGRN;

        var optsOkpo = baseOpts.copy();
        optsOkpo.generateText = BTN_GENERATE_OKPO;

        ResultRow inn  = new ResultRow(optsInn, service::generateInn);
        ResultRow kpp  = new ResultRow(optsKpp, service::generateKpp);
        ResultRow ogrn = new ResultRow(optsOgrn, service::generateOgrn);
        ResultRow okpo = new ResultRow(optsOkpo, service::generateOkpo);

        Button generateAll = new Button(BTN_GENERATE_ALL, e -> {
            inn.generateNow();
            kpp.generateNow();
            ogrn.generateNow();
            okpo.generateNow();
        });
        generateAll.setWidth("250px");
        generateAll.addClassName("btn");

        card.add(generateAll, inn, kpp, ogrn, okpo);
        add(card);
    }

}
