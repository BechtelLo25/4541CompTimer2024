package src;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;

import util.Clock;
import webpulls.CompSchedule;
import webpulls.EventTeams;
import webpulls.FirstInfo;
import webpulls.Ranking;

public class Frame {

    public static FirstInfo firstInfo = new FirstInfo();
    public static EventTeams eventTeams = new EventTeams();
    public Clock clock = new Clock();
    public CompSchedule compSchedule = new CompSchedule();
    public Ranking ranking = new Ranking();

    public void createMainPage(String eventID, String teamNum) {

        int updateFrequency = 0;

        JFrame frame = new JFrame("4541 Comp Timer");

        JLabel welcome = new JLabel("Welcome to the 4541 competition timer!");
        JLabel currentSeasonInfo = new JLabel("Current Season: " + firstInfo.getCurrentGameName() + " - " + firstInfo.getCurrentSeason());
        JLabel clock = new JLabel();

        JTextArea compInfo = new JTextArea(CompSchedule.getCompSchedule(eventID, teamNum));

        JLabel rankingInfo = new JLabel(Ranking.getRanking(eventID, teamNum));

        JLabel queueStatus = new JLabel(CompSchedule.getIfQueuing(eventID));
        JLabel githubLink = new JLabel("https://github.com/BechtelLo25/4541CompTimer2024");

        JPanel panel = new JPanel(null) {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                GradientPaint gradient = new GradientPaint(100, 100, new Color(45, 45, 45), getWidth(), getHeight(), new Color(45, 45, 45));

                ((Graphics2D) g).setPaint(gradient);

                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setVisuals(welcome, currentSeasonInfo, clock, compInfo, rankingInfo, queueStatus, githubLink);
        setPositions(welcome, currentSeasonInfo, clock, compInfo, rankingInfo, queueStatus, githubLink);
        addComponents(welcome, currentSeasonInfo, clock, compInfo, panel, frame, rankingInfo, queueStatus, githubLink);

        while (true) {
            updateFrequency++; 

            clock.setText(Clock.getCurrentTime());

            if(updateFrequency == 2000000) {
                compInfo.setText(CompSchedule.getCompSchedule(eventID, teamNum));
                rankingInfo.setText(Ranking.getRanking(eventID, teamNum));
                queueStatus.setText(CompSchedule.getIfQueuing(eventID));
                updateFrequency = 0;
            }
            
        }
    }

    public void addComponents(JLabel welcome, JLabel currentSeasonInfo, JLabel clock, JTextArea compInfo, JPanel panel, JFrame frame, JLabel rankingInfo, JLabel queueStatus, JLabel githubLink) {

        panel.add(welcome);
        panel.add(currentSeasonInfo);
        panel.add(clock);
        panel.add(compInfo);
        panel.add(rankingInfo);
        panel.add(queueStatus);
        panel.add(githubLink);

        compInfo.setFocusTraversalKeysEnabled(false);

        frame.add(panel);

        frame.setSize(1920, 1080);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setVisuals(JLabel welcome, JLabel currentSeasonInfo, JLabel clock, JTextArea compInfo, JLabel rankingInfo, JLabel queueStatus, JLabel githubLink) {

        Font introFont = new Font("Trebuchet MS", Font.BOLD, 30);
        Font clockFont = new Font("Trebuchet MS", Font.BOLD, 50);
        Font compInfoFont = new Font("Trebuchet MS", Font.BOLD, 16);
        Font queueFont = new Font("Trebuchet MS", Font.BOLD, 75);
        Font githubLinkFont = new Font("Trebuchet MS", Font.BOLD, 18);

        welcome.setFont(introFont);
        currentSeasonInfo.setFont(introFont);
        clock.setFont(clockFont);
        compInfo.setFont(compInfoFont);
        rankingInfo.setFont(introFont);
        queueStatus.setFont(queueFont);
        githubLink.setFont(githubLinkFont);

        welcome.setForeground(Color.WHITE);
        currentSeasonInfo.setForeground(Color.WHITE);
        clock.setForeground(Color.WHITE);
        compInfo.setForeground(Color.WHITE);
        rankingInfo.setForeground(Color.WHITE);
        queueStatus.setForeground(Color.WHITE);
        githubLink.setForeground(Color.WHITE);

        compInfo.setBackground(Color.DARK_GRAY);
        compInfo.setBorder(new LineBorder(Color.WHITE, 2));
    }

    public void setPositions(JLabel welcome, JLabel currentSeasonInfo, JLabel clock, JTextArea compInfo, JLabel rankingInfo, JLabel queueStatus, JLabel githubLink) {

        welcome.setBounds(560, 0, 750, 30);
        currentSeasonInfo.setBounds(600, 30, 750, 30);
        clock.setBounds(700, 60, 500, 50);
        compInfo.setBounds(15, 120, 1670, 600);
        rankingInfo.setBounds(50, 750, 1250, 40);
        queueStatus.setBounds(1225, 728, 1250, 90);
        githubLink.setBounds(25, 75, 500, 50);

        compInfo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume(); // Consume the key event
            }
        });
    }
}