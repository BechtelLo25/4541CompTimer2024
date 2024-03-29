package webpulls;
import util.APIPuller;
import util.StandardTimeConverter;

public class Ranking {

    public static APIPuller apiPuller = new APIPuller();

    public static StandardTimeConverter standardTimeConverter = new StandardTimeConverter();

    public static String getRanking(String eventID, String teamNum) {

        try {

            String ranking = "";
            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/rankings/" + eventID + "?teamNumber=" + teamNum + "&top=");

            double rankingScore = Double.parseDouble(webPull.substring(webPull.indexOf("Order1") + 8, webPull.indexOf("sortOrder2") - 2));
            int rankingPoints = (int) (rankingScore * (Integer.parseInt(webPull.substring(webPull.indexOf("Played") + 8, webPull.indexOf("}]}")))));

            ranking += "Current Rank: " + webPull.substring(webPull.indexOf("rank") + 6, webPull.indexOf("team") - 2);
            ranking += "     Ranking Points: " + rankingPoints;
            ranking += "     Ranking Score: " + rankingScore;
            ranking += "     Record: " + webPull.substring(webPull.indexOf("wins") + 6, webPull.indexOf("losses") - 2) + "-" + 
                             webPull.substring(webPull.indexOf("losses") + 8, webPull.indexOf("ties") - 2) + "-" + 
                             webPull.substring(webPull.indexOf("ties") + 6, webPull.indexOf("qualAverage") - 2);

            return ranking;

        } catch (Exception e) {
            e.printStackTrace();

            return "No Data";
        }

    }
}
