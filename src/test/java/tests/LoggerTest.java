package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class LoggerTest {

    private static final Logger log =
            LogManager.getLogger(LoggerTest.class);

    @Test
    public void verifyLogging() {

        log.info("Logging is working successfully");
        log.error("Sample error message");
    }
}