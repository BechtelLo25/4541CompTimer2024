package webpulls;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class FirstInfo {

    public String getCurrentSeason() {
            
        String apiUrl = "https://frc-api.firstinspires.org/v3.0/";

        try {
            HttpClient client = HttpClient.newHttpClient();
        
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("accept", "text/plain")
                    .GET()
                    .build();
        
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body().substring(response.body().indexOf("Season") + 8, response.body().indexOf("maxSea") - 2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Error";
    }


    public String getCurrentGameName() {

        try {

            String apiUrl = "https://frc-api.firstinspires.org/v3.0/2024";

            URL url = new URL(apiUrl);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            String username = "LoganFRC618";
            String password = "83dc87d4-987f-4959-a0bc-51db9b70c2e4";
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
            String authHeaderValue = "Basic " + new String(encodedAuth);
            connection.setRequestProperty("Authorization", authHeaderValue);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            connection.disconnect();
            return response.toString().substring(response.toString().indexOf("Name") + 7, response.toString().indexOf("kick") - 6);

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }

}