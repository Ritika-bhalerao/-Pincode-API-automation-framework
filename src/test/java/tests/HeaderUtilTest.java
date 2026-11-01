package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.HeaderUtil;

import java.util.Map;

public class HeaderUtilTest {

    @Test
    public void verifySelectedHeaders() {

        Map<String, String> headers = HeaderUtil.getHeaders(
                "accept",
                "is_ssr",
                "device_id",
                "token"
        );

        // Validate header count
        Assert.assertEquals(headers.size(), 4);

        // Validate static headers
        Assert.assertEquals(headers.get("accept"), "*/*");
        Assert.assertEquals(headers.get("is_ssr"), "false");

        // Validate dynamic headers
        Assert.assertNotNull(headers.get("device_id"));
        Assert.assertFalse(headers.get("device_id").trim().isEmpty());

        // Validate token
        Assert.assertNotNull(headers.get("token"));
        Assert.assertFalse(headers.get("token").trim().isEmpty());

        System.out.println("Selected header names: " + headers.keySet());
        System.out.println("Token fetched successfully.");

        String token = headers.get("token");

        Assert.assertNotNull(token);
        Assert.assertFalse(token.trim().isEmpty());

        System.out.println("Token: " + token);
    }
}