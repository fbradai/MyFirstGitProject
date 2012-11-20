package org.exoplatform.platform.component;

import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPopupContainer;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 01/11/12
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
@ComponentConfig(
        lifecycle = UIApplicationLifecycle.class,
        template = "app:/groovy/platformNavigation/portlet/UIUserPlatformToolBarCreatePortlet/UIUserPlatformToolBarCreatePortlet.gtmpl"
)
public class UIUserPlatformToolBarCreatePortlet extends UIPortletApplication {


    private String renderedCompId_;

    public UIUserPlatformToolBarCreatePortlet() throws Exception {
        addChild(UICreateList.class, null, null);
        addChild(UIPopupContainer.class, null, "CreatePortletPopUPContainer");
    }


    public String getRenderedId(Class T, UIUserPlatformToolBarCreatePortlet uiParent) {
        return uiParent.getChild(T).getId();
    }


}
