package game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import player.*;
import player.SpecialAttack;

public class Board extends JPanel implements ActionListener {

    private final int PLAYER1_X = 100;
    private final int PLAYER2_X = 550;

    private boolean ingame = true;
    private final int DELAY = 15;

    private Timer timer;
    private Image image;
    private JLabel timeLabel;

    private int timeOfLoad = 500;
    private final Font timeFont = new Font("Times New Roman", Font.BOLD, 35);
    private final Settings settings = new Settings();

    private Player player;
    private Player player2;


    public Board() throws IOException {

        initBoard();
    }

    private void initBoard() throws IOException {

        addKeyListener(new TAdapter());
        ImageIcon i = new ImageIcon("resources/images/battle.jpeg");
        image = i.getImage();

        setFocusable(true);
        setPreferredSize(new Dimension(settings.getGAME_WIDTH(), settings.getGAME_HEIGHT()));

        player = new Player(PLAYER1_X, settings.getPLAYER_HEIGHT_LIMIT(), "blastoise");
        player2 = new Player(PLAYER2_X, settings.getPLAYER_HEIGHT_LIMIT(), "zapdos");

        timer = new Timer(DELAY, this);
        timer.start();

        gameTime();

        setIngame(true);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (ingame) {

            drawObjects(g);

        } else {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, null);

        healthBar(g2d);

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), settings.getPLAYER_D_WIDTH(), settings.getPLAYER_D_HEIGHT(),this);

        g2d.drawImage(player2.getImage(), player2.getX(), player2.getY(), settings.getPLAYER_D_WIDTH(), settings.getPLAYER_D_HEIGHT(),this);

        java.util.List<SpecialAttack> specialAttacks = player.getSpecialAttack();

        for (SpecialAttack specialAttack : specialAttacks) {

            g2d.drawImage(specialAttack.getImage(), specialAttack.getX(), specialAttack.getY(), this);
            //g2d.drawRect(specialAttack.getX(), specialAttack.getY(), 90, 85);
        }

        //g.drawRect(player.getX(), player.getY(), 150, 170);
        //g.drawRect(player2.getX(), player2.getY(), 150, 170);
    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(font);

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (settings.getGAME_WIDTH() - fm.stringWidth(msg)) / 2, settings.getGAME_HEIGHT() / 2);
        setBackground(Color.black);
        Ui.musicBackground.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateSpecialAttack();
        updatePlayer();

        repaint();
    }

    private void updateSpecialAttack() {

        java.util.List<SpecialAttack> specialAttacks = player.getSpecialAttack();

        for (int i = 0; i < specialAttacks.size(); i++) {

            SpecialAttack specialAttack = specialAttacks.get(i);

            if (specialAttack.isVisible()) {

                specialAttack.moveRight(player2);

                if(!specialAttack.isVisible()) {
                    specialAttacks.remove(i);
                }

            } else {

                specialAttacks.remove(i);
            }
        }
    }

    private void updatePlayer() {

        player.move();
        player2.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W,
                        KeyEvent.VK_ESCAPE, KeyEvent.VK_5 -> {
                    try {
                        player.keyPressed(e);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_ENTER -> {
                    try {
                        player2.keyPressed(e);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

            switch (e.getKeyCode()){
                case KeyEvent.VK_SPACE:
                    player.keyReleased(e);

                case KeyEvent.VK_A:
                    player.keyReleased(e);

                case KeyEvent.VK_D:
                    player.keyReleased(e);

                case KeyEvent.VK_W:
                    player.keyReleased(e);

                case KeyEvent.VK_ESCAPE:
                    player.keyReleased(e);

                case KeyEvent.VK_5:
                    player.keyReleased(e);

                case KeyEvent.VK_LEFT:
                    player2.keyReleased(e);

                case KeyEvent.VK_RIGHT:
                    player2.keyReleased(e);

                case KeyEvent.VK_UP:
                    player2.keyReleased(e);

                case KeyEvent.VK_ENTER:
                    player2.keyReleased(e);
            }
        }
    }

    private void healthBar(Graphics2D g2d){

        g2d.setColor(Color.blue);
        g2d.fillRect(40, 27, settings.getHEALTH_BAR_PLAYER1(), 15);
        g2d.fillRect(500, 27, settings.getHEALTH_BAR_PLAYER2(),15);

        if(player.isIntersected()){
            settings.setHEALTH_BAR_PLAYER1();
            player.setIntersected(false);
        }

        if(player2.isIntersected()){
            settings.setHEALTH_BAR_PLAYER2();
            player2.setIntersected(false);
        }
    }

    private void gameTime(){
        timeLabel = new JLabel();
        timeLabel.setFont(timeFont);
        timeLabel.setForeground(Color.white);
        add(timeLabel);

        timer = new Timer(500, e -> {
            settings.GAME_TIME--;
            if (settings.getGAME_TIME() > 0) {
                timeLabel.setText(Integer.toString(settings.getGAME_TIME()));

            } else {
                ((Timer) (e.getSource())).stop();
                timeLabel.setText("");
                ingame = false;
            }
            timeOfLoad = settings.getGAME_TIME();
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public int getTimeOfLoad() {
        return timeOfLoad;
    }

    public void saveGame() {
        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter("gameSave.txt"));

            bw.write("" + player.getHealth());
            bw.newLine();
            bw.write("" + player2.getHealth());
            bw.newLine();
            bw.write("" + getTimeOfLoad());

            bw.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try{
            BufferedReader br = new BufferedReader(new FileReader("gameSave.txt"));

            String line;
            int count = 0;

            while ((line =  br.readLine()) != null){

                count++;
                switch (count) {
                    case 1 -> player.setHealth(Integer.parseInt(line));
                    case 2 -> player2.setHealth(Integer.parseInt(line));
                    case 3 -> settings.GAME_TIME = Integer.parseInt(line);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setIngame(boolean ingame) {
        this.ingame = ingame;
    }
}