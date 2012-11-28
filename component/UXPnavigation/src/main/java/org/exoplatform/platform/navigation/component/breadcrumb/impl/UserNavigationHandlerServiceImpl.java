package org.exoplatform.platform.navigation.component.breadcrumb.impl;

import org.exoplatform.container.xml.InitParams;
import org.exoplatform.platform.navigation.component.breadcrumb.UserNavigationHandlerService;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 28/11/12
 * Time: 11:51
 * To change this template use File | Settings | File Templates.
 */
public class UserNavigationHandlerServiceImpl implements UserNavigationHandlerService {

    List<String> userNavigationuri;
    private static final Log LOG = ExoLogger.getLogger(UserNavigationHandlerServiceImpl.class);

    public UserNavigationHandlerServiceImpl(InitParams initParams) {
        userNavigationuri = initParams.getValuesParam("user.navigation.uri").getValues();
    }


    public List<String> loadUserNavigation() {
        return userNavigationuri;
    }
}
