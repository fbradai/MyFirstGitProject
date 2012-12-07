/*
 * Copyright (C) 2011 eXo Platform SAS.
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

package org.exoplatform.platform.portlet.juzu.whosonline;

import juzu.Controller;
import juzu.Path;
import juzu.View;
import org.exoplatform.social.core.identity.model.Profile;

import javax.inject.Inject;
import java.util.List;

/** @author <a href="mailto:rtouzi@exoplatform.com">Racha Touzi</a> */
public class Whosonline extends Controller{

  @Inject
    @Path("whosonline.gtmpl")
    org.exoplatform.platform.portlet.juzu.whosonline.templates.whosonline index;

    @Inject
    WhosonlineListService listService;

    @View
  public void index() {
      String userId =  renderContext.getSecurityContext().getRemoteUser();
      List<Profile> list= listService.getOnlineContacts(userId);
      //index.render(list);
       //whosonline.render();
      index.with().list(list).render();
  }
}
