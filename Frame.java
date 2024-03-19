import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

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

        JTextArea compInfo = new JTextArea(CompSchedule.get4541CompSchedule(eventID));

        JPanel panel = new JPanel(null) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                GradientPaint gradient = new GradientPaint(100, 100, new Color(45, 45, 45), getWidth(), getHeight(), new Color(45, 45, 45));

                ((Graphics2D) g).setPaint(gradient);

                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setVisuals(label1, label2, label3, compInfo);
        setPositions(label1, label2, label3, compInfo);
        addComponents(label1, label2, label3, compInfo, panel, frame);

        while (true) {
            label3.setText(Clock.getCurrentTime());
            compInfo.setText(CompSchedule.get4541CompSchedule(eventID));
        }
    }

    public void addComponents(JLabel label1, JLabel label2, JLabel label3, JTextArea compInfo, JPanel panel, JFrame frame) {

        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(compInfo);

        frame.add(panel);

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setVisuals(JLabel label1, JLabel label2, JLabel label3, JTextArea compInfo) {

        Font introFont = new Font("Trebuchet MS", Font.BOLD, 30);
        Font clockFont = new Font("Trebuchet MS", Font.BOLD, 50);
        Font compInfoFont = new Font("Trebuchet MS", Font.BOLD, 20);

        label1.setFont(introFont);
        label2.setFont(introFont);
        label3.setFont(clockFont);
        compInfo.setFont(compInfoFont);

        label1.setForeground(Color.WHITE);
        label2.setForeground(Color.WHITE);
        label3.setForeground(Color.WHITE);
        compInfo.setForeground(Color.WHITE);

        compInfo.setBackground(Color.DARK_GRAY);
        compInfo.setBorder(new LineBorder(Color.WHITE, 2));
    }

    public void setPositions(JLabel label1, JLabel label2, JLabel label3, JTextArea compInfo) {

        label1.setBounds(560, 0, 750, 30);
        label2.setBounds(600, 30, 750, 30);
        label3.setBounds(700, 60, 500, 50);
        compInfo.setBounds(50, 120, 1600, 600);
    }
}