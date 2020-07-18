package ru.open.test.testcases;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.open.EndPoints;
import ru.open.test.RestAssuredLogListener;
import ru.open.user.TestUser;
import ru.open.user.TestUserResponse;
import ru.open.userlist.UserListResponse;

import static io.restassured.RestAssured.given;

@Listeners({RestAssuredLogListener.class})
public class ApiTaskTest {

    @Severity(SeverityLevel.CRITICAL)
    @Story("Api Test Task")
    @Test(description = "Get user list", priority = 1)
    public void checkGettingUserList() throws Throwable {
        UserListResponse response = given()
                .contentType(ContentType.JSON).
        when()
                .get(EndPoints.GET_USER_LIST_URL).
        then()
                .statusCode(200)
                .log().body()
                .extract().response().as(UserListResponse.class);

        response.verification()
                .checkAdNotNull()
                .checkDataNotNull()
                .checkPageNotNull()
                .checkPerPageNotNull()
                .checkTotalNotNull()
                .checkTotalPagesNotNull();

        response.getAd().verification()
                .checkCompanyNotNull()
                .checkTextNotNull()
                .checkUrlNotNull();

        response.getData().get(0).verification()
                .checkIdNotNull()
                .checkAvatarNotNull()
                .checkEmailNotNull()
                .checkFirstNameNotNull()
                .checkLastNameNotNull();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Story("Api Test Task")
    @Test(description = "Create user", priority = 2)
    public void checkUserCreating() throws Throwable {
        TestUser user = new TestUser("michael.lawson@reqres.in", "Michael", "Lawson", "https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg");

        TestUserResponse response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .log().body().
        when()
                .post(EndPoints.CREATE_USER_URL).
        then()
                .statusCode(201)
                .log().body()
                .extract().response().as(TestUserResponse.class);

        response.verification()
                .checkEmail(user.getEmail())
                .checkFirstName(user.getFirstName())
                .checkLastName(user.getLastName())
                .checkAvatar(user.getAvatar())
                .checkIdNotNull()
                .checkCreatedAtNotNull();
    }
}
