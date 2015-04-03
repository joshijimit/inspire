package org.inspire.wicket;

import org.apache.wicket.protocol.http.WebApplication;

public class TabbedApplication extends WebApplication {
     public TabbedApplication() {
    }

    public Class getHomePage() {
        return TabbedPanelPage.class;
    }

}
