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
package org.exoplatform.platform.common.navigation;

import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.wcm.core.BaseWebSchemaHandler;

import javax.jcr.Node;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author <a href="hzekri@exoplatform.com">hzekri</a>
 * @date 26/11/12
 */
public class NavigationServiceHandler extends BaseWebSchemaHandler {

    Calendar calendar = new GregorianCalendar();

    public void onCreateNode(SessionProvider sessionProvider, final Node portalFolder) throws Exception {

        if (portalFolder.hasNode("ApplicationData")) {
            Node applicationDataFolder = portalFolder.getNode("ApplicationData");
            Node logoApplicationFolder = applicationDataFolder.addNode("logo", NT_UNSTRUCTURED);
            addMixin(logoApplicationFolder, "exo:owneable");
            addMixin(logoApplicationFolder,"exo:datetime");
            addMixin(logoApplicationFolder, "exo:hiddenable");
            logoApplicationFolder.setProperty("exo:dateCreated", calendar);

        }

        portalFolder.getSession().save();
    }

    @Override
    protected String getHandlerNodeType() {
        return "exo:portalFolder";
    }

    @Override
    protected String getParentNodeType() {
         return "nt:unstructured";
    }
}
