package scyjc1.frogger;

import javafx.scene.image.Image;

public class Digit extends Actor {

	@Override
	public void act(long now) {
	}

	Digit(int n, int size, int x, int y) {
		setImage(new Image("file:resources/images/" + n + ".png", size, size, true, true));
		setX(x);
		setY(y);
	}
}
