package scyjc1.frogger.model;

import javafx.scene.image.Image;

public class LogSnake extends MovingActor {
	private Image logSnakeLeft1;
	private Image logSnakeLeft2;
	private Image logSnakeLeft3;
	private Image logSnakeRight1;
	private Image logSnakeRight2;
	private Image logSnakeRight3;
	private Log log;
	private double speed;

	public LogSnake(Log log, double speed) {
		int size = (int) (log.getImage().getHeight() * 1.4);
		logSnakeLeft1 = new Image("file:resources/images/snake_left_animation_1.png", size, size, true, true);
		logSnakeLeft2 = new Image("file:resources/images/snake_left_animation_2.png", size, size, true, true);
		logSnakeLeft3 = new Image("file:resources/images/snake_left_animation_3.png", size, size, true, true);
		logSnakeRight1 = new Image("file:resources/images/snake_right_animation_1.png", size, size, true, true);
		logSnakeRight2 = new Image("file:resources/images/snake_right_animation_2.png", size, size, true, true);
		logSnakeRight3 = new Image("file:resources/images/snake_right_animation_3.png", size, size, true, true);
		if (speed >= 0) {
			setImage(logSnakeRight1);
			setX(log.getX());
		} else {
			setImage(logSnakeLeft1);
			setX(log.getX() + log.getImage().getWidth() - getImage().getWidth());
		}
		setY(log.getY() + (log.getImage().getHeight() - getImage().getHeight()) / 3);
		setSpeed(speed);
		this.log = log;
	}

	@Override
	public void act(long now) {
		if (speed >= 0) {
			if (now / 150000000 % 3 == 0) {
				setImage(logSnakeRight1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(logSnakeRight2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(logSnakeRight3);
			}
		} else {
			if (now / 150000000 % 3 == 0) {
				setImage(logSnakeLeft1);
			} else if (now / 150000000 % 3 == 1) {
				setImage(logSnakeLeft2);
			} else if (now / 150000000 % 3 == 2) {
				setImage(logSnakeLeft3);
			}
		}

		move(log.getSpeed() + speed, 0);

		// Removed from world on log reset.
		if (getIntersectingObjects(Log.class).size() == 0) {
			getWorld().remove(this);
		}

		// Change direction on log boundary.
		if ((getX() + getImage().getWidth() > log.getX() + log.getImage().getWidth() && speed > 0) || (getX() < log.getX() && speed < 0)) {
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
