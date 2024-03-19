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

import util.StandardTimeConverter;

public class Ranking {

    public static StandardTimeConverter standardTimeConverter = new StandardTimeConverter();

    public static String get4541Ranking(String eventID) {

        try {

            String ranking = "";

            String apiUrl = "https://frc-api.firstinspires.org/v3.0/2024/rankings/" + eventID + "?teamNumber=4541&top=";

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

            String webPull = response.toString();

            ranking += "Current Rank: " + webPull.substring(webPull.indexOf("rank") + 6, webPull.indexOf("team") - 2);
            ranking += "     Ranking Score: " + webPull.substring(webPull.indexOf("Order1") + 8, webPull.indexOf("sortOrder2") - 2);
            ranking += "     Record: " + webPull.substring(webPull.indexOf("wins") + 6, webPull.indexOf("losses") - 2) + "-" + 
                             webPull.substring(webPull.indexOf("losses") + 8, webPull.indexOf("ties") - 2) + "-" + 
                             webPull.substring(webPull.indexOf("ties") + 6, webPull.indexOf("qualAverage") - 2);

            return ranking;

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }
}
