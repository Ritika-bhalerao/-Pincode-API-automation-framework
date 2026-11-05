
package tests;

import client.PincodeApiClient;
import model.PincodeCheckResponse;
import dataproviders.PincodeDataProvider;

import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PincodeCheckTest {

    private static final Logger logger =
            LogManager.getLogger(PincodeCheckTest.class);

    private final PincodeApiClient apiClient =
            new PincodeApiClient();


    // ==========================================
    // PINCODE CHECK API TEST
    // ==========================================

    @Test(
            dataProvider = "pincodeData",
            dataProviderClass = PincodeDataProvider.class,
            description = "Validate product delivery based on pincode"
    )

    public void verifyPincodeCheck(
            String productId,
            String pincode,
            String stockStatus) {

        logger.info("======================================");
        logger.info("STARTING PINCODE CHECK TEST");
        logger.info("Product ID: {}", productId);
        logger.info("Pincode: {}", pincode);
        logger.info("Stock Status: {}", stockStatus);

        try {

            // ==================================
            // 1. API CALL
            // ==================================

            Response response =
                    apiClient.checkPincode(productId, pincode);

            logger.info("HTTP Status Code: {}", response.statusCode());
            logger.info("Response Body: {}", response.asString());


            // ==================================
            // 2. RESPONSE DESERIALIZATION
            // ==================================

            PincodeCheckResponse responseBody =
                    response.as(PincodeCheckResponse.class);

            Assert.assertNotNull(
                    responseBody,
                    "Response body should not be null");


            // ==================================
            // 3. IN STOCK VALIDATION
            // ==================================

            if ("IN_STOCK".equalsIgnoreCase(stockStatus)) {

                logger.info("Validating IN_STOCK response");

                // Validate HTTP status

                Assert.assertEquals(
                        response.statusCode(),
                        200,
                        "HTTP status code mismatch");


                // Validate pincode

                Assert.assertEquals(
                        responseBody.getPincode(),
                        Integer.valueOf(pincode),
                        "Pincode mismatch");


                // Validate location error

                Assert.assertEquals(
                        responseBody.getIs_location_error(),
                        Boolean.FALSE,
                        "is_location_error should be false");


                // Validate delivery message is not null

                Assert.assertNotNull(
                        responseBody.getMessage(),
                        "Delivery message is missing");


                // Validate delivery estimate format

                // Assert.assertTrue(
                //         responseBody.getMessage().matches(
                //                 "Get delivery in \\d+ days?, \\d{1,2} [A-Za-z]{3,}"
                //         ),
                //         "Delivery message format mismatch: "
                //                 + responseBody.getMessage()
                // );


                logger.info(
                        "Delivery Estimate: {}",
                        responseBody.getMessage()
                );

                logger.info("IN_STOCK validation PASSED");
            }


            // ==================================
            // 4. OOS VALIDATION
            // ==================================

            else if ("OOS".equalsIgnoreCase(stockStatus)) {

                logger.info("Validating OOS response");

                // Validate HTTP status

                Assert.assertEquals(
                        response.statusCode(),
                        200,
                        "HTTP status code mismatch");


                // Validate pincode

                Assert.assertEquals(
                        responseBody.getPincode(),
                        Integer.valueOf(pincode),
                        "Pincode mismatch");


                // Validate location error

                Assert.assertEquals(
                        responseBody.getIs_location_error(),
                        Boolean.TRUE,
                        "is_location_error should be true");


                // Validate product availability

                Assert.assertEquals(
                        responseBody.getProduct_available(),
                        Integer.valueOf(0),
                        "product_available mismatch");


                // Validate OOS message

                Assert.assertEquals(
                        responseBody.getMessage(),
                        "Sorry! Items not deliverable to this pincode",
                        "OOS message mismatch");


                logger.info("OOS validation PASSED");
            }


            // ==================================
            // 5. MISSING PINCODE VALIDATION
            // ==================================

            else if ("MISSING_PINCODE".equalsIgnoreCase(stockStatus)) {

                logger.info("Validating MISSING_PINCODE response");

                // Validate HTTP status

                Assert.assertEquals(
                        response.statusCode(),
                        400,
                        "Expected HTTP status 400");


                // Validate JSON status code

                Assert.assertEquals(
                        responseBody.getStatusCode(),
                        Integer.valueOf(400),
                        "JSON statusCode mismatch");


                // Validate error

                Assert.assertEquals(
                        responseBody.getError(),
                        "Bad Request",
                        "Error field mismatch");


                // Validate error message

                Assert.assertEquals(
                        responseBody.getMessage(),
                        "pincode query parameter is required",
                        "Error message mismatch");


                logger.info("MISSING PINCODE validation PASSED");
            }


            // ==================================
            // 6. INVALID TEST DATA
            // ==================================

            else {

                Assert.fail(
                        "Invalid stock status received: "
                                + stockStatus
                );
            }


            logger.info("TEST PASSED: {}", stockStatus);

        }


        // ==================================
        // 7. ASSERTION ERROR HANDLING
        // ==================================

        catch (AssertionError e) {

            logger.error("TEST FAILED: {}", stockStatus, e);

            throw e;
        }


        // ==================================
        // 8. EXCEPTION HANDLING
        // ==================================

        catch (Exception e) {

            logger.error("Unexpected exception: {}", stockStatus, e);

            throw e;
        }


        // ==================================
        // 9. FINALLY BLOCK
        // ==================================

        finally {

            logger.info("PINCODE CHECK TEST COMPLETED");
            logger.info("======================================");
        }
    }
}
