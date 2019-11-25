package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class BackgroundImage extends Actor {
	@Override
	public void act(long now) {
	}

	BackgroundImage(String imageLink) {
		setImage(new Image(imageLink, 600, 800, true, true));
	}
}
