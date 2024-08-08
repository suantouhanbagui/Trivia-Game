package main2;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;


public class AdditionalFeatureMusic {
    public static void playSound(String fileName) {
        try {
            // Load the sound file from the classpath
            URL soundURL = AdditionalFeatureMusic.class.getResource(fileName);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + fileName);
                return;
            }

            // Get an audio input stream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL);

            // Get a clip resource
            Clip clip = AudioSystem.getClip();

            // Open the clip with the audio input stream
            clip.open(audioInputStream);

            // Loop the main2.music
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            // Start playing the sound
            clip.start();

            // Optional: wait for the sound to finish playing
            Thread.sleep(clip.getMicrosecondLength());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
