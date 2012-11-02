package org.exoplatform.platform.component;


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


    private HelpService helpService = null;
    private String currentNavigation= null;

    public UIHelpPlatformToolbarPortlet() throws Exception {
    super();
    }

  public String getCurrentNavigation()
  {

      try {
          currentNavigation= Util.getUIPortal().getNavPath().getName();
      } catch (Exception e) {
          e.printStackTrace();
      }
       if (currentNavigation!=null)
      return currentNavigation;
      else return "default";
  }
    public  String getHelpPage()
    {
        try {
            helpService= getApplicationComponent(HelpService.class);
        } catch (Exception exception) {
        }
        if (helpService == null) {
            return HelpPortletUtils.DEFAULT_HELP_PAGE;
        }
        System.out.println("currentNavigation"+getCurrentNavigation());
        return helpService.fetchHelpPage(currentNavigation);
    }
}
