package org.exoplatform.platform.component;

import org.exoplatform.cs.event.UICreateEvent;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.wcm.webui.Utils;
import org.exoplatform.webui.commons.UIDocumentSelector;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIContainer;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

                ),
                @EventConfig(
                        listeners = UICreateList.WikiActionListener.class

                ),

                @EventConfig
                        (listeners = UICreateList.UploadActionListener.class
                        )
        }
)

public class UICreateList extends UIContainer {
    static String parStatus;

    public static void remove(UICreateList uiform) {
        List<UIComponent> uilist = uiform.getChildren();
        List<String> lisID = new ArrayList<String>();
       if(uilist.size()!=0) {
        for (UIComponent uIComponent : uilist) {
            lisID.add(uIComponent.getId());
        }
        for (String id : lisID) {
            uiform.removeChildById(id);

        }
       }
    }


    static public class AddEventActionListener extends EventListener<UICreateList> {


        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            UICreateList uisource = (UICreateList) event.getSource();
            HttpServletRequest request = Util.getPortalRequestContext().getRequest();
            remove(uisource);
            uisource.addChild(UICreateEvent.class, null, null).setRendered(true);
            event.getRequestContext().addUIComponentToUpdateByAjax(uisource);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }

    }

    static public class PollActionListener extends EventListener<UICreateList> {


        public void execute(Event<UICreateList> event)
                throws Exception {

            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            parStatus = event.getRequestContext().getRequestParameter("objectId");

            UICreateList uisource = (UICreateList) event.getSource();
            remove(uisource);
            uisource.addChild(UICreateForm.class, null, null).setRendered(true);
            event.getRequestContext().addUIComponentToUpdateByAjax(uisource);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }

    }


    static public class TopicActionListener extends EventListener<UICreateList> {


        public void execute(Event<UICreateList> event)
                throws Exception {

             UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            parStatus = event.getRequestContext().getRequestParameter("objectId");
            UICreateList uisource = (UICreateList) event.getSource();
            remove(uisource);
            uisource.addChild(UICreateForm.class, null, null).setRendered(true);
            event.getRequestContext().addUIComponentToUpdateByAjax(uisource);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }
    }


    static public class WikiActionListener extends EventListener<UICreateList> {

        public void execute(Event<UICreateList> event)
                throws Exception {
            UIUserPlatformToolBarCreatePortlet uiForm = (UIUserPlatformToolBarCreatePortlet) event.getSource().getAncestorOfType(UIUserPlatformToolBarCreatePortlet.class);
            parStatus = event.getRequestContext().getRequestParameter("objectId");
            UICreateList uisource = (UICreateList) event.getSource();
            remove(uisource);
            uisource.addChild(UICreateForm.class, null, null).setRendered(true);
            event.getRequestContext().addUIComponentToUpdateByAjax(uisource);
            event.getRequestContext().addUIComponentToUpdateByAjax(uiForm);
        }
    }


    /**
     * *
     */

    static public class UploadActionListener extends EventListener<UICreateList> {

        public void execute(Event<UICreateList> event) throws Exception {
            UICreateList uiCraeteList = event.getSource();
            //PortalRequestContext prContext = Util.getPortalRequestContext();

            try {
                UIDocumentSelector selector = uiCraeteList.createUIComponent(UIDocumentSelector.class, null, "UploadFileSelector");
                Utils.createPopupWindow(uiCraeteList, selector, "UploadFileSelectorPopUpWindow", 335);
            } catch (Exception e) {
                //TODO add log
            }
        }
    }

    /**
     * **
     */

    public static String getParStatus() {
        return parStatus;
    }

    public String[] getActions() {
        return new String[]{"Topic", "Poll", "AddEvent", "Wiki", "Upload"};
    }
}
