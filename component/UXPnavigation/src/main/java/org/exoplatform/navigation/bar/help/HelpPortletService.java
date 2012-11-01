package org.exoplatform.navigation.bar.help;


import org.exoplatform.container.xml.InitParams;

/**
 * Created with IntelliJ IDEA.
 * User: fatma
 * Date: 01/11/12
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */

public class HelpPortletService {


    private String defaultPageHelp = HelpPortletUtils.DEFAULT_HELP_PAGE;

    public HelpPortletService(InitParams initParams) {
        defaultPageHelp = initParams.getValueParam("default.help.page").getValue();
    }

    public String getDefaultPageHelp() {
        return defaultPageHelp;
    }
}
