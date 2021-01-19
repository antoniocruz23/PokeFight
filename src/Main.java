import game.Ui;
import player.Pokemon;
import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        Ui ui = new Ui();
        ui.start();

        Pokemon pokemon = new Pokemon("chrizard");
        pokemon.get();
    }
}
