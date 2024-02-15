package wit.comp1050.mastermind;
import java.io.IOException;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.util.Scanner;

public class Audio {

    public static void playAudio(String fileName) {
        Scanner input = new Scanner(System.in);
        AudioInputStream audioIn;
        try {
            audioIn = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResourceAsStream(fileName));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException  e) {
            e.printStackTrace();
        }
    }

}