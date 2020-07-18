package ru.open.site;

import ru.open.site.pages.GoogleSearchPage;
import ru.open.site.pages.OpenRuMainPage;

public class SitePageManager {
    private GoogleSearchPage googleSearchPage = new GoogleSearchPage();
    private OpenRuMainPage openRuMainPage = new OpenRuMainPage();

    public GoogleSearchPage google() {
        return googleSearchPage;
    }

    public OpenRuMainPage openru() {
        return openRuMainPage;
    }
}
