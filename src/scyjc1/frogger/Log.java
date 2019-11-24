package scyjc1.frogger;

import javafx.scene.image.Image;

public class Log extends Actor {
	double speed;

	@Override
	public void act(long now) {
		move(speed, 0);
		if (getX() > 600 && speed > 0) {
			// Going right, exceeds boundary.
			setX(-180);
		}
		if (getX() < -300 && speed < 0) {
			// Going left, exceeds boundary.
			setX(700);
		}
	}

	Log(String imageLink, int size, int x, int y, double s) {
		setImage(new Image(imageLink, size, size, true, true));
		setX(x);
		setY(y);
		speed = s;
	}

	boolean getLeft() {
		return speed < 0;
	}
}
