package ru.open.site;

import ru.open.common.WebDriverTools;

public class SiteFrameworkManager {
    private WebDriverTools webDriverTools = new WebDriverTools();
    private SitePageManager site = new SitePageManager();

    public WebDriverTools driver() {
        return webDriverTools;
    }

    public SitePageManager site() {
        return site;
    }

}
