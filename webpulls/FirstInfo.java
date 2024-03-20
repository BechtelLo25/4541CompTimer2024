package webpulls;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import util.APIPuller;

public class FirstInfo {

    public static APIPuller apiPuller = new APIPuller();

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

            String response = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024");
            
            return response.toString().substring(response.toString().indexOf("Name") + 7, response.toString().indexOf("kick") - 6);

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }

}