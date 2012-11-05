package org.exoplatform.navigation.bar.help;


import org.exoplatform.container.xml.InitParams;
import org.exoplatform.container.xml.PropertiesParam;

/**
 * Created with IntelliJ IDEA.
 * User: fatma
 * Date: 01/11/12
 * Time: 10:31
 * To change this template use File | Settings | File Templates.
 */

public class HelpServiceImpl implements HelpService {


    private String defaultPageHelp = HelpPortletUtils.DEFAULT_HELP_PAGE;
    PropertiesParam param = null;

    public HelpServiceImpl(InitParams initParams) {
         param = initParams.getPropertiesParam("help.pages");
    }

    public String fetchHelpPage(String currentNavigation) {

        String helpPage = param.getProperty(currentNavigation);

        if ((helpPage != null) && (!helpPage.equals(""))) helpPage = param.getProperty("default.help.page");

        if ((helpPage != null) && (!helpPage.equals(""))) helpPage = defaultPageHelp;

        return helpPage;
        //return (((helpPage != null)&&(!helpPage.equals(""))) ? helpPage : defaultPageHelp);
        // return HelpPortletUtils.DEFAULT_HELP_PAGE;
    }
}
