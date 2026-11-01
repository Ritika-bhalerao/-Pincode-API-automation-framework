package utils;

import config.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class HeaderUtil {

    private static Map<String, String> getStaticHeaders() {

        Map<String, String> headers = new HashMap<>();

        headers.put("accept", "*/*");
        headers.put("content-type", "application/json");
        headers.put("cache-control", "no-cache");
        headers.put("is_ssr", "false");
        headers.put("mode_device", "mobile");

        return headers;
    }

    public static Map<String, String> getHeaders(String... headerNames) {

        Map<String, String> allHeaders = getStaticHeaders();
        Map<String, String> selectedHeaders = new HashMap<>();

        for (String name : headerNames) {

            String key = name.toLowerCase();

            if (allHeaders.containsKey(key)) {
                selectedHeaders.put(key, allHeaders.get(key));
            }
            else if (key.equals("token")) {
                selectedHeaders.put(key, TokenManager.getToken());
            }
            else if (key.equals("device_id")) {
                selectedHeaders.put(
                        key,
                        ConfigReader.getProperty("device.id")
                );
            }
            else {
                throw new IllegalArgumentException("Unknown header: " + name);
            }
        }

        return selectedHeaders;
    }
}
