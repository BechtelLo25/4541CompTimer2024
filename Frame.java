import java.awt.*;

import javax.swing.*;

public class Frame {

    public static FirstInfo firstInfo = new FirstInfo();
    public static EventTeams eventTeams = new EventTeams();
    public Clock clock = new Clock();
    public CompSchedule compSchedule = new CompSchedule();

    public void createMainPage(String eventID) {

        JFrame frame = new JFrame("4541 Comp Timer");

        JLabel label1 = new JLabel("Welcome to the 4541 competition timer!");
        JLabel label2 = new JLabel("Current Season: " + firstInfo.getCurrentGameName() + " - " + firstInfo.getCurrentSeason());
        JLabel label3 = new JLabel();

        JTextArea compInfo = new JTextArea(compSchedule.get4541CompSchedule(eventID));

        Font customFont = new Font("Trebuchet MS", Font.BOLD, 20);
        label1.setFont(customFont);
        label2.setFont(customFont);
        label3.setFont(customFont);
        compInfo.setFont(customFont);

        label1.setBounds(750, 0, 500, 30); 
        label2.setBounds(780, 20, 500, 30);
        label3.setBounds(880, 40, 500, 30); 
        compInfo.setBounds(50, 70, 1600, 600); 


        JPanel panel = new JPanel(null);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(compInfo);

        frame.add(panel);

        frame.setSize(1920, 1080);

        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while(true) {
            label3.setText(Clock.getCurrentTime());
        }
    }
}