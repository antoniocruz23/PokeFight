package game;

public class Settings {

    private String PLAYER1_POKEMON;
    private String PLAYER2_POKEMON;
    private String PLAYER1_DEFAULT = "blastoise";
    private String PLAYER2_DEFAULT = "zapdos";
    private int PLAYER1_HEALTH = 250;
    private int PLAYER2_HEALTH = 250;

    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 500;
    public int GAME_TIME = 150;

    private final int PLAYER_D_WIDTH = 200;
    private final int PLAYER_D_HEIGHT = 200;

    private final int PLAYER_WIDTH_LIMIT = 650;
    private final int PLAYER_HEIGHT_LIMIT = 250;

    private final int PLAYER1_INIT_X = 80;
    private final int PLAYER2_INIT_X = 550;

    private int HEALTH_BAR_PLAYER1 = 250;
    private int HEALTH_BAR_PLAYER2 = 250;

    private int SPECIAL_ATTACK_DAMAGE = 20;

    private final String UI_BACKGROUND_COLOR = "#F5A53C";

    public int getGAME_WIDTH() {
        return GAME_WIDTH;
    }

    public int getGAME_HEIGHT() {
        return GAME_HEIGHT;
    }

    public int getGAME_TIME() {
        return GAME_TIME;
    }

    public int getPLAYER_D_WIDTH() {
        return PLAYER_D_WIDTH;
    }

    public int getPLAYER_D_HEIGHT() {
        return PLAYER_D_HEIGHT;
    }

    public int getPLAYER_WIDTH_LIMIT() {
        return PLAYER_WIDTH_LIMIT;
    }

    public int getPLAYER_HEIGHT_LIMIT() {
        return PLAYER_HEIGHT_LIMIT;
    }

    public String getUI_BACKGROUND_COLOR() {
        return UI_BACKGROUND_COLOR;
    }

    public int getHEALTH_BAR_PLAYER1() {
        return HEALTH_BAR_PLAYER1;
    }

    public void setHEALTH_BAR_PLAYER1() {
        this.HEALTH_BAR_PLAYER1 -= getSPECIAL_ATTACK_DAMAGE();
    }

    public int getHEALTH_BAR_PLAYER2() {
        return HEALTH_BAR_PLAYER2;
    }

    public void setHEALTH_BAR_PLAYER2() {
        this.HEALTH_BAR_PLAYER2 -= getSPECIAL_ATTACK_DAMAGE();
    }

    public int getSPECIAL_ATTACK_DAMAGE() {
        return SPECIAL_ATTACK_DAMAGE;
    }

    public int getPLAYER1_INIT_X() {
        return PLAYER1_INIT_X;
    }

    public int getPLAYER2_INIT_X() {
        return PLAYER2_INIT_X;
    }

    public String getPLAYER1_POKEMON() {
        return PLAYER1_POKEMON;
    }

    public String getPLAYER2_POKEMON() {
        return PLAYER2_POKEMON;
    }

    public void setPLAYER1_POKEMON(String PLAYER1_POKEMON) {
        this.PLAYER1_POKEMON = PLAYER1_POKEMON;
    }

    public void setPLAYER2_POKEMON(String PLAYER2_POKEMON) {
        this.PLAYER2_POKEMON = PLAYER2_POKEMON;
    }

    public void setHEALTH_BAR_P1_LOAD(int health){
        this.HEALTH_BAR_PLAYER1 = health;
    }

    public void setHEALTH_BAR_P2_LOAD(int health){
        this.HEALTH_BAR_PLAYER2 = health;
    }

    public String getPLAYER1_DEFAULT() {
        return PLAYER1_DEFAULT;
    }

    public String getPLAYER2_DEFAULT() {
        return PLAYER2_DEFAULT;
    }

    public int getPLAYER1_HEALTH() {
        return PLAYER1_HEALTH;
    }

    public int getPLAYER2_HEALTH() {
        return PLAYER2_HEALTH;
    }

    public void setPLAYER1_HEALTH(int PLAYER1_HEALTH) {
        this.PLAYER1_HEALTH = PLAYER1_HEALTH;
    }

    public void setPLAYER2_HEALTH(int PLAYER2_HEALTH) {
        this.PLAYER2_HEALTH = PLAYER2_HEALTH;
    }
}
