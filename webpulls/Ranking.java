package webpulls;
import util.APIPuller;
import util.StandardTimeConverter;

public class Ranking {

    public static APIPuller apiPuller = new APIPuller();

    public static StandardTimeConverter standardTimeConverter = new StandardTimeConverter();

    public static String get4541Ranking(String eventID) {

        try {

            String ranking = "";
            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/rankings/" + eventID + "?teamNumber=4541&top=");

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
