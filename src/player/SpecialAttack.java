package player;

import game.Settings;

import java.awt.*;
import java.io.IOException;

public class SpecialAttack extends Sprite {

    private final int SPECIAL_ATTACK_SPEED = 3;
    private Settings settings = new Settings();
    private String attackSprite;


    public SpecialAttack(int x, int y, String attackSprite) throws IOException {
        super(x, y);

        this.attackSprite = attackSprite;

        initSpecialAttack();
    }

    private void initSpecialAttack() throws IOException {

        loadImage(attackSprite);
        getImageDimensions();
        getRect();
    }

    public void moveRight(Player player) {
        x += SPECIAL_ATTACK_SPEED;

        if (x > settings.getGAME_WIDTH()) {
            setVisible(false);
        }

        intersects(player);
    }

    public void moveLeft(Player player) {
        x -= SPECIAL_ATTACK_SPEED;

        if (x < 0) {
            setVisible(false);
        }

        intersects(player);
    }

    public Rectangle getRect() {
        return new Rectangle(x + 10, y + 30, 70, 40);
    }

    public void intersects(Player player) {

        if(getRect().intersects(player.getRect())){
//            System.out.println("touch-----------------------" + x);

            player.setIntersected(true);
            player.getDamage();
            setVisible(false);
        }
//        System.out.println(x + " SPECIAL ATTACK");
    }
}