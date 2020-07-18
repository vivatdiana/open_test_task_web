package ru.open.site.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;

public class OpenRuMainPage {
    private static Logger log = LogManager.getLogger(Class.class.getSimpleName());

    //checks
    public OpenRuMainPage.Verification verification() {
        return new OpenRuMainPage.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public OpenRuMainPage.Verification checkSellPriceMoreThanBuyPrice(String currency) throws Throwable {
            this.log.info("Check sell price is more than buy price for currency: {}", currency);
            Assert.assertTrue(getSellValue(currency) > (getBuyValue(currency)));
            return this;
        }

        private Double getSellValue(String currency) throws Throwable {
            SelenideElement rootElement = $(By.xpath("//span[text()='" + currency + "']"));
            return convertStringToDouble(rootElement.parent().parent().parent().$x(".//td[4]//span").getText());
        }

        private Double getBuyValue(String currency) throws Throwable {
            SelenideElement rootElement = $(By.xpath("//span[text()='" + currency + "']"));
            return convertStringToDouble(rootElement.parent().parent().parent().$x(".//td[2]//span").getText());
        }

        private Double convertStringToDouble(String value) throws Throwable {
            NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
            Number number = format.parse(value);
            Double d = number.doubleValue();
            return d;
        }
    }

}
