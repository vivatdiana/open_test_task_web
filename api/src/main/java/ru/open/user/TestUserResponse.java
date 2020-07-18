package ru.open.user;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class TestUserResponse extends TestUser {
    int id;
    String createdAt;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public TestUserResponse.Verification verification() {
        return new TestUserResponse.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public TestUserResponse.Verification checkEmail(String email) throws Throwable {
            this.log.info("Check email: {}", email);
            Assert.assertEquals(getEmail(), email);
            return this;
        }

        @Step
        public TestUserResponse.Verification checkFirstName(String first_name) throws Throwable {
            this.log.info("Check first name: {}", first_name);
            Assert.assertEquals(getFirstName(), first_name);
            return this;
        }

        @Step
        public TestUserResponse.Verification checkLastName(String last_name) throws Throwable {
            this.log.info("Check last name: {}", last_name);
            Assert.assertEquals(getLastName(), last_name);
            return this;
        }

        @Step
        public TestUserResponse.Verification checkAvatar(String avatar) throws Throwable {
            this.log.info("Check avatar: {}", avatar);
            Assert.assertEquals(getAvatar(), avatar);
            return this;
        }

        @Step
        public TestUserResponse.Verification checkIdNotNull() throws Throwable {
            this.log.info("Check id is not null");
            Assert.assertNotNull(getId());
            return this;
        }

        @Step
        public TestUserResponse.Verification checkCreatedAtNotNull() throws Throwable {
            this.log.info("Check createdAt is not null");
            Assert.assertNotNull(getCreatedAt());
            return this;
        }
    }
}
