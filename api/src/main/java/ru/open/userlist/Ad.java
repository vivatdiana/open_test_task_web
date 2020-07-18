package ru.open.userlist;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class Ad {
    String company;
    String url;
    String text;

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Ad.Verification verification() {
        return new Ad.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public Ad.Verification checkCompanyNotNull() throws Throwable {
            this.log.info("Check company is not null");
            Assert.assertNotNull(getCompany());
            return this;
        }

        @Step
        public Ad.Verification checkUrlNotNull() throws Throwable {
            this.log.info("Check url is not null");
            Assert.assertNotNull(getUrl());
            return this;
        }

        @Step
        public Ad.Verification checkTextNotNull() throws Throwable {
            this.log.info("Check text is not null");
            Assert.assertNotNull(getText());
            return this;
        }
    }
}
