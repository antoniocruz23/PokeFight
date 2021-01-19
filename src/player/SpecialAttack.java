package player;

import game.Settings;

import java.awt.*;

public class SpecialAttack extends Sprite {

    private final int SPECIAL_ATTACK_SPEED = 3;
    private Settings settings = new Settings();


    public SpecialAttack(int x, int y) {
        super(x, y);

        initSpecialAttack();
    }

    private void initSpecialAttack() {

        specialAttackImage("resources/images/blue-fireball.gif");
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

    public void moveLeft() {
        x -= SPECIAL_ATTACK_SPEED;

        if (x > settings.getGAME_WIDTH()) {
            setVisible(false);
        }
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, 90, 85);
    }

    public void intersects(Player player) {

        if(getRect().intersects(player.getRect())){
            System.out.println("touch-----------------------" + x);

            player.setIntersected(true);
            player.setDamage();
            setVisible(false);
        }
        System.out.println(x + " SPECIAL ATTACK");
    }
}