package org.exoplatform.platform.component;

import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIForm;
import org.exoplatform.webui.form.UIFormSelectBox;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Racha
 * Date: 08/11/12
 * Time: 09:56
 * To change this template use File | Settings | File Templates.
 */

@ComponentConfig(
        lifecycle = UIFormLifecycle.class,
        template = "app:/groovy/platformNavigation/portlet/UIUserPlatformToolBarCreatePortlet/UICreateForm.gtmpl",
        events = {
                @EventConfig(
                        listeners = UICreateForm.NextActionListener.class,
                        phase = org.exoplatform.webui.event.Event.Phase.DECODE
                ),
                @EventConfig(
                        listeners = UICreateForm.CancelActionListener.class,
                        phase = org.exoplatform.webui.event.Event.Phase.DECODE
                )
        }
)

public class UICreateForm extends UIForm {
    static String LOCATION = "In Location".intern();
    static List<SelectItemOption<String>> options = new ArrayList();
    private static Log log = ExoLogger.getLogger(UICreateForm.class);

    public UICreateForm() {

        addUIFormInput(new UIFormSelectBox(LOCATION, LOCATION, options));
    }

    public String[] getActions() {
        return new String[]{"Next", "Cancel"};
    }

    static public class NextActionListener extends EventListener<UICreateForm> {


        public void execute(Event<UICreateForm> event)
                throws Exception {

            HttpServletRequest request = Util.getPortalRequestContext().getRequest();

            log.info("#################### Next Action was triggered");


        }
    }


    static public class CancelActionListener extends EventListener<UICreateForm> {


        public void execute(Event<UICreateForm> event)
                throws Exception {

            log.info("#################### Cancel  Action was triggered");


        }
    }




}
