package client;

import constants.Endpoints;
import io.restassured.response.Response;
import requestSpecifications.RequestBuilder;
import requestSpecifications.RequestSpec;

import java.util.HashMap;
import java.util.Map;

public class PincodeApiClient {

    public Response checkPincode(
            String productId,
            String pincode) {

        Map<String, Object> queryParams = new HashMap<>();

        queryParams.put("product_id", productId);

        // Do not send pincode when it is null
        if (pincode != null) {
            queryParams.put("pincode", pincode);
        }

        return RequestBuilder.getRequest(
                Endpoints.PINCODE_CHECK,
                RequestSpec.getAuthenticatedSpec(),
                queryParams
        );
    }
}