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
    private static AudioClip chuckPickUpItem;
    private static AudioClip chuckLevelComplete;
    private static AudioClip chuckWalkBomber;
    private static AudioClip chuckBomberDie;

    public static void playMusic() {
        music = new AudioClip(SoundManager.class.getResource("/sounds/FindTheDoor.mp3").toExternalForm());
        music.setCycleCount(AudioClip.INDEFINITE);
        music.play();
    }

    public static void initSound() {
        chunkSetBomb = new AudioClip(SoundManager.class.getResource("/sounds/set_bomb.wav").toExternalForm());
        chunkExplodeBomb = new AudioClip(SoundManager.class.getResource("/sounds/explode_bomb.wav").toExternalForm());
        chuckPickUpItem = new AudioClip(SoundManager.class.getResource("/sounds/pickUpItem.wav").toExternalForm());
        chuckLevelComplete = new AudioClip(SoundManager.class.getResource("/sounds/level_complete.wav").toExternalForm());
        chuckWalkBomber = new AudioClip(SoundManager.class.getResource("/sounds/walkBomber.mp3").toExternalForm());
        chuckBomberDie = new AudioClip(SoundManager.class.getResource("/sounds/bomberdie.wav").toExternalForm());
    }

    public static void playChunk(Chunk chunk) {
        switch (chunk) {
            case SET_BOMB:
                chunkSetBomb.play();
                break;
            case EXPLODE_BOMB:
                chunkExplodeBomb.play();
                break;
            case PICKUP_ITEM:
                chuckPickUpItem.play();
                break;
            case LEVEL_COMPLETE:
                chuckLevelComplete.play();
                break;
            case WALK_BOMBER:
                chuckWalkBomber.play();
                break;
            case BOMBER_DIE:
                chuckBomberDie.play();
                break;
        }

    }
}
