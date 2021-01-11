import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import javax.swing.*;

public class Board extends JPanel implements ActionListener {

    private final int PLAYER1_X = 100;
    private final int PLAYER2_X = 550;
    private final int PLAYER_Y = 250;

    private final int PLAYER_WIDTH = 200;
    private final int PLAYER_HEIGHT = 200;

    private final int B_WIDTH = 800;
    private final int B_HEIGHT = 500;

    private boolean ingame = true;
    private final int DELAY = 15;

    private Timer timer;
    private Image image;
    private JLabel timeLabel;
    private int timeCount = 300;
    private int currentTime = 500;
    private final Font timeFont = new Font("Times New Roman", Font.BOLD, 35);

    protected Player player;
    protected Player player2;


    public Board() {

        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        ImageIcon i = new ImageIcon("resources/images/battle.jpeg");
        image = i.getImage();

        setFocusable(true);
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        player = new Player(PLAYER1_X, PLAYER_Y, "blastoise");
        player2 = new Player(PLAYER2_X, PLAYER_Y, "zapdos");

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

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), PLAYER_WIDTH, PLAYER_HEIGHT,this);

        g2d.drawImage(player2.getImage(), player2.getX(), player2.getY(), PLAYER_WIDTH, PLAYER_HEIGHT,this);

        List<SpecialAttack> specialAttacks = player.getSpecialAttack();

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
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
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

        List<SpecialAttack> specialAttacks = player.getSpecialAttack();

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
                        KeyEvent.VK_ESCAPE, KeyEvent.VK_5 -> player.keyPressed(e);

                case KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP -> player2.keyPressed2(e);
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
                    player2.keyReleased2(e);

                case KeyEvent.VK_RIGHT:
                    player2.keyReleased2(e);

                case KeyEvent.VK_UP:
                    player2.keyReleased2(e);
            }
        }
    }

    private void healthBar(Graphics2D g2d){

        if(player.getHealth() == 100) {
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 100) * 250, 15);

        }
        if(player.getHealth() == 90){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 90) * 228, 15);

        }
        if(player.getHealth() == 80){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 80) * 206, 15);

        }
        if(player.getHealth() == 70){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 70) * 184, 15);

        }
        if(player.getHealth() == 60){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 60) * 162, 15);

        }
        if(player.getHealth() == 50){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 50) * 140, 15);

        }
        if(player.getHealth() == 40){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 40) * 118, 15);

        }
        if(player.getHealth() == 30){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 30) * 96, 15);

        }
        if(player.getHealth() == 20){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 20) * 74, 15);

        }
        if(player.getHealth() == 10){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth() / 10) * 52, 15);

        }
        if(player.getHealth() <= 0){
            g2d.setColor(Color.blue);
            g2d.fillRect(40,27, (player.getHealth()) * 30, 15);

        }

        if(player2.getHealth() == 100) {
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 100) * 250, 15);

        }
        if(player2.getHealth() == 90){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 90) * 228, 15);

        }
        if(player2.getHealth() == 80){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 80) * 206, 15);

        }
        if(player2.getHealth() == 70){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 70) * 184, 15);

        }
        if(player2.getHealth() == 60){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 60) * 162, 15);

        }
        if(player2.getHealth() == 50){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 50) * 140, 15);

        }
        if(player2.getHealth() == 40){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 40) * 118, 15);

        }
        if(player2.getHealth() == 30){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 30) * 96, 15);

        }
        if(player2.getHealth() == 20){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 20) * 74, 15);

        }
        if(player2.getHealth() == 10){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth() / 10) * 52, 15);

        }
        if(player2.getHealth() <= 0){
            g2d.setColor(Color.blue);
            g2d.fillRect(500, 27, (player2.getHealth()) * 30, 15);

        }
    }

    private void gameTime(){
        timeLabel = new JLabel();
        timeLabel.setFont(timeFont);
        timeLabel.setForeground(Color.white);
        add(timeLabel);

        timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeCount--;
                if (timeCount > 0) {
                    timeLabel.setText(Integer.toString(timeCount));

                } else {
                    ((Timer) (e.getSource())).stop();
                    timeLabel.setText("");
                    ingame = false;
                }
                currentTime = timeCount;
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void saveGame() {
        try{

            BufferedWriter bw = new BufferedWriter(new FileWriter("gameSave.txt"));

            bw.write("" + player.getHealth());
            bw.newLine();
            bw.write("" + player2.getHealth());
            bw.newLine();
            bw.write("" + getCurrentTime());

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
                    case 3 -> this.timeCount = Integer.parseInt(line);
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