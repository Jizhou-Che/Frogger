package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class WetTurtle extends Actor {
	private Image wetTurtle1;
	private Image wetTurtle2;
	private Image wetTurtle3;
	private Image wetTurtle4;
	private double speed;
	private boolean isSunk = false;

	public WetTurtle(@NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		wetTurtle1 = new Image("file:resources/images/turtle_animation_1.png", size, size, true, true);
		wetTurtle2 = new Image("file:resources/images/wet_turtle_animation_1.png", size, size, true, true);
		wetTurtle3 = new Image("file:resources/images/wet_turtle_animation_2.png", size, size, true, true);
		wetTurtle4 = new Image("file:resources/images/wet_turtle_animation_3.png", size, size, true, true);
		setImage(wetTurtle2);
		setX(x);
		setY(y);
		this.speed = speed;
	}

	@Override
	public void act(long now) {
		if (now / 900000000 % 4 == 0) {
			setImage(wetTurtle2);
			isSunk = false;
		} else if (now / 900000000 % 4 == 1) {
			setImage(wetTurtle1);
			isSunk = false;
		} else if (now / 900000000 % 4 == 2) {
			setImage(wetTurtle3);
			isSunk = false;
		} else if (now / 900000000 % 4 == 3) {
			setImage(wetTurtle4);
			isSunk = true;
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
		return isSunk;
	}

	double getSpeed() {
		return speed;
	}
}
