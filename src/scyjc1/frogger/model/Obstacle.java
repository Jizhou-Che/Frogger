package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class Obstacle extends Actor {
	private int speed;

	public Obstacle(@NamedArg("type") int type, @NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") int speed) {
		switch (type) {
			case 1:
				setImage(new Image("file:resources/images/car_1_left.png", size, size, true, true));
				break;
			case 2:
				setImage(new Image("file:resources/images/car_1_right.png", size, size, true, true));
				break;
			case 3:
				setImage(new Image("file:resources/images/truck_1_left.png", size, size, true, true));
				break;
			case 4:
				setImage(new Image("file:resources/images/truck_1_right.png", size, size, true, true));
				break;
			case 5:
				setImage(new Image("file:resources/images/truck_2_left.png", size, size, true, true));
				break;
			case 6:
				setImage(new Image("file:resources/images/truck_2_right.png", size, size, true, true));
				break;
		}
		setX(x);
		setY(y);
		this.speed = speed;
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
