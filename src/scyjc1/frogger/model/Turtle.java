package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class Turtle extends Actor {
	private Image turtle1;
	private Image turtle2;
	private Image turtle3;
	private double speed;

	public Turtle(@NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		turtle1 = new Image("file:resources/images/turtle_animation_1.png", size, size, true, true);
		turtle2 = new Image("file:resources/images/turtle_animation_2.png", size, size, true, true);
		turtle3 = new Image("file:resources/images/turtle_animation_3.png", size, size, true, true);
		setImage(turtle2);
		setX(x);
		setY(y);
		this.speed = speed;
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
