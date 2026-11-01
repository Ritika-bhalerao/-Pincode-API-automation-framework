package utils;

import config.ConfigReader;
import constants.Endpoints;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {

    private static String token;

    public static synchronized String getToken() {

        if (token == null || token.trim().isEmpty()) {
            token = fetchToken();
        }

        return token;
    }

    private static String fetchToken() {

        String deviceId = ConfigReader.getProperty("device.id");

        if (deviceId == null || deviceId.trim().isEmpty()) {
            throw new RuntimeException("Device ID is missing in config.properties");
        }

        Response response = RestAssured.given()
                .baseUri(ConfigReader.getBaseUrl())
                .header("device_id", deviceId)
                .header("mode_device", "android")
                .header("type", "android")
                .header("user-agent", "okhttp/5.3.1")
                .contentType(ContentType.JSON)
                .when()
                .get(Endpoints.TOKEN)
                .then()
                .extract()
                .response();

        if (response.statusCode() != 200) {
            throw new RuntimeException(
                    "Token API failed. Status: " + response.statusCode()
            );
        }

        String fetchedToken = response.jsonPath().getString("token");

        if (fetchedToken == null || fetchedToken.trim().isEmpty()) {
            throw new RuntimeException("Token not found in API response.");
        }

        System.out.println("Authentication token fetched successfully.");

        return fetchedToken;
    }

    public static synchronized void clearToken() {
        token = null;
    }
}

