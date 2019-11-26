package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class WetTurtle extends Actor {
	private Image wetTurtle1;
	private Image wetTurtle2;
	private Image wetTurtle3;
	private Image wetTurtle4;
	private double speed;
	private boolean sunk = false;

	public WetTurtle(int x, int y, int s, int w, int h) {
		wetTurtle1 = new Image("file:resources/images/turtle_animation_1.png", w, h, true, true);
		wetTurtle2 = new Image("file:resources/images/wet_turtle_animation_1.png", w, h, true, true);
		wetTurtle3 = new Image("file:resources/images/wet_turtle_animation_2.png", w, h, true, true);
		wetTurtle4 = new Image("file:resources/images/wet_turtle_animation_3.png", w, h, true, true);
		setImage(wetTurtle2);
		setX(x);
		setY(y);
		speed = s;
	}

	@Override
	public void act(long now) {
		if (now / 900000000 % 4 == 0) {
			setImage(wetTurtle2);
			sunk = false;
		} else if (now / 900000000 % 4 == 1) {
			setImage(wetTurtle1);
			sunk = false;
		} else if (now / 900000000 % 4 == 2) {
			setImage(wetTurtle3);
			sunk = false;
		} else if (now / 900000000 % 4 == 3) {
			setImage(wetTurtle4);
			sunk = true;
		}

		move(speed, 0);

		if (getX() > 600 && speed > 0) {
			setX(-200);
		}
		if (getX() < -150 && speed < 0) {
			setX(600);
		}
	}

	boolean isSunk() {
		return sunk;
	}

	double getSpeed() {
		return speed;
	}
}
