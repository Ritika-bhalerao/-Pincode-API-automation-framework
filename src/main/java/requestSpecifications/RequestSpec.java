package requestSpecifications;

import config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.HeaderUtil;

public class RequestSpec {

    public static RequestSpecification getDefaultSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .addHeaders(HeaderUtil.getHeaders(
                        "accept",
                        "is_ssr",
                        "mode_device"
                ))
                .build();
    }

    public static RequestSpecification getAuthenticatedSpec() {

        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getBaseUrl())
                .addHeaders(HeaderUtil.getHeaders(
                        "accept",
                        "is_ssr",
                        "mode_device",
                        "token"
                ))
                .build();
    }
}
