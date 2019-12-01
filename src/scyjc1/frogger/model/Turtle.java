package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Turtle extends Actor {
	private Image turtle1;
	private Image turtle2;
	private Image turtle3;
	private double speed;

	public Turtle(int x, int y, double s, int w, int h) {
		turtle1 = new Image("file:resources/images/turtle_animation_1.png", w, h, true, true);
		turtle2 = new Image("file:resources/images/turtle_animation_2.png", w, h, true, true);
		turtle3 = new Image("file:resources/images/turtle_animation_3.png", w, h, true, true);
		setImage(turtle2);
		setX(x);
		setY(y);
		speed = s;
	}

	@Override
	public void act(long now) {
		if (now / 900000000 % 3 == 0) {
			setImage(turtle2);
		} else if (now / 900000000 % 3 == 1) {
			setImage(turtle1);
		} else if (now / 900000000 % 3 == 2) {
			setImage(turtle3);
		}

		move(speed, 0);

		if (getX() > 600 && speed > 0) {
			setX(-200);
		}
		if (getX() < -150 && speed < 0) {
			setX(600);
		}
	}

	double getSpeed() {
		return speed;
	}
}
