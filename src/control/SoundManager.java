package control;

import enumeration.Chunk;
import enumeration.Music;
import javafx.scene.media.AudioClip;

public abstract class SoundManager {

    public static boolean isMuted = false;
    public static AudioClip musicGame;
    public static AudioClip musicMenu;
    public static AudioClip chunkSetBomb;
    public static AudioClip chunkExplodeBomb;
    public static AudioClip chuckPickUpItem;
    public static AudioClip chuckLevelComplete;
    public static AudioClip chuckBomberDie;
    public static AudioClip chuckGameOver;
    public static AudioClip chunkGameVictory;
    public static AudioClip chunkNextLevel;

    public static void initSound() {
        chunkSetBomb = new AudioClip(SoundManager.class.getResource("/sounds/set_bomb.wav")
            .toExternalForm());
        chunkExplodeBomb = new AudioClip(SoundManager.class.getResource("/sounds/explode_bomb.wav")
            .toExternalForm());
        chuckPickUpItem = new AudioClip(SoundManager.class.getResource("/sounds/pickUpItem.wav")
            .toExternalForm());
        chuckLevelComplete = new AudioClip(
            SoundManager.class.getResource("/sounds/level_complete.wav")
                .toExternalForm());
        chuckBomberDie = new AudioClip(SoundManager.class.getResource("/sounds/bomberdie.wav")
            .toExternalForm());
        chuckGameOver = new AudioClip(SoundManager.class.getResource("/sounds/game_over.mp3")
            .toExternalForm());
        chunkGameVictory = new AudioClip(SoundManager.class.getResource("/sounds/game_victory.mp3")
            .toExternalForm());
        chunkNextLevel = new AudioClip(SoundManager.class.getResource("/sounds/next_level.mp3")
            .toExternalForm());

        musicGame = new AudioClip(SoundManager.class.getResource("/sounds/FindTheDoor.mp3")
            .toExternalForm());
        musicMenu = new AudioClip(SoundManager.class.getResource("/sounds/menu_screen.mp3")
            .toExternalForm());

        musicGame.setCycleCount(AudioClip.INDEFINITE);
        musicMenu.setCycleCount(AudioClip.INDEFINITE);
    }

    public static void playMusic(Music music) {
        if (isMuted) {
            return;
        }
        switch (music) {
            case MENU:
                musicMenu.play();
                break;
            case GAME:
                musicGame.play();
                break;
        }
    }

    public static void stopMusic() {
        if (musicMenu.isPlaying()) {
            musicMenu.stop();
        }
        if (musicGame.isPlaying()) {
            musicGame.stop();
        }
        if (chunkSetBomb.isPlaying()) {
            chunkSetBomb.stop();
        }
        if (chunkExplodeBomb.isPlaying()) {
            chunkExplodeBomb.stop();
        }
        if (chuckPickUpItem.isPlaying()) {
            chuckPickUpItem.stop();
        }
        if (chuckLevelComplete.isPlaying()) {
            chuckLevelComplete.stop();
        }
        if (chuckBomberDie.isPlaying()) {
            chuckBomberDie.stop();
        }
        if (chuckGameOver.isPlaying()) {
            chuckGameOver.stop();
        }
        if (chunkGameVictory.isPlaying()) {
            chunkGameVictory.stop();
        }
        if (chunkNextLevel.isPlaying()) {
            chunkNextLevel.stop();
        }
    }

    public static void playChunk(Chunk chunk) {
        if (isMuted) {
            return;
        }
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
            case BOMBER_DIE:
                chuckBomberDie.play();
                break;
            case GAME_OVER:
                chuckGameOver.play();
                break;
            case GAME_VICTORY:
                chunkGameVictory.play();
                break;
            case NEXT_LEVEL:
                chunkNextLevel.play();
                break;
        }
    }

    public static void changeStatus() {
        isMuted = !isMuted;
        if (isMuted) {
            stopMusic();
        } else {
            musicGame.play();
        }
    }
}