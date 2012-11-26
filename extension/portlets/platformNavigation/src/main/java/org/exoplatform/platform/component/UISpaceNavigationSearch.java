package org.exoplatform.platform.component;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormStringInput;

import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 22/11/12
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */


@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/platformNavigation/portlet/UISpaceNavigationPortlet/UISpaceNavigationSearch.gtmpl",
        events = {
                @EventConfig(

                        listeners = UISpaceNavigationSearch.SearchNavigationActionListener.class  ,  phase = org.exoplatform.webui.event.Event.Phase.DECODE

                )
}
)

public class UISpaceNavigationSearch extends UIForm {
    private static final String HTML_ATTRIBUTE_TITLE = "title";
    public static final String SPACE_SEARCH = "SpaceSearch";
    private static final String HTML_ATTRIBUTE_PLACEHOLDER = "placeholder";
    String spaceNameSearch = null;
    public static final String SEARCH = "SearchNavigation";
    private boolean isNewSearch;

    protected static Log log = ExoLogger.getLogger(UISpaceNavigationSearch.class);


    public UISpaceNavigationSearch() throws Exception {
        WebuiRequestContext requestContext = WebuiRequestContext.getCurrentInstance();
        ResourceBundle resourceBundle = requestContext.getApplicationResourceBundle();
        UIFormStringInput findSpace = new UIFormStringInput(SPACE_SEARCH, null, null);
        findSpace.setHTMLAttribute(HTML_ATTRIBUTE_TITLE, resourceBundle.getString("UISpaceSearch.label.FindSpace"));
        findSpace.setHTMLAttribute(HTML_ATTRIBUTE_PLACEHOLDER, resourceBundle.getString("UISpaceSearch.label.DefaultSpaceNameAndDesc"));

       addUIFormInput(findSpace);
    }

    static public class SearchNavigationActionListener extends EventListener<UISpaceNavigationSearch> {
        @Override
        public void execute(Event<UISpaceNavigationSearch> event) throws Exception {
            log.info("search triggered11111111111111111111111111111111111111111111111");
            WebuiRequestContext ctx = event.getRequestContext();
            UISpaceNavigationSearch uiSpaceSearch = event.getSource();

            ResourceBundle resApp = ctx.getApplicationResourceBundle();
            String defaultSpaceNameAndDesc = resApp.getString(uiSpaceSearch.getId() + ".label.DefaultSpaceNameAndDesc");
            String searchCondition = (((UIFormStringInput) uiSpaceSearch.getChildById(SPACE_SEARCH)).getValue());
            if ((searchCondition == null || searchCondition.equals(defaultSpaceNameAndDesc))) {
                // uiSpaceSearch.setSelectedChar(ALL);
                uiSpaceSearch.setSpaceNameSearch(null);
                uiSpaceSearch.setNewSearch(true);
            } else {
                if (searchCondition != null) {
                    searchCondition = searchCondition.trim();
                }


                uiSpaceSearch.setSpaceNameSearch(searchCondition);
                uiSpaceSearch.setNewSearch(true);

                Event<UIComponent> searchEvent = uiSpaceSearch.<UIComponent>getParent().createEvent(SEARCH, Event.Phase.DECODE, ctx);
                if (searchEvent != null) {
                    searchEvent.broadcast();
                }
            }
        }
    }







    public void setSpaceNameSearch(String spaceNameSearch) {
        this.spaceNameSearch = spaceNameSearch;
    }
    public void setNewSearch(boolean isNewSearch) {
        this.isNewSearch = isNewSearch;
    }
    public String getSpaceNameSearch() {
        return spaceNameSearch;
    }



}
