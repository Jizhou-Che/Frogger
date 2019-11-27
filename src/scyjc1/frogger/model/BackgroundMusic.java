package scyjc1.frogger.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The background music controller.
 * Singleton pattern applied.
 */
public class BackgroundMusic {
	private static BackgroundMusic bgm;
	private static final MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/sounds/theme.mp3").toURI().toString()));

	private BackgroundMusic() {}

	public static BackgroundMusic getBgm() {
		if (bgm == null) {
			bgm = new BackgroundMusic();
		}
		return bgm;
	}

	public void play() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	public void pause() {
		mediaPlayer.pause();
	}

	public void stop() {
		mediaPlayer.stop();
	}
}
