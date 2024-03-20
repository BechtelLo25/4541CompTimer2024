package webpulls;
import util.APIPuller;

public class EventTeams {

    public static APIPuller apiPuller = new APIPuller();
    
    public static String getEventTeams(String eventID) {

        try {

            String teamList = "";

            String response = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/teams?eventCode=" + eventID);
            String sortResponse = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/teams?eventCode=" + eventID);

            for(int i = 0; i < Integer.parseInt(response.toString().substring(response.toString().indexOf("Total") + 7, response.toString().indexOf("teamCountPage") - 2)); i++) {

                teamList += sortResponse.substring(sortResponse.indexOf("Number") + 8, sortResponse.indexOf("nameFull") - 2) + ", ";
                sortResponse = sortResponse.substring(sortResponse.indexOf("nameFull") + 1);
            }

            return teamList;

        } catch (Exception e) {
            e.printStackTrace();

            return "No Data";
        }

    }
}
