package org.exoplatform.platform.component;

import org.exoplatform.cs.event.UICreateEvent;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 09/11/12
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */
@ComponentConfig(
        template = "app:/groovy/platformNavigation/portlet/UIUserPlatformToolBarCreatePortlet/UICreateList.gtmpl",
        events = {
                @EventConfig(
                        listeners = UICreateList.AddEventActionListener.class
                ),
                @EventConfig(
                        listeners = UICreateList.PollActionListener.class

                ),
                @EventConfig(
                        listeners = UICreateList.TopicActionListener.class

                )  ,
                @EventConfig(
                        listeners = UICreateList.WikiActionListener.class

                )
        }
)

public class UICreateList extends UIContainer {
    static String parStatus;

    public static void remove(UIUserPlatformToolBarCreatePortlet uiform) {
        List<UIComponent> uilist = uiform.getChildren();
        List<String> lisID = null;
        for (UIComponent uIComponent : uilist) {
            lisID.add(uIComponent.getId());

        }
        for (String id : lisID) {
            uiform.removeChildById(id);

        }


    }


    static public class AddEventActionListener extends EventListener<UICreateList> {
        Boolean indicator = false;


        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            UICreateList uisource = (UICreateList) event.getSource();
            HttpServletRequest request = Util.getPortalRequestContext().getRequest();

            //uisource.setRendered(false);
            uiForm.getChild(UICreateForm.class) .setRendered(false);
            uiForm.getChild(UICreateEvent.class).setRendered(true);
            // uiForm.addChild(UICreateEvent.class, null, null).setRendered(true);
            //    request.setAttribute("indicator", !indicator);
            //event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }


    }

    static public class PollActionListener extends EventListener<UICreateList> {
        Boolean indicator = false;

        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            parStatus = event.getRequestContext().getRequestParameter("objectId");
            System.out.println("##############" + parStatus);
            HttpServletRequest request = Util.getPortalRequestContext().getRequest();
            // remove(uiForm);
            uiForm.getChild(UICreateEvent.class).setRendered(false);
            uiForm.getChild(UICreateForm.class).setRendered(true);
            //  request.setAttribute("indicator", !indicator);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }


    }


    static public class TopicActionListener extends EventListener<UICreateList> {
        Boolean indicator = false;

        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            HttpServletRequest request = Util.getPortalRequestContext().getRequest();
            parStatus = event.getRequestContext().getRequestParameter("objectId");
            //remove(uiForm);
            uiForm.getChild(UICreateEvent.class).setRendered(false);
            uiForm.getChild(UICreateForm.class).setRendered(true);
            //    request.setAttribute("indicator", !indicator);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }


    }


    static public class WikiActionListener extends EventListener<UICreateList> {
        Boolean indicator = false;

        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            HttpServletRequest request = Util.getPortalRequestContext().getRequest();
            parStatus = event.getRequestContext().getRequestParameter("objectId");
            //remove(uiForm);
            uiForm.getChild(UICreateEvent.class).setRendered(false);
            uiForm.getChild(UICreateForm.class).setRendered(true);
            //    request.setAttribute("indicator", !indicator);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }


    }

    public static String getParStatus() {
        return parStatus;
    }

    public String[] getActions() {
        return new String[]{"Topic", "Poll", "AddEvent","Wiki"};
    }
}
