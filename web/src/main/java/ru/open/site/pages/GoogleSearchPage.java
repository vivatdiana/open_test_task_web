package ru.open.site.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class GoogleSearchPage {
    private static Logger log = LogManager.getLogger(Class.class.getSimpleName());

    //elements
    private SelenideElement searchInput = $(By.name("q"));
    private SelenideElement searchInGoogleButton = $(By.name("btnK"));
    private SelenideElement cookieAgreementButton = $(By.xpath("//a[contains(@class, 'cookies-agreement__agree-button')]"));


    //actions
    @Step
    public GoogleSearchPage enterSearchCriteria(String searchCriteria) {
        log.info("Enter search criteria: {}", searchCriteria);
        searchInput.click();
        searchInput.sendKeys(searchCriteria);
        return this;
    }

    @Step
    public GoogleSearchPage clickSearchInGoogleButton() {
        log.info("Click Search in Google button");
        searchInGoogleButton.click();
        return this;
    }

    @Step
    public GoogleSearchPage navigateToUrl(String url) {
        log.info("Navigate to url: {}", url);
        $(By.xpath("//a[contains(@href,'" + url + "')]")).click();
        if (cookieAgreementButton.exists()) {
            cookieAgreementButton.click();
        }
        $(By.xpath("//a[@href='/']")).click();
        return this;
    }

    //checks
    public GoogleSearchPage.Verification verification() {
        return new GoogleSearchPage.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public GoogleSearchPage.Verification checkUrlDisplayed(String url) throws Throwable {
            this.log.info("Check url is displayed: {}", url);
            Assert.assertTrue($(By.xpath("//a[contains(@href,'" + url + "')]")).exists(), "Url is not on the page");
            return this;
        }
    }
}
