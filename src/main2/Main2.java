package main2;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.net.URL;

public class Main2 {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        AdditionalFeatureMusic.playSound("\\z_additional_features\\adventure_game_music.wav");
    }
}
