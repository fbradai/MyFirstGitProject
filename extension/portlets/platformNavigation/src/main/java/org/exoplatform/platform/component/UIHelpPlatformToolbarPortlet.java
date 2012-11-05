/**
 * Copyright (C) 2012 eXo Platform SAS.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.exoplatform.platform.component;


import org.exoplatform.platform.navigation.component.help.HelpService;
import org.exoplatform.platform.navigation.component.help.Helper;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.core.UIPortletApplication;
import org.exoplatform.webui.core.lifecycle.UIApplicationLifecycle;

/**
 * @author <a href="kmenzli@exoplatform.com">Kmenzli</a>
 * @date 05/11/12
 */

@ComponentConfig(lifecycle = UIApplicationLifecycle.class, template = "app:/groovy/platformNavigation/portlet/UIHelpPlatformToolbarPortlet/UIHelpPlatformToolbarPortlet.gtmpl")
public class UIHelpPlatformToolbarPortlet extends UIPortletApplication {

    private static final Log LOG = ExoLogger.getExoLogger(UIHelpPlatformToolbarPortlet.class);
    private String currentNavigation = "";
    private String helpPage = "";
    private HelpService helpService = null;

    public UIHelpPlatformToolbarPortlet() throws Exception {
        helpService = getApplicationComponent(HelpService.class);
    }

    public String getHelpPage() {

        getCurrentNavigation();

        //TODO use a Helper Class for this test
        if (currentNavigation != null && currentNavigation.length() !=0) {

            helpPage = helpService.fetchHelpPage(currentNavigation);

        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("The navigation [[ "+currentNavigation+" ]] has as page help [[ "+helpPage+" ]]");
        }

        return  helpPage;
    }

    //TODO put it in helper class
    private void getCurrentNavigation() {
        try {

            currentNavigation =  Util.getUIPortal().getNavPath().getName();

        } catch (Exception E) {

            LOG.warn("Can not load the currentNavigation ",E);

        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("The current Navigation is [[ "+currentNavigation+" ]]");
        }
    }
}
