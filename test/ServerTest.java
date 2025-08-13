import org.junit.jupiter.api.Test;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerTest {

    @Test
    public void testServer_ShouldRejectGET() throws Exception {
        // Start server in background thread
        new Thread(() -> Server.startServer(8082)).start();
        Thread.sleep(1000); // Wait for server to start

        // Test GET request (should get 400 - method not allowed)
        URL url = new URL("http://localhost:8082/scenario");
        HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
        getConnection.setRequestMethod("GET");
        int getResponseCode = getConnection.getResponseCode();
        assertTrue(getResponseCode == 400, "Server should respond with 400 for GET");
        getConnection.disconnect();
    }

    @Test
    public void testServer_ShouldAcceptPOST() throws Exception {

        new Thread(() -> Server.startServer(8083)).start();
        Thread.sleep(1000);

        // Test POST request with valid data (should get 200 - success)
        URL url = new URL("http://localhost:8083/scenario"); // ADD THIS LINE
        HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        String validJson = "{\"technology\":\"Java\",\"role\":\"Developer\",\"environment\":\"Test\"}";
        postConnection.getOutputStream().write(validJson.getBytes());
        int postResponseCode = postConnection.getResponseCode();
        assertTrue(postResponseCode == 200, "Server should respond with 200 for valid POST");
        postConnection.disconnect();
    }
}