package webpulls;
import java.io.IOException;
import java.util.ArrayList;

import util.APIPuller;
import util.StandardTimeConverter;

public class CompSchedule {

    public static StandardTimeConverter standardTimeConverter = new StandardTimeConverter();

    public static APIPuller apiPuller = new APIPuller();

    public static ArrayList<String> qualifiers = new ArrayList<>();
    public static ArrayList<String> teamColors = new ArrayList<>();
    public static boolean qualifiersRecieved = false;

    public static String getCompSchedule(String eventID, String teamNum) {

        try {

            String compSchedule = "";


            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/schedule/" + eventID + "?tournamentLevel=qual&teamNumber=" + teamNum + "&start=&end=");
            String target = teamNum;
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
            String SortTeamColor = webPull;

            for (int i = 0; i < count; i++) {
                String[] colors = {"Red 1", "Red 2", "Red 3", "Blue 1", "Blue 2", "Blue 3"};

                compSchedule += "Qualification " + SortResponse.substring(SortResponse.indexOf("ation") + 6, SortResponse.indexOf("start") - 3) + ": ";

                if(qualifiersRecieved == false) {
                    qualifiers.add(SortResponse.substring(SortResponse.indexOf("ation") + 6, SortResponse.indexOf("start") - 3));

                    SortTeamColor = SortTeamColor.substring(SortTeamColor.indexOf(teamNum));
                    teamColors.add(SortTeamColor.substring(SortTeamColor.indexOf(teamNum) + 16, SortTeamColor.indexOf("surrogate") - 4));
                    SortTeamColor = SortTeamColor.substring(SortTeamColor.indexOf(teamNum) + 1);
                }

                for (String color : colors) {
                    compSchedule += color + ": " + SortTeamNums.substring(SortTeamNums.indexOf("teamNumber") + 12, SortTeamNums.indexOf("station") - 2) + " ";
                    SortTeamNums = SortTeamNums.substring(SortTeamNums.indexOf("station") + 7);
                }

                compSchedule += "\tColor: " + teamColors.get(i);

                compSchedule += "\tStart Time: " + standardTimeConverter.getStandardTime(SortResponse.substring(SortResponse.indexOf("startTime") + 23, SortResponse.indexOf("matchNumber") - 3));
                SortResponse = SortResponse.substring(SortResponse.indexOf("Blue3") + 7);

                compSchedule += getMatchStats(eventID, qualifiers.get(i), teamColors.get(i)) + "\n\n";

            }
            qualifiersRecieved = true;

            return compSchedule;

        } catch (Exception e) {
            e.printStackTrace();

            return "No Data";
        }

    }

    public static String getMatchStats(String eventID, String qualifier, String color) throws IOException {

        try {
            String stats = "";
            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/scores/" + eventID + "/qual?matchNumber=" + qualifier + "&start=&end=");

            int blueScore = Integer.parseInt(webPull.substring(webPull.indexOf("totalPoints") + 13, webPull.indexOf("Red") - 15));
            webPull = webPull.substring(webPull.indexOf("totalPoints") + 20);
            int redScore = Integer.parseInt(webPull.substring(webPull.indexOf("totalPoints") + 13, webPull.indexOf("}]}]}")));

            stats += "\tBlue Total Points: " + blueScore;
            stats += "\tRed Total Points: " + redScore;

            if(color.equals("Red")) {
                if (redScore > blueScore) {
                    stats += "\tWin";
                } else if (redScore < blueScore) {
                    stats += "\tLoss";
                } else if (redScore == blueScore) {
                    stats += "\tTie";
                }
            } else {
                if (redScore > blueScore) {
                    stats += "\tLoss";
                } else if (redScore < blueScore) {
                    stats += "\tWin";
                } else if (redScore == blueScore) {
                    stats += "\tTie";
                }
            }
            
            return stats;

        } catch(Exception e) {
            e.printStackTrace();

            return "";
        }
    }

    public static int getAmountOfMatches(String eventID) {
        
        try {

            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/schedule/" + eventID + "?tournamentLevel=qual&teamNumber=&start=&end=");
            String target = "matchNumber";
            int count = 0;
            int lastIndex = 0;

            while (lastIndex != -1) {
                lastIndex = webPull.indexOf(target, lastIndex);
                if (lastIndex != -1) {
                    count++;
                    lastIndex += target.length();
                }
            }
            return count;
            
        } catch (Exception e) {
            
        }
        return 200;
    }

    public static int getCurrentMatch(String eventID) {
        
        int currentMatch = 0;

        try {

           
            boolean currentMatchRecieved = false;

            String webPull = apiPuller.pullFromAPI("https://frc-api.firstinspires.org/v3.0/2024/matches/" + eventID + "?tournamentLevel=qual&teamNumber=&matchNumber=&start=&end=");

            while(currentMatchRecieved == false){
                if (webPull.substring(webPull.indexOf("scoreRedFinal") + 15, webPull.indexOf("scoreRedFoul") - 2).equals("null")) {
                    currentMatchRecieved = true;
                } else {
                    currentMatch++;
                    webPull = webPull.substring(webPull.indexOf("scoreBlueFinal") + 1);
                }
            }
            
            return currentMatch;

        } catch (Exception e) {
            
            return currentMatch;
        }
        
    }

    public static String getIfQueing(String eventID) {

        for(String qual : qualifiers) {
            if (getCurrentMatch(eventID) < Integer.parseInt(qual) && getCurrentMatch(eventID) >= Integer.parseInt(qual) - 3) {
                return "Queing";
            } 
        }
        return "Not Queing";
    }

}
