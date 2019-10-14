package demo.demounittest;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class WebTestUtil {
    private WebTestUtil() {
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = TestContextConfig.objectMapper();
        return mapper.writeValueAsBytes(object);
    }
}
