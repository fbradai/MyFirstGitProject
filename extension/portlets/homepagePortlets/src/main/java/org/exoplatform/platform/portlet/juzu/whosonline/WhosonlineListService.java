package org.exoplatform.platform.portlet.juzu.whosonline;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.impl.RuntimeDelegateImpl;
import org.exoplatform.social.core.identity.model.Identity;
import org.exoplatform.social.core.identity.model.Profile;
import org.exoplatform.social.core.identity.provider.OrganizationIdentityProvider;
import org.exoplatform.social.core.manager.IdentityManager;
import org.exoplatform.social.core.manager.RelationshipManager;
import org.exoplatform.social.core.relationship.model.Relationship;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.RuntimeDelegate;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 06/12/12
 * Time: 18:04
 * To change this template use File | Settings | File Templates.
 */
public class WhosonlineListService {
    private static Log log = ExoLogger.getLogger(WhosonlineListService.class);

    private static final CacheControl cacheControl;
    static {
        RuntimeDelegate.setInstance(new RuntimeDelegateImpl());
        cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
    }

    public List<Profile> getOnlineContacts(String userId) {

        try {


            if(userId == null) {
                return null;
        }

            ForumService forumService = (ForumService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(ForumService.class);
            IdentityManager identityManager = (IdentityManager) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(IdentityManager.class);
            RelationshipManager relationshipManager = (RelationshipManager) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(RelationshipManager.class);

            Identity myIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, userId);
            List<String> users = forumService.getOnlineUsers();



            List<Profile> parameters = new ArrayList<Profile>();
            for (String user : users) {


                Identity userIdentity = identityManager.getOrCreateIdentity(OrganizationIdentityProvider.NAME, user);

                if (relationshipManager.getStatus(userIdentity, myIdentity) == null)
                    continue;
                else if (!relationshipManager.getStatus(userIdentity, myIdentity).equals(Relationship.Type.CONFIRMED))
                    continue;

                //if user is not a contact, skip him

                Profile userProfile = userIdentity.getProfile();
                String avatar = userProfile.getAvatarImageSource();
                if (avatar == null) {avatar = "/social-resources/skin/ShareImages/Avatar.gif"; }
                String position = userProfile.getPosition();
                if (position == null) {position = "";}
                parameters.add(userProfile);
                log.info(userProfile.getFullName());
            }

            return parameters;

        }
        catch (Exception e) {
            log.error("Error in who's online rest service: " + e.getMessage(), e);
            return null;
        }
    }




    private String getViewerId(UriInfo uriInfo) {
        URI uri = uriInfo.getRequestUri();
        String requestString = uri.getQuery();
        if (requestString == null) return null;
        String[] queryParts = requestString.split("&");
        for (String queryPart : queryParts) {
            if (queryPart.startsWith("opensocial_viewer_id")) {
                return queryPart.substring(queryPart.indexOf("=") + 1, queryPart.length());
            }
        }
        return null;
    }
}
