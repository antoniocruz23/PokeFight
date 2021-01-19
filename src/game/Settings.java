package game;

public class Settings {

    private final int GAME_WIDTH = 800;
    private final int GAME_HEIGHT = 500;
    protected int GAME_TIME = 300;

    private final int PLAYER_D_WIDTH = 200;
    private final int PLAYER_D_HEIGHT = 200;

    private final int PLAYER_WIDTH_LIMIT = 650;
    private final int PLAYER_HEIGHT_LIMIT = 250;

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
}
