import java.net.MalformedURLException;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        new Ui();
        Pokemon pokemon = new Pokemon("chrizard");

        pokemon.get();
    }
}
