package demo.web.ui;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;

import static demo.web.i18n.UiText.*;

public class MainLayout extends AppLayout {

    public MainLayout() {
        addClassName("main-layout");

        VerticalLayout drawer = new VerticalLayout();
        drawer.addClassName("sidebar");
        drawer.setPadding(false);
        drawer.setSpacing(false);

        VerticalLayout nav = new VerticalLayout();
        nav.addClassName("nav");
        nav.setPadding(false);
        nav.setSpacing(false);

        nav.add(link(MENU_ACCOUNTS, AccountsView.class));
        nav.add(link(MENU_LEGAL, LegalEntityView.class));
        nav.add(link(MENU_CODES, CodesView.class));
        nav.add(link(MENU_STRINGS, StringsView.class));
        nav.add(link(MENU_SNILS, SnilsView.class));

        VerticalLayout footer = new VerticalLayout();
        footer.addClassName("sidebar-footer");
        footer.setPadding(false);
        footer.setSpacing(false);
        footer.add(new Span(APP_NAME));
        footer.add(new Span(APP_EDITION));
        footer.add(new Span(APP_VERSION));

        drawer.add(nav);
        drawer.expand(nav);
        drawer.add(footer);

        addToDrawer(drawer);
    }

    private RouterLink link(String text, Class<? extends Component> target) {
        RouterLink link = new RouterLink(text, target);
        link.addClassName("menu-link");
        return link;
    }
}
