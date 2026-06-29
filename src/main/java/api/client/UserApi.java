package api.client;

import api.constants.Api;
import api.models.UserCreds;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import api.models.User;
import org.apache.http.HttpStatus;

import static api.constants.Api.*;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserApi {

    static { RestAssured.baseURI = Api.BASE_URL; }

    //@Step("Создание нового пользователя")
    public static ValidatableResponse createUserReturnResponse(User user) {
        return given()
                .contentType(JSON)
                .and()
                .body(user)
                .when()
                .post(API_REGISTER)
                .then();
    }

    //@Step("Создание нового пользователя и получение токена")
    public static String createUserReturnToken(User user) {
        return createUserReturnResponse(user)
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .extract()
                .jsonPath()
                .getString("accessToken");
    }

    //@Step("Авторизация пользователя и получение токена")
    public static String loginUser(String email, String password) {
        UserCreds userCreds = UserCreds.builder()
                .email(email)
                .password(password)
                .build();

        return given()
                .contentType(JSON)
                .and()
                .body(userCreds)
                .when()
                .post(API_LOGIN)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("success", equalTo(true))
                .extract()
                .jsonPath()
                .getString("accessToken");
    }

    //@Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        given()
                .header("Authorization", accessToken)
                .contentType(JSON)
                .when()
                .delete(API_USER)
                .then();
    }

}
