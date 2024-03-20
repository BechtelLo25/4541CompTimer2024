package webpulls;
import util.APIPuller;
import util.StandardTimeConverter;

public class CompSchedule {

    public static StandardTimeConverter standardTimeConverter = new StandardTimeConverter();

    public static APIPuller apiPuller = new APIPuller();

    public static String get4541CompSchedule(String eventID) {

        try {

            String compSchedule = "";


            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/schedule/" + eventID + "?tournamentLevel=qual&teamNumber=4541&start=&end=");
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
                String[] colors = {"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3"};

                compSchedule += "Qualification " + SortResponse.substring(SortResponse.indexOf("ation") + 6, SortResponse.indexOf("start") - 3) + ": ";

                for (String color : colors) {
                    compSchedule += color + ": " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                    SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                }

                compSchedule += "\tExpected Start Time: " + standardTimeConverter.getStandardTime(SortResponse.substring(SortResponse.indexOf("startTime") + 23, SortResponse.indexOf("matchNumber") - 3)) + "\n\n";
                SortResponse = SortResponse.substring(SortResponse.indexOf("Blue3") + 7);

            }

            return compSchedule;

        } catch (Exception e) {
            e.printStackTrace();

            return "Error";
        }

    }
}