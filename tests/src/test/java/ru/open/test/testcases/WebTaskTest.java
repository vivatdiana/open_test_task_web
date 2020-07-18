package ru.open.test.testcases;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.open.site.SiteFrameworkManager;
import ru.open.test.TestListener;

@Listeners({TestListener.class})
public class WebTaskTest {
    SiteFrameworkManager web = new SiteFrameworkManager();

    @BeforeMethod
    public void openUrl() {
        web.driver().browserUp("https://www.google.com/");
    }

    @AfterMethod
    public void closeBrowser() throws Throwable {
        web.driver().browserTearDown();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Web Test Task")
    @Test(description = "Find open.ru site via google search and compare exchange courses", priority = 1)
    public void findOpenRuAndCompareExchangeCourses() throws Throwable {
        web.site().google()
                .enterSearchCriteria("Открытие")
                .clickSearchInGoogleButton();

        web.site().google().verification()
                .checkUrlDisplayed("https://www.open.ru");

        web.site().google().navigateToUrl("https://www.open.ru");

        web.site().openru().verification()
                .checkSellPriceMoreThanBuyPrice("USD")
                .checkSellPriceMoreThanBuyPrice("EUR");
    }
}
