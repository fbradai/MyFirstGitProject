package org.exoplatform.platform.component;

import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.core.lifecycle.Lifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 08/11/12
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */

@ComponentConfig(
        lifecycle = Lifecycle.class,
        template = "app:/groovy/platformNavigation/portlet/UIUserPlatformToolBarCreatePortlet/UICreateForm.gtmpl",
        events = {
                @EventConfig(
                        listeners = UICreateForm.NextActionListener.class

                ),
                @EventConfig(
                        listeners = UICreateForm.CancelActionListener.class
                )
        }
)

public class UICreateForm extends UIContainer {
    static String LOCATION = "In Location".intern();
    static List<SelectItemOption<String>> options = new ArrayList();
    private static final String SWITCH_SPACE_ACTION = "SwitchSpace";
    public static final String SPACE_SWITCHER = "UIWikiSpaceSwitcher";
    private static final String CREATE_FORM_CONTAINER = "UICreateForm";

    private static Log log = ExoLogger.getLogger(UICreateForm.class);

    public UICreateForm() throws Exception {

        //  UIWikiSpaceSwitcher uiWikiSpaceSwitcher = addChild(UIWikiSpaceSwitcher.class, null, SPACE_SWITCHER);
        //   EventUIComponent eventComponent = new EventUIComponent(CREATE_FORM_CONTAINER, SWITCH_SPACE_ACTION, EventUIComponent.EVENTTYPE.EVENT);
        //   uiWikiSpaceSwitcher.init(eventComponent);


        //  addUIFormInput(new UIFormSelectBox(LOCATION, LOCATION, options));
    }

    public String[] getActions() {
        return new String[]{"Next", "Cancel"};
    }

    static public class NextActionListener extends EventListener<UICreateForm> {


        public void execute(Event<UICreateForm> event)
                throws Exception {

            HttpServletRequest request = Util.getPortalRequestContext().getRequest();

            log.info("#################### Next Action was triggered");


        }
    }


    static public class CancelActionListener extends EventListener<UICreateForm> {


        public void execute(Event<UICreateForm> event)
                throws Exception {

            log.info("#################### Cancel  Action was triggered");
            UICreateList uiparent = event.getSource().getAncestorOfType(UICreateList.class);
            WebuiRequestContext context = event.getRequestContext();
            if(uiparent.getChild(UICreateForm.class)!=null) {
            uiparent.removeChild(UICreateForm.class);
            }

            log.info("*********************** Cancel  Action was triggered");

            context.addUIComponentToUpdateByAjax(uiparent);


        }
    }


}
