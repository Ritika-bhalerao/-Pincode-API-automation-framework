package constants;

public class Endpoints {
    private Endpoints() {

    }
    public static final String TOKEN = "/neo/auth/get/v1";
    public static final String PINCODE_CHECK = "/neo/bff/product/pincode-check";
}

/*
Interview tip:
If asked why you use a private constructor in a constants class,
say: “The class contains only static constants, so creating objects is unnecessary.
A private constructor prevents instantiation, and declaring the class final prevents inheritance.”

Declaration	                   | Purpose
public                         | Allows access from other packages
final on the class	           | Prevents inheritance
static final on the variable   | Defines a class-level constant
private on the constructor	   | Prevents external object creation
 */
