package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class Snake extends MovingActor {
	private Image snake1;
	private Image snake2;
	private Image snake3;
	private double speed;

	public Snake(int size, int x, int y, double speed) {
		snake1 = new Image("file:resources/images/snake_animation_1.png", size, size, true, true);
		snake2 = new Image("file:resources/images/snake_animation_2.png", size, size, true, true);
		snake3 = new Image("file:resources/images/snake_animation_3.png", size, size, true, true);
		setImage(snake1);
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	@Override
	public void act(long now) {
		if (now / 150000000 % 3 == 0) {
			setImage(snake1);
		} else if (now / 150000000 % 3 == 1) {
			setImage(snake2);
		} else if (now / 150000000 % 3 == 2) {
			setImage(snake3);
		}

		move(speed, 0);

		if (getX() > 700 && speed > 0) {
			setX(-300);
		}
		if (getX() < -300 && speed < 0) {
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
