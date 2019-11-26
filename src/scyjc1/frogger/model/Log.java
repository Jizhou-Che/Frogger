package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Log extends Actor {
	private double speed;

	Log(String imageLink, int size, int x, int y, double s) {
		setImage(new Image(imageLink, size, size, true, true));
		setX(x);
		setY(y);
		speed = s;
	}

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

	double getSpeed() {
		return speed;
	}
}