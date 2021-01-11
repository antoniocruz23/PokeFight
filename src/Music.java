import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Music {
    private Clip clip;
    private URL musicURL;

    public Music(String path) {

        initClip(path);
    }

    public void play() {

        clip.start();
    }

    public void stop() {

        clip.stop();
    }

    public void setLoop(int times) {
        clip.loop(times);
    }

    private void initClip(String path) {

        musicURL = Music.class.getResource(path);
        AudioInputStream inputStream = null;

        try {

            if (musicURL == null) {
                path = path.substring(1);
                File file = new File(path);
                musicURL = file.toURI().toURL();
            }

            inputStream = AudioSystem.getAudioInputStream(musicURL);
            clip = AudioSystem.getClip();
            clip.open(inputStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
