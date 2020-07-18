package ru.open.userlist;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import ru.open.user.TestUser;

public class User extends TestUser {
    int id;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User.Verification verification() {
        return new User.Verification();
    }

    public class Verification {
        private Logger log;

        private Verification() {
            this.log = LogManager.getLogger(Class.class.getSimpleName());
        }

        @Step
        public User.Verification checkIdNotNull() throws Throwable {
            this.log.info("Check id is not null");
            Assert.assertNotNull(getId());
            return this;
        }

        @Step
        public User.Verification checkAvatarNotNull() throws Throwable {
            this.log.info("Check avatar is not null");
            Assert.assertNotNull(getAvatar());
            return this;
        }

        @Step
        public User.Verification checkEmailNotNull() throws Throwable {
            this.log.info("Check email is not null");
            Assert.assertNotNull(getEmail());
            return this;
        }

        @Step
        public User.Verification checkFirstNameNotNull() throws Throwable {
            this.log.info("Check first_name is not null");
            Assert.assertNotNull(getFirstName());
            return this;
        }

        @Step
        public User.Verification checkLastNameNotNull() throws Throwable {
            this.log.info("Check last_name is not null");
            Assert.assertNotNull(getLastName());
            return this;
        }
    }
}
