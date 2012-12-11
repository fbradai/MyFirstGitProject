@Application
@Portlet
@Assets(
        scripts = {
                @Script(id="jquery", src = "gettingStarted/js/jquery-1.8.3.js"),
              //  @Script( src = "gettingStarted/js/gettingStarted.js")

        } ,
        stylesheets = {
                @Stylesheet(src = "gettingStarted/css/gettingstarted.css")   ,

        }
)

package org.exoplatform.platform.portlet.juzu.gettingstarted;

import juzu.Application;
import juzu.plugin.asset.Assets;
import juzu.plugin.asset.Script;
import juzu.plugin.asset.Stylesheet;
import juzu.plugin.portlet.Portlet;