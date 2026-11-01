package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PincodeCheckResponse {

    private Integer pincode;

    @JsonProperty("is_location_error")
    private Boolean locationError;

    private String message;

    @JsonProperty("delivery_by")
    private String deliveryBy;

    @JsonProperty("product_available")
    private Integer productAvailable;

    private Integer statusCode;

    private String error;


    public Integer getPincode() {
        return pincode;
    }

    public Boolean getIs_location_error() {
        return locationError;
    }

    public String getMessage() {
        return message;
    }

    public String getDelivery_by() {
        return deliveryBy;
    }

    public Integer getProduct_available() {
        return productAvailable;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }
}