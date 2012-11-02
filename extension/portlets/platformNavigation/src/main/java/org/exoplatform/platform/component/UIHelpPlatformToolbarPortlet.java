package org.exoplatform.platform.component;

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
@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/platformNavigation/portlet/UIHelpPlatformToolBarPortlet/UIHelpPlatformToolBarPortlet.gtmpl")
public class UIHelpPlatformToolbarPortlet extends UIPortletApplication {

    //private static final Log LOG = ExoLogger.getLogger(UIHelpPlatformToolbarPortlet.class);
    //private HelpPortletService helpService = null;
    private String currentPage= null;
    public UIHelpPlatformToolbarPortlet() throws Exception {
    super();
     /*   try {
           // helpService= getApplicationComponent(HelpPortletService.class);
        } catch (Exception exception) {
            LOG.error("HelpPortletService null  ", exception);
        }  */
    /*    if (helpService == null) {
            return;
        }    */
       // System.out.println("getNavigationData"+Util.getPortalRequestContext().getNavigationData());
        System.out.println("looooooooooooooooooooooooooooooooooooooooooooooooooool");
    }

  public String getCurrentPage()
  {
      System.out.println("getNavigationData"+Util.getPortalRequestContext().getNavigationData());
      System.out.println("getPortalContextPath"+Util.getPortalRequestContext().getPortalContextPath());
      System.out.println("getParentAppRequestContext"+Util.getPortalRequestContext().getParentAppRequestContext());
      System.out.println("getNodePath"+Util.getPortalRequestContext().getNodePath());
      System.out.println("getPortalURI"+Util.getPortalRequestContext().getPortalURI());
      System.out.println("getRequestURI"+Util.getPortalRequestContext().getRequestURI());
      System.out.println("getRequest().getRequestURI()"+Util.getPortalRequestContext().getRequest().getRequestURI());
     // System.out.println(("getInitialURI()"+Util.getPortalRequestContext().getInitialURI()!=null)?Util.getPortalRequestContext().getInitialURI().toString():"null");
      return Util.getPortalRequestContext().getInitialURI();
  }
    public  void getHelpPage()
    {

     //   getCurrentPage();
      //  return helpService.getDefaultPageHelp();

    }
}
