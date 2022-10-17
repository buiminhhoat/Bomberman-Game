package control;

import enumeration.Chunk;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import javafx.stage.Stage;

public abstract class SoundManager {
    private static AudioClip music;
    private static AudioClip chunkSetBomb;
    private static AudioClip chunkExplodeBomb;

    public static void playMusic() {
        music = new AudioClip(SoundManager.class.getResource("/sounds/FindTheDoor.mp3").toExternalForm());
        music.setCycleCount(AudioClip.INDEFINITE);
        music.play();
    }

    public static void initSound() {
        chunkSetBomb = new AudioClip(SoundManager.class.getResource("/sounds/set_bomb.wav").toExternalForm());
        chunkExplodeBomb = new AudioClip(SoundManager.class.getResource("/sounds/explode_bomb.wav").toExternalForm());
    }

    public static void playChunk(Chunk chunk) {
        switch (chunk) {
            case SET_BOMB:
                chunkSetBomb.play();
                break;
            case EXPLODE_BOMB:
                chunkExplodeBomb.play();
                break;
        }

    }
}
