package org.exoplatform.platform.portlet.juzu.gettingstarted;

import juzu.Controller;
import juzu.Path;
import juzu.View;
import juzu.template.Template;
import org.exoplatform.social.core.service.LinkProvider;

import javax.inject.Inject;
import java.util.HashMap;

/**
 * @author <a href="fbradai@exoplatform.com">Fbradai</a>
 * @date 07/12/12
*/
public class GettingStarted extends Controller {

    String remoteUser = renderContext.getSecurityContext().getRemoteUser();

    @Inject
    @Path("gettingStart.gtmpl")
    Template gettingStart;

    @View
    public void index() throws Exception {
        HashMap parameters =new HashMap();
        parameters.put("profile", LinkProvider.getUserProfileUri(remoteUser)) ;
        parameters.put("profileLabel","Upload a Profile Picture");
        parameters.put("connect",LinkProvider.getUserActivityUri(remoteUser) ) ;
        parameters.put("connectLabel","Connect With Collegues");
        parameters.put("space","/portal/intranet/all-spaces");
        parameters.put("spaceLabel","Join a Space");
        parameters.put("activity","#");
        parameters.put("activityLabel","Post an activity");
        parameters.put("upload","portal/intranet/documents");
        parameters.put("uploadLabel","Upload a Document");
        gettingStart.render(parameters);
    }
}
