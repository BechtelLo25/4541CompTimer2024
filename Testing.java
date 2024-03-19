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

public class Testing {
    
    public static void main(String[] args) {


        getEventTeams("MDOWI");

    }

    public static String getEventTeams(String eventID) {

        try {

            String teamList = "";

            String apiUrl = "https://frc-api.firstinspires.org/v3.0/2024/teams?eventCode=" + eventID;

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

            String sortResponse = response.toString();

            for(int i = 0; i < Integer.parseInt(response.toString().substring(response.toString().indexOf("Total") + 7, response.toString().indexOf("teamCountPage") - 2)); i++) {
                
                if(i % 4 == 0 && i != 0) {
                    teamList += sortResponse.substring(sortResponse.indexOf("Number") + 8, sortResponse.indexOf("nameFull") - 2) + "\n";
                } else {
                    teamList += sortResponse.substring(sortResponse.indexOf("Number") + 8, sortResponse.indexOf("nameFull") - 2) + ", ";
                }

                sortResponse = sortResponse.substring(sortResponse.indexOf("nameFull") + 1);

                System.out.println(teamList);
                
            }

            return response.toString();

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }
}
