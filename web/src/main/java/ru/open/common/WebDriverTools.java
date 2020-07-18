package ru.open.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class WebDriverTools {
    private static WebDriver webDriver;
    private static Logger log = LogManager.getLogger(Class.class.getSimpleName());
    private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    private static void setChromeDriverProperty() {
        //Link to webdriver, should be updated together with browser updates
        if(SystemUtils.IS_OS_WINDOWS) {
            System.setProperty(CHROME_DRIVER_PROPERTY, "src/main/resources/webdriver/chromedriver.exe");
        }  else if (SystemUtils.IS_OS_LINUX) {
            System.setProperty(CHROME_DRIVER_PROPERTY, System.getProperty("user.dir") + "/src/main/resources/webdriver/chromedriver_linux64");
            System.setProperty("chromeoptions.args", "--disable-infobars --disable-dev-shm-usage --user-data-dir");
        } else if(SystemUtils.IS_OS_MAC_OSX) {
            System.setProperty(CHROME_DRIVER_PROPERTY, "src/main/resources/webdriver/chromedriver_mac64");
            System.setProperty("chromeoptions.args", "--disable-infobars --disable-dev-shm-usage --user-data-dir");
        }
    }

    @Step
    public static WebDriver browserUp(String url) {
        Configuration.timeout = 10000;
        Configuration.savePageSource = false;
        Configuration.screenshots = true;
        Configuration.reportsFolder = "target/screenshots";

        log.info("OS: {}", SystemUtils.OS_NAME);

        setChromeDriverProperty();
        configureChromerDriver();

        log.info("Open browser with url: {}", url);
        try {
            webDriver.get(url);
            if (url.contains("google.com")) {
                webDriver.manage().addCookie(new Cookie("SEARCH_SAMESITE", "CgQIvY8B"));
                webDriver.manage().addCookie(new Cookie("ANID", "AHWqTUnBFeetyQM6EYEXGZZ50wVxM-l6Mza57-1oFGVg5usOpQhWPXXraNsR_UEF"));
                webDriver.manage().addCookie(new Cookie("1P_JAR", "2020-07-16-09"));
                webDriver.manage().addCookie(new Cookie("SID", "zQfHRsh12c-0KdjvwHSsmSkQ-TKzOgmhp4ppKIaUK1PkhN1lullfBOPDaPDIDB2d6cc4Kw."));
                webDriver.manage().addCookie(new Cookie("__Secure-3PSID", "zQfHRsh12c-0KdjvwHSsmSkQ-TKzOgmhp4ppKIaUK1PkhN1lvS3SbMMFx2j5CDcl6Jg_OQ."));
                webDriver.manage().addCookie(new Cookie("HSID", "AqoafabBLK3YY4xbK"));
                webDriver.manage().addCookie(new Cookie("SSID", "AZh3QBSbLpNu0NG0c"));
                webDriver.manage().addCookie(new Cookie("APISID", "BI_TTLWkHm8jBvn_/Afv9UE0ooXgbY30S6"));
                webDriver.manage().addCookie(new Cookie("SAPISID", "9JE94jY8U0E03_NC/ADTUVFHMOpGHz4Q5R"));
                webDriver.manage().addCookie(new Cookie("__Secure-HSID", "AqoafabBLK3YY4xbK"));
                webDriver.manage().addCookie(new Cookie("__Secure-SSID", "AZh3QBSbLpNu0NG0c"));
                webDriver.manage().addCookie(new Cookie("__Secure-APISID", "BI_TTLWkHm8jBvn_/Afv9UE0ooXgbY30S6"));
                webDriver.manage().addCookie(new Cookie("__Secure-3PAPISID", "9JE94jY8U0E03_NC/ADTUVFHMOpGHz4Q5R"));
                webDriver.manage().addCookie(new Cookie("NID", "204=l6ivcXfJQQhjojADmYOLNJKS6OoyrKB47FhsKlzQOGP8u9_UMnsXmO90oFwULxLWy9VyEt1NznaACYQKoVlSUO-JhCJ0sAfIailwj_w1LoLRQczZhYYiFRFmWX0Pa0J3TRnnORknYTCaLpIGUc75IIoX2womjBaAxn9Om4apaqTJRYr8xX-GWMuMNK8BDGqzX9Q_qHztEdcrBWmkWIJpUPSMLQOtBju7LN3gEIWryhtFfuqLliG3Gc4"));
                webDriver.manage().addCookie(new Cookie("CONSENT", "YES+RU.ru+"));
                webDriver.manage().addCookie(new Cookie("GMAIL_LOGIN", "T1594895837572/1594895837572/1594896190238"));
                webDriver.manage().addCookie(new Cookie("SIDCC", "AJi4QfHhoqzCsktcp6QBI7dH1BtYMTx7hzWpA9mQT1dxA9zG6aI867KLz8q3Or8N0CopAOLc"));
                Selenide.open(url);
            }
        } catch (TimeoutException e) {
            log.error("Catching timeout exception");
            WebDriverRunner.getWebDriver().navigate().refresh();
        }

        webDriver.manage().window().maximize();
        return webDriver;

    }

    private static void configureChromerDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--window-size=1325x744");
        chromeOptions.addArguments("--disable-notifications");
        if (SystemUtils.IS_OS_LINUX) {
            chromeOptions.addArguments("--disable-features=VizDisplayCompositor");
            chromeOptions.addArguments("--headless");
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("enable-automation");
            chromeOptions.addArguments("--disable-browser-side-navigation");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-infobars");
            chromeOptions.addArguments("--disable-dev-shm-usage");
        }

        webDriver = new ChromeDriver(chromeOptions);

        Selenide.sleep(15000); //this delay is needed to avoid Timed out receiving message from renderer error

        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverRunner.setWebDriver(webDriver);
    }

    @Step
    public void browserTearDown() throws Throwable {
        log.info("Close driver");
        webDriver.quit();
    }

    @Step
    public void openUrl(String url) {
        log.info("Open url in current tab: {}", url);
        Selenide.open(url);
    }
}
