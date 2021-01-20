package game;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import player.*;
import player.SpecialAttack;

public class Board extends JPanel implements ActionListener {

    private boolean ingame = true;
    private final int DELAY = 15;

    private Timer timer;
    private Image image;
    private JLabel timeLabel;

    private int timeOfLoad = 500;
    private final Font timeFont = new Font("Times New Roman", Font.BOLD, 35);
    private final Settings settings = new Settings();

    private Player player1;
    private Player player2;

    private SpecialAttack specialAttack1;
    private SpecialAttack specialAttack2;


    public Board() throws IOException {

        initBoard();
    }

    private void initBoard() throws IOException {

        addKeyListener(new TAdapter());
        ImageIcon i = new ImageIcon("resources/images/battle.jpeg");
        image = i.getImage();

        setFocusable(true);
        setPreferredSize(new Dimension(settings.getGAME_WIDTH(), settings.getGAME_HEIGHT()));

        player1 = new Player(settings.getPLAYER1_INIT_X(), settings.getPLAYER_HEIGHT_LIMIT(), settings.getPLAYER1_POKEMON());
        player2 = new Player(settings.getPLAYER2_INIT_X(), settings.getPLAYER_HEIGHT_LIMIT(), settings.getPLAYER2_POKEMON());

        timer = new Timer(DELAY, this);
        timer.start();

        gameTime();

        setIngame(true);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (ingame){

            drawObjects(g);

        } if (settings.getGAME_TIME() == 0 || player1.getHealth() <= 0 || player2.getHealth() <= 0) {

            drawGameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image, 0, 0, null);

        healthBar(g2d);

        g2d.drawImage(player1.getImage(), player1.getX(), player1.getY(), settings.getPLAYER_D_WIDTH(), settings.getPLAYER_D_HEIGHT(),this);

        g2d.drawImage(player2.getImage(), player2.getX(), player2.getY(), settings.getPLAYER_D_WIDTH(), settings.getPLAYER_D_HEIGHT(),this);

        java.util.List<SpecialAttack> specialAttacks = player1.getSpecialAttack();
        java.util.List<SpecialAttack> specialAttacks2 = player2.getSpecialAttack();

        for (SpecialAttack specialAttack : specialAttacks) {

            g2d.drawImage(specialAttack.getImage(), specialAttack.getX(), specialAttack.getY(), this);
            g2d.drawRect(specialAttack.getX() + 10, specialAttack.getY() + 30, 70, 40);
        }
        for (SpecialAttack specialAttack2 : specialAttacks2) {

            g2d.drawImage(specialAttack2.getImage(), specialAttack2.getX(), specialAttack2.getY(), this);
            g2d.drawRect(specialAttack2.getX() + 10, specialAttack2.getY() + 30, 70, 40);
        }

        g.drawRect(player1.getX() + 50, player1.getY() + 30, 100, 130);
        g.drawRect(player2.getX() + 50, player2.getY() + 30, 100, 130);
    }

    private void drawGameOver(Graphics g) {
        String msg;
        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fm = getFontMetrics(font);

        setBackground(Color.black);
        setIngame(false);
        timeLabel.setVisible(false);
        Ui.musicBackground.stop();

        if(player1.getHealth() <= 0){
            msg = player2.getPokemon().getPokeName().toUpperCase() + " WIN!!";

        }
        else if(player2.getHealth() <= 0){
            msg = player1.getPokemon().getPokeName().toUpperCase() + " WIN!!";

        } else {
            msg = "Game Over";
        }

        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg, (settings.getGAME_WIDTH() - fm.stringWidth(msg)) / 2, settings.getGAME_HEIGHT() / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        updateSpecialAttack(player1, player2);
        updateSpecialAttack(player2, player1);
        updatePlayer();

        repaint();
    }

    private void updateSpecialAttack(Player playerAttack, Player playerDamage) {

        java.util.List<SpecialAttack> specialAttacks = playerAttack.getSpecialAttack();

        for (int i = 0; i < specialAttacks.size(); i++) {

            SpecialAttack specialAttack = specialAttacks.get(i);

            if (specialAttack.isVisible()) {

                specialAttack.moveRight(playerDamage);

                if(!specialAttack.isVisible()) {
                    specialAttacks.remove(i);
                }

            } else {

                specialAttacks.remove(i);
            }
        }
    }

    private void updatePlayer() {

        player1.move();
        player2.move();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {
                case KeyEvent.VK_SPACE, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W,
                        KeyEvent.VK_ESCAPE, KeyEvent.VK_5 -> {
                    try {
                        player1.keyPressed(e);
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
                    player1.keyReleased(e);

                case KeyEvent.VK_A:
                    player1.keyReleased(e);

                case KeyEvent.VK_D:
                    player1.keyReleased(e);

                case KeyEvent.VK_W:
                    player1.keyReleased(e);

                case KeyEvent.VK_ESCAPE:
                    player1.keyReleased(e);

                case KeyEvent.VK_5:
                    player1.keyReleased(e);

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

        if(player1.isIntersected()){
            settings.setHEALTH_BAR_PLAYER1();
            player1.setIntersected(false);
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

        timer = new Timer(1000, e -> {
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

            bw.write("" + player1.getHealth());
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
                    case 1 -> player1.setHealth(Integer.parseInt(line));
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