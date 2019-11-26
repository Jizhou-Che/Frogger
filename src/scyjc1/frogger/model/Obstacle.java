package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Obstacle extends Actor {
	private int speed;

	public Obstacle(String imageLink, int x, int y, int s, int w, int h) {
		setImage(new Image(imageLink, w, h, true, true));
		setX(x);
		setY(y);
		speed = s;
	}

	@Override
	public void act(long now) {
		move(speed, 0);
		if (getX() > 600 && speed > 0) {
			setX(-200);
		}
		if (getX() < -50 && speed < 0) {
			setX(600);
		}
	}
}
