package dataproviders;

import org.testng.annotations.DataProvider;

public class PincodeDataProvider {

    @DataProvider(name = "pincodeData")
    public static Object[][] pincodeData() {

        return new Object[][] {

                // Product ID, Pincode, Stock Status

                {"421921", "171008", "IN_STOCK"},

                {"421921", "121213", "OOS"},

                {"421921", null, "MISSING_PINCODE"}

        };
    }
}
