package ru.open.userlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.List;

public class UserListResponse {
    int page;
    @JsonProperty("per_page")
    int perPage;
    int total;
    @JsonProperty("total_pages")
    int totalPages;
    List<User> data;
    Ad ad;

    public int getPage() {
        return this.page;
    }

    public void setPage() {
        this.page = page;
    }

    public int getPerPage() {
        return this.perPage;
    }

    public void setPerPage(int per_page) {
        this.perPage = per_page;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int total_pages) {
        this.totalPages = total_pages;
    }

    public Ad getAd() {
        return this.ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public List<User> getData() {
        return this.data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public UserListResponse.Verification verification() {
        return new UserListResponse.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public UserListResponse.Verification checkTotalNotNull() throws Throwable {
            this.log.info("Check total is not null");
            Assert.assertNotNull(getTotal());
            return this;
        }

        @Step
        public UserListResponse.Verification checkPageNotNull() throws Throwable {
            this.log.info("Check page is not null");
            Assert.assertNotNull(getPage());
            return this;
        }

        @Step
        public UserListResponse.Verification checkPerPageNotNull() throws Throwable {
            this.log.info("Check per_page is not null");
            Assert.assertNotNull(getPerPage());
            return this;
        }

        @Step
        public UserListResponse.Verification checkTotalPagesNotNull() throws Throwable {
            this.log.info("Check total_pages is not null");
            Assert.assertNotNull(getTotalPages());
            return this;
        }

        @Step
        public UserListResponse.Verification checkAdNotNull() throws Throwable {
            this.log.info("Check ad is not null");
            Assert.assertNotNull(getAd());
            return this;
        }

        @Step
        public UserListResponse.Verification checkDataNotNull() throws Throwable {
            this.log.info("Check data is not null");
            Assert.assertNotNull(getData());
            return this;
        }
    }
}
