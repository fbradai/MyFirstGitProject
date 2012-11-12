package org.exoplatform.platform.component;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * Created with IntelliJ IDEA.
 * User: hela
 * Date: 07/11/12
 * Time: 11:54
 * To change this template use File | Settings | File Templates.
 */
@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/platformNavigation/portlet/UISearchPlatformToolBarPortlet/UISearchPlatformToolBarPortlet.gtmpl")
public class UISearchPlatformToolbarPortlet extends UIPortletApplication {


    public UISearchPlatformToolbarPortlet() throws Exception {
        super();
    }
}
