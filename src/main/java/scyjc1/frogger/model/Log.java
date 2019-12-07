package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class Log extends MovingActor {
	private double speed;

	public Log(@NamedArg("type") int type, @NamedArg("size") int size, @NamedArg("x") int x, @NamedArg("y") int y, @NamedArg("speed") double speed) {
		switch (type) {
			case 1:
				setImage(new Image("file:src/main/resources/images/log_1.png", size, size, true, true));
				break;
			case 2:
				setImage(new Image("file:src/main/resources/images/log_2.png", size, size, true, true));
				break;
			case 3:
				setImage(new Image("file:src/main/resources/images/log_3.png", size, size, true, true));
				break;
		}
		setX(x);
		setY(y);
		setSpeed(speed);
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

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}
}
