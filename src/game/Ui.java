package game;

import music.Music;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Ui extends JFrame {

    private JLabel titleLabel;
    private JPanel titlePanel;
    private JPanel buttonPanel;
    private JButton startButton;
    private JButton loadButton;
    private final Settings settings = new Settings();

    static Music musicBackground = new Music("/resources/music/music.midi");
    private final Music buttonSound = new Music("/resources/music/button.wav");

    private final Font titleFont = new Font("Times New Roman", Font.PLAIN, 90);
    private final Font normalFont = new Font("Times New Roman", Font.PLAIN, 30);


    public void start() {
        createWindow();
    }

    private void createWindow() {
        setSize(settings.getGAME_WIDTH(), settings.getGAME_HEIGHT());
        setResizable(false);
        setTitle("Poke Fight");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //musicBackground.play();
        //musicBackground.setLoop(10);
        createUI();
    }

    private void createUI() {

        titlePanel = new JPanel();
        titlePanel.setBounds(100, 100, 600, 120);
        titlePanel.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));

        titleLabel = new JLabel("POKE FIGHT");
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(titleFont);

        buttonPanel = new JPanel();
        buttonPanel.setBounds(300, 300, 200, 60);
        buttonPanel.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));

        startButton = new JButton("START");
        startButton.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));
        startButton.setForeground(Color.white);
        startButton.setFont(normalFont);
        startButton.setOpaque(true);
        startButton.setBorderPainted(false);
        startButton.addActionListener(startGame);

        loadButton = new JButton("LOAD");
        loadButton.setBackground(Color.decode(settings.getUI_BACKGROUND_COLOR()));
        loadButton.setForeground(Color.white);
        loadButton.setFont(normalFont);
        loadButton.setOpaque(true);
        loadButton.setBorderPainted(false);
        loadButton.addActionListener(loadGame);

        GridBagConstraints gbcStart = new GridBagConstraints();
        gbcStart.ipadx = 60;
        gbcStart.ipady = 10;
        gbcStart.insets = new Insets(250, 200, 0, 0);
        gbcStart.weightx = 1;
        gbcStart.weighty = 1;
        gbcStart.anchor = GridBagConstraints.NORTHWEST;

        GridBagConstraints gbcLoad = new GridBagConstraints();
        gbcLoad.ipadx = 60;
        gbcLoad.ipady = 10;
        gbcLoad.insets = new Insets(250, 0, 0, 150);
        gbcLoad.weightx = 1;
        gbcLoad.weighty = 1;
        gbcLoad.anchor = GridBagConstraints.NORTHWEST;

        titlePanel.add(titleLabel);
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.add(startButton, gbcStart);
        buttonPanel.add(loadButton, gbcLoad);

        add(titlePanel);
        add(buttonPanel);

        setVisible(true);
    }

    ActionListener startGame = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            titlePanel.setVisible(false);
            buttonPanel.setVisible(false);
            //buttonSound.play();

            try {
                add(new Board());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    ActionListener loadGame = new ActionListener() {
        public void actionPerformed(ActionEvent actionEvent) {
            titlePanel.setVisible(false);
            buttonPanel.setVisible(false);
            //buttonSound.play();

            Board board = null;
            try {
                board = new Board();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert board != null;
            board.loadGame();
            add(board);
        }
    };
}