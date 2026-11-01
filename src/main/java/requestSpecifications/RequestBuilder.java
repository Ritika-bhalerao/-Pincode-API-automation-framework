package requestSpecifications;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestBuilder {

    private static final Logger log =
            LoggerUtil.getLogger(RequestBuilder.class);

    public static Response getRequest(
            String endpoint,
            RequestSpecification requestSpec,
            Map<String, ?> queryParams) {

        log.info("Sending GET request: {}", endpoint);

        long startTime = System.currentTimeMillis();

        Response response = given()
                .spec(requestSpec)
                .queryParams(queryParams)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();

        long responseTime = System.currentTimeMillis() - startTime;

        log.info("Response Status Code: {}", response.statusCode());
        log.info("Response Time: {} ms", responseTime);

        return response;
    }
}