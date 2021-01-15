import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Sprite {

    private int health = 100;

    private int POSITION_X;
    private int POSITION_Y;
    private final int BOARD_WIDTH = 650;
    private final int BOARD_HEIGHT = 250;

    private final Pokemon pokemon;

    private final String IMAGE_RIGHT;
    private final String IMAGE_LEFT;

    private int PLAYER_SIDE = 1;

    private List<SpecialAttack> specialAttacks;

    public Player(int x, int y, String pokemonName) throws IOException {
        super(x, y);

        pokemon = new Pokemon(pokemonName);

        IMAGE_RIGHT = pokemon.getRightSprite();
        IMAGE_LEFT = pokemon.getLeftSprite();

        initPlayer();
    }

    private void initPlayer() throws IOException {

        specialAttacks = new ArrayList<>();
        loadImage(IMAGE_RIGHT);
        loadImage(IMAGE_LEFT);
        getImageDimensions();
    }

    protected void move() {
        x += POSITION_X;
        if (x > BOARD_WIDTH) {
            x = BOARD_WIDTH;
        }
        if (x < 0) {
            x = 0;
        }

        y += POSITION_Y;
        if (y > BOARD_HEIGHT) {
            y = BOARD_HEIGHT;
        }
        if (y < 5) {
            y = 5;
        }
    }

    private boolean canJump(){
        return y == BOARD_HEIGHT;
    }

    protected List<SpecialAttack> getSpecialAttack() {
        return specialAttacks;
    }

    private void specialAttack() {
        specialAttacks.add(new SpecialAttack(x + width, y + height / 2));
    }

    public int getPLAYER_SIDE() {
        return PLAYER_SIDE;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.health -= damage;
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 150, 170);
    }

    protected void keyPressed(KeyEvent e) throws IOException {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            specialAttack();
            return;
        }

        if (key == KeyEvent.VK_A) {
            POSITION_X = -3;
            loadImage(IMAGE_LEFT);
            PLAYER_SIDE = 0;
            return;
        }

        if (key == KeyEvent.VK_D) {
            POSITION_X = 3;
            loadImage(IMAGE_RIGHT);
            PLAYER_SIDE = 1;
            return;
        }

        if (key == KeyEvent.VK_W && canJump()) {
            POSITION_Y = -5;
            return;
        }

        if (key == KeyEvent.VK_ESCAPE) {
            new Ui();
            return;
        }

        if (key == KeyEvent.VK_5) {
            new Board().saveGame();
        }
    }

    protected void keyPressed2(KeyEvent e) throws IOException {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            POSITION_X = -3;
            loadImage(IMAGE_LEFT);
            PLAYER_SIDE = 0;
            return;
        }

        if (key == KeyEvent.VK_RIGHT) {
            POSITION_X = 3;
            loadImage(IMAGE_RIGHT);
            PLAYER_SIDE = 1;
            return;
        }

        if (key == KeyEvent.VK_UP && canJump()) {
            POSITION_Y = -5;
        }
    }

    protected void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            POSITION_X = 0;
        }

        if (key == KeyEvent.VK_D) {
            POSITION_X = 0;
        }

        if (key == KeyEvent.VK_W) {
            POSITION_Y = 3;
        }
    }

    protected void keyReleased2(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            POSITION_X = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            POSITION_X = 0;
        }

        if (key == KeyEvent.VK_UP) {
            POSITION_Y = 3;
        }
    }
}
