package frogger.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The background music of the game.
 * Singleton pattern applied.
 */
public class BackgroundMusic {
	private static BackgroundMusic bgm;
	private MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/sounds/theme.mp3").toURI().toString()));
	private boolean enabled = true;

	/**
	 * Private constructor in case of careless use.
	 */
	private BackgroundMusic() {
	}

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
	 * Checks whether the background music is globally enabled.
	 *
	 * @return whether the background music is globally enabled as a boolean.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * Toggles the global enabling status of background music.
	 */
	public void toggleEnabled() {
		enabled = !enabled;
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
