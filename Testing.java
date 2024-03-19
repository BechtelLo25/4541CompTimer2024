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


        get4541CompSchedule("MDSEV");

    }

    public static String get4541CompSchedule(String eventID) {

        try {

            String compSchedule = "";

            String apiUrl = "https://frc-api.firstinspires.org/v3.0/2024/schedule/" + eventID + "?tournamentLevel=qual&teamNumber=4541&start=&end=";

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
            String target = "4541";
            int count = 0;
            int lastIndex = 0;

            while (lastIndex != -1) {
                lastIndex = webPull.indexOf(target, lastIndex);
                if (lastIndex != -1) {
                    count++;
                    lastIndex += target.length();
                }
            }

            String SortResponse = webPull;
            String SortTeamNums = webPull;

            for (int i = 0; i < count; i++) {
                compSchedule += "Qualification " + SortResponse.substring(SortResponse.indexOf("ation") + 6, SortResponse.indexOf("start") - 3) + ": ";
                compSchedule += "Red 1: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                compSchedule += "Red 2: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                compSchedule += "Red 3: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                compSchedule += "Blue 1: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                compSchedule += "Blue 2: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                compSchedule += "Blue 3: " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + "";

                compSchedule += "\t --- Expected Start Time: " + SortResponse.substring(SortResponse.indexOf("startTime") + 23, SortResponse.indexOf("matchNumber") - 3) + "\n";

                SortResponse = SortResponse.substring(SortResponse.indexOf("Blue3") + 7);

            }
            
            System.out.println(compSchedule);


            return compSchedule;

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }
}
