import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;

public class Server {

    public static void startServer(int port) {

        try{
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

            server.createContext("/scenario", new HttpHandler(){
                @Override
                public void handle(HttpExchange exchange) throws IOException {

                    if ("POST".equals(exchange.getRequestMethod()))
                    {
                        try
                        {
                                String requestBody = new String(exchange.getRequestBody().readAllBytes());
                                System.out.println("Received request: " + requestBody);
                                String technology = extractValue(requestBody, "technology");
                                String role = extractValue(requestBody, "role");
                                String environment = extractValue(requestBody, "environment");

                                if (technology == null || technology.trim().isEmpty() ||
                                        role == null || role.trim().isEmpty() ||
                                        environment == null || environment.trim().isEmpty()) {


                                    sendErrorResponse(exchange, "Missing required fields: technology, role, environment");
                                    return;
                                }

                                ResponseGenerator responseGenerator = new ResponseGenerator();
                                String result = responseGenerator.generateScenario(technology, role, environment);

                                sendSuccessResponse(exchange, result);

                        }
                        catch (Exception e)
                        {
                                e.printStackTrace();
                                sendErrorResponse(exchange, "Internal server error");
                        }
                    }
                    else
                    {
                        sendErrorResponse(exchange, "Method not allowed. Use POST.");
                    }
                }
            });

            server.setExecutor(null);
            server.start();
            System.out.println("Server started on port " + port);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void sendErrorResponse(HttpExchange exchange, String errorMessage) throws IOException{
        String errorJson = "{\"error\": \"" + errorMessage + "\"}";
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); // FIXED: Added CORS header
        exchange.sendResponseHeaders(400, errorJson.length());
        exchange.getResponseBody().write(errorJson.getBytes());
        exchange.getResponseBody().close();
    }

    private static void sendSuccessResponse(HttpExchange exchange, String responseBody) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.sendResponseHeaders(200, responseBody.length());
        exchange.getResponseBody().write(responseBody.getBytes());
        exchange.getResponseBody().close();
    }
    //Extract value using Regex
    private static String extractValue(String json, String key) {
        try {
            // Look for "key": "value" pattern
            String searchPattern = "\"" + key + "\"";
            int keyIndex = json.indexOf(searchPattern);

            if (keyIndex == -1) {
                return null; // Key not found
            }

            // Find the colon after the key
            int colonIndex = json.indexOf(":", keyIndex);
            if (colonIndex == -1) {
                return null;
            }

            // Find the opening quote of the value
            int startQuoteIndex = json.indexOf("\"", colonIndex);
            if (startQuoteIndex == -1) {
                return null;
            }

            // Find the closing quote of the value
            int endQuoteIndex = json.indexOf("\"", startQuoteIndex + 1);
            if (endQuoteIndex == -1) {
                return null;
            }

            // Extract the value between quotes
            return json.substring(startQuoteIndex + 1, endQuoteIndex);

        } catch (Exception e) {
            return null;
        }
    }
}