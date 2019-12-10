package scyjc1.frogger.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The background music of the game.
 * Singleton pattern applied.
 */
public class BackgroundMusic {
	private static BackgroundMusic bgm;
	private static final MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/sounds/theme.mp3").toURI().toString()));

	/**
	 * Private constructor in case of careless use.
	 */
	private BackgroundMusic() {}

	/**
	 * Gets the single {@link BackgroundMusic} instance.
	 *
	 * @return the single {@link BackgroundMusic} instance.
	 */
	public static BackgroundMusic getBgm() {
		if (bgm == null) {
			bgm = new BackgroundMusic();
		}
		return bgm;
	}

	/**
	 * Plays or resumes the background music.
	 */
	public void play() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
		mediaPlayer.setVolume(0.5);
	}

	/**
	 * Pauses the background music.
	 */
	public void pause() {
		mediaPlayer.pause();
	}

	/**
	 * Stops the background music.
	 */
	public void stop() {
		mediaPlayer.stop();
	}

	/**
	 * Mutes the background music.
	 */
	public void mute() {
		mediaPlayer.setMute(true);
	}

	/**
	 * Unmutes the background music.
	 */
	public void unmute() {
		mediaPlayer.setMute(false);
	}
}
