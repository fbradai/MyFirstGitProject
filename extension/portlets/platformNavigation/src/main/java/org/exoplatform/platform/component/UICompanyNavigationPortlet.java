package org.exoplatform.platform.component;

import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.portal.config.UserACL;
import org.exoplatform.portal.mop.SiteKey;
import org.exoplatform.portal.mop.Visibility;
import org.exoplatform.portal.mop.navigation.Scope;
import org.exoplatform.portal.mop.user.UserNavigation;
import org.exoplatform.portal.mop.user.UserNode;
import org.exoplatform.portal.mop.user.UserNodeFilterConfig;
import org.exoplatform.portal.mop.user.UserPortal;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

import java.util.Collection;
import java.util.Collections;

/**
 * @author <a href="fbradai@exoplatform.com">Fbradai</a>
 * @date 20/11/12
 */
@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/platformNavigation/portlet/UICompanyNavigationPortlet/UICompanyNavigationPortlet.gtmpl")
public class UICompanyNavigationPortlet extends UIPortletApplication {
    private static final Log LOG = ExoLogger.getExoLogger(UICompanyNavigationPortlet.class);

    private UserACL userACL = null;
    private UserNodeFilterConfig userFilterConfig;


    public UICompanyNavigationPortlet() throws Exception {
        UserNodeFilterConfig.Builder builder = UserNodeFilterConfig.builder();
        builder.withReadWriteCheck().withVisibility(Visibility.DISPLAYED, Visibility.TEMPORAL).withTemporalCheck();
        userFilterConfig = builder.build();
        userACL = getApplicationComponent(UserACL.class);
    }

    public UserNavigation getCurrentPortalNavigation() throws Exception {
        return getNavigation(SiteKey.portal(getCurrentPortal()));
    }

    private UserNavigation getNavigation(SiteKey userKey) {
        UserPortal userPortal = getUserPortal();
        return userPortal.getNavigation(userKey);
    }

    private UserPortal getUserPortal() {
        PortalRequestContext portalRequestContext = Util.getPortalRequestContext();
        return portalRequestContext.getUserPortal();
    }

    public String getCurrentPortal() {
        return Util.getPortalRequestContext().getPortalOwner();
    }

    public Collection<UserNode> getUserNodes(UserNavigation nav) {
        UserPortal userPortall = getUserPortal();
        if (nav != null) {
            try {
                UserNode rootNode = userPortall.getNode(nav, Scope.ALL, userFilterConfig, null);
                return rootNode.getChildren();
            } catch (Exception exp) {
                LOG.warn(nav.getKey().getName() + " has been deleted");
            }
        }
        return Collections.emptyList();
    }
    public UserNode getSelectedPageNode() throws Exception {
        return Util.getUIPortal().getSelectedUserNode();
    }

}
