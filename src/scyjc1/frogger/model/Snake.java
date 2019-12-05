package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Snake extends MovingActor {
	private Image snakeLeft1;
	private Image snakeLeft2;
	private Image snakeLeft3;
	private Image snakeRight1;
	private Image snakeRight2;
	private Image snakeRight3;
	private double speed;

	public Snake(int size, int x, int y, double speed) {
		snakeLeft1 = new Image("file:resources/images/snake_left_animation_1.png", size, size, true, true);
		snakeLeft2 = new Image("file:resources/images/snake_left_animation_2.png", size, size, true, true);
		snakeLeft3 = new Image("file:resources/images/snake_left_animation_3.png", size, size, true, true);
		snakeRight1 = new Image("file:resources/images/snake_right_animation_1.png", size, size, true, true);
		snakeRight2 = new Image("file:resources/images/snake_right_animation_2.png", size, size, true, true);
		snakeRight3 = new Image("file:resources/images/snake_right_animation_3.png", size, size, true, true);
		if (speed >= 0) {
			setImage(snakeRight1);
		} else {
			setImage(snakeLeft1);
		}
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	@Override
	public void act(long now) {
		if (speed >= 0) {
			if (now / 150000000 % 3 == 0) {
				setImage(snakeRight1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(snakeRight2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(snakeRight3);
			}
		} else {
			if (now / 150000000 % 3 == 0) {
				setImage(snakeLeft1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(snakeLeft2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(snakeLeft3);
			}
		}

		move(speed, 0);

		if ((getX() > 900 && speed > 0) || (getX() < -300 && speed < 0)) {
			speed = -speed;
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
