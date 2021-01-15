import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Pokemon {

    private BufferedReader reader;
    private HttpURLConnection connection;
    private StringBuffer responseContent = new StringBuffer();
    private String line;

    private String LEFT_SPRITE;
    private String RIGHT_SPRITE;
    private String pokemonName;

    public Pokemon(String pokeName) throws MalformedURLException {
        this.pokemonName = pokeName;
        get();
    }

    public void get() throws MalformedURLException {

        URL url = new URL("https://pokeapi.co/api/v2/pokemon/" + this.pokemonName + "/");

        try{
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                line = reader.readLine();
                line = line.substring(line.indexOf("base_experience"));

                LEFT_SPRITE = line.substring(line.indexOf("front_default") + 16, line.indexOf("\"front_female") - 2);
                RIGHT_SPRITE = line.substring(line.indexOf("back_default") + 15, line.indexOf("\"back_female") - 2);
                pokemonName = line.substring(line.indexOf("name") + 7, line.indexOf("\"url") - 2);

                System.out.println(LEFT_SPRITE);
                System.out.println(RIGHT_SPRITE);
                System.out.println(pokemonName);

            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLeftSprite(){
        return LEFT_SPRITE;
    }

    public String getRightSprite(){
        return RIGHT_SPRITE;
    }

    public String getPokeName() {
        return this.pokemonName;
    }
}
