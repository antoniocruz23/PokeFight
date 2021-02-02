package player;

import game.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player extends Sprite {

    private int health = 250;

    private int POSITION_X;
    private int POSITION_Y;
    private final Settings settings = new Settings();
    private boolean intersected = false;

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

    public void move() {
        x += POSITION_X;
        if (x > settings.getPLAYER_WIDTH_LIMIT()) {
            x = settings.getPLAYER_WIDTH_LIMIT();
        }
        if (x < 0) {
            x = 0;
        }

        y += POSITION_Y;
        if (y > settings.getPLAYER_HEIGHT_LIMIT()) {
            y = settings.getPLAYER_HEIGHT_LIMIT();
        }
        if (y < 5) {
            y = 5;
        }
    }

    private boolean canJump(){
        return y == settings.getPLAYER_HEIGHT_LIMIT();
    }

    public List<SpecialAttack> getSpecialAttack() {
        return specialAttacks;
    }

    public void specialAttack(String attackSprite) {
        specialAttacks.add(new SpecialAttack(x + width, y + height / 2, attackSprite));
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

    public void getDamage() {
        this.health -= settings.getSPECIAL_ATTACK_DAMAGE();
    }

    public Rectangle getRect() {
        return new Rectangle(x + 50, y + 30, 100, 130);
    }

    public void keyPressed(KeyEvent e) throws IOException {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE && getPLAYER_SIDE() == 1) {
            specialAttack("resources/images/blue-fireball-p1.gif");
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
            System.exit(0);
            return;
        }

        if (key == KeyEvent.VK_5) {
            return;
        }

        /* PLAYER 2 CONTROLLER */

        if (key == KeyEvent.VK_ENTER && getPLAYER_SIDE() == 0) {
            specialAttack("resources/images/blue-fireball-p2.gif");
            return;
        }

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

    public void keyReleased(KeyEvent e) {

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

    public void setIntersected(boolean intersected) {
        this.intersected = intersected;
    }

    public boolean isIntersected() {
        return intersected;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }
}
