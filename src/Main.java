package src;
// Championship event id - CHCMP
// Ashland VA event id - VAASH
// Blacksburg VA event id - VABLA
// District Falls VA event id - VAFAL
// Glen Allen VA event id - VAGLE
// Portsmouth VA event id - VAPOR
// Spalding event id - MDSEV
// McDonogh event id - MDOWI

// https://frc-api.firstinspires.org/v3.0/2024/rankings/MDSEV?teamNumber=4541&top=
// https://frc-api.firstinspires.org/v3.0/2024/matches/MDSEV?tournamentLevel=qual&teamNumber=4541&matchNumber=&start=&end=

public class Main {

    public static Frame frame = new Frame();
    public static void main(String[] args) {

        frame.createMainPage("MDSEV");
    }
}