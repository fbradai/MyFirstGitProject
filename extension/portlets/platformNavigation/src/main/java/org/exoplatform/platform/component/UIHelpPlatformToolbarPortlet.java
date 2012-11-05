package org.exoplatform.platform.component;


import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.navigation.bar.help.HelpPortletUtils;
import org.exoplatform.navigation.bar.help.HelpService;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * Created with IntelliJ IDEA.
 * User: fatma
 * Date: 01/11/12
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/platformNavigation/portlet/UIHelpPlatformToolbarPortlet/UIHelpPlatformToolbarPortlet.gtmpl")
public class UIHelpPlatformToolbarPortlet extends UIPortletApplication {

    private String currentNavigation = null;
    private HelpService helpService = null;

    public UIHelpPlatformToolbarPortlet() throws Exception {

    }

    public void getCurrentNavigation() {
        try {
            currentNavigation = Util.getUIPortal().getNavPath().getName();
            if (currentNavigation == null)   currentNavigation= "default.help.page";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHelpPage() {

        getCurrentNavigation();
        //helpService = getApplicationComponent(HelpService.class);

        ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
        helpService = (HelpService) myContainer.getComponentInstanceOfType(HelpService.class);


        if (helpService == null) {
            return HelpPortletUtils.DEFAULT_HELP_PAGE;
        }
        else return helpService.fetchHelpPage(currentNavigation);
    }
}
