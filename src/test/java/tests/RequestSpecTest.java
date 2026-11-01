package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import requestSpecifications.RequestSpec;
import utils.HeaderUtil;

import java.util.Map;

public class RequestSpecTest {

    @Test
    public void verifyAuthenticatedRequestSpec() {

        // Create authenticated specification
        Assert.assertNotNull(RequestSpec.getAuthenticatedSpec());

        // Get the token used by RequestSpec
        Map<String, String> headers =
                HeaderUtil.getHeaders("token");

        String token = headers.get("token");

        Assert.assertNotNull(token, "Token is missing");
        Assert.assertFalse(token.trim().isEmpty(), "Token is empty");

        System.out.println("Token in RequestSpec: " + token);
    }
}