package utils;

import aquality.selenium.browser.AqualityServices;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ApiUtil {
    public static String getRequest(String basePath) {
        return getRequest(basePath, HttpStatus.SC_OK);
    }

    public static String getRequest(String basePath, Integer statusCode) {
        AqualityServices.getLogger().info("SEND GET REQUEST TO " + RestAssured.baseURI + basePath);
        return given()
                .accept(ContentType.JSON)
                .get(basePath)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .assertThat()
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .body()
                .asString();
    }

    public static String postRequest(String basePath, String requestBody) {
        return postRequest(basePath, requestBody, HttpStatus.SC_OK);
    }

    public static String postRequest(String basePath, String requestBody, Integer statusCode) {
        AqualityServices.getLogger().info("SEND POST REQUEST TO " + RestAssured.baseURI + basePath);
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(basePath)
                .then()
                .assertThat()
                .statusCode(statusCode)
                .assertThat()
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .body()
                .asString();
    }
}
