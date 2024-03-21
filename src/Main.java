package src;
// Championship event id - CHCMP
// Ashland VA event id - VAASH
// Blacksburg VA event id - VABLA
// District Falls VA event id - VAFAL
// Glen Allen VA event id - VAGLE
// Portsmouth VA event id - VAPOR
// Spalding event id - MDSEV
// McDonogh event id - MDOWI

public class Main {

    public static Frame frame = new Frame();
    public static void main(String[] args) {

        // Enter event ID and team number to use the app
        // Event ID is the state abbrieviation and the first 3 letter of the locality
        frame.createMainPage("MDSEV", "4541");
    }
}