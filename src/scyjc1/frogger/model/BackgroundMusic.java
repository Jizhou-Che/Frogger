package scyjc1.frogger.model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

/**
 * The background music controller.
 * Singleton pattern applied.
 */
class BackgroundMusic {
	private static BackgroundMusic bgm;
	private static final MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("resources/sounds/theme.mp3").toURI().toString()));

	private BackgroundMusic() {}

	static BackgroundMusic getBgm() {
		if (bgm == null) {
			bgm = new BackgroundMusic();
		}
		return bgm;
	}

	void play() {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	void stop() {
		mediaPlayer.stop();
	}
}
