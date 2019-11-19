package scyjc1.frogger;

import javafx.scene.image.Image;

public class Digit extends Actor {
	int dim;

	@Override
	public void act(long now) {
	}

	Digit(int n, int dim, int x, int y) {
		Image im1 = new Image("file:resources/" + n + ".png", dim, dim, true, true);
		setImage(im1);
		setX(x);
		setY(y);
	}
}
