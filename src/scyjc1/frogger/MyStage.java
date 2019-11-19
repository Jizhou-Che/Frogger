package scyjc1.frogger;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MyStage extends World {
	private MediaPlayer mediaPlayer;

	@Override
	public void act(long now) {
	}

	MyStage() {
//		mediaPlayer.play();
//		mediaPlayer.setOnEndOfMedia(new Runnable() {
//			@Override
//			public void run() {
//				mediaPlayer.seek(Duration.ZERO);
//			}
//		});
//		mediaPlayer.play();
	}

	void playMusic() {
		String musicFile = "resources/Frogger Main Song Theme (loop).mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}

	void stopMusic() {
		mediaPlayer.stop();
	}
}
