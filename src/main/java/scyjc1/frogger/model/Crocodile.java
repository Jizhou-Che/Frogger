package scyjc1.frogger.model;

import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Crocodile extends MovingActor {
	private Image crocodile1;
	private Image crocodile2;
	private double speed;

	public Crocodile(int size, int x, int y, double speed) {
		crocodile1 = new Image("file:src/main/resources/images/crocodile_animation_1.png", size, size, true, true);
		crocodile2 = new Image("file:src/main/resources/images/crocodile_animation_2.png", size, size, true, true);
		setImage(crocodile1);
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	@Override
	public void act(long now) {
		if (now / 900000000 % 2 == 0) {
			setImage(crocodile1);
		} else {
			setImage(crocodile2);
		}

		move(speed, 0);

		if (getX() > 600 && speed > 0) {
			setX(-180);
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

	public boolean killsFrog(Bounds frogBoundsInLocal) {
		ImageView crocodileHead = new ImageView();
		crocodileHead.setX(getX() + getImage().getWidth() - getImage().getHeight() / 2);
		crocodileHead.setY(getY() + getImage().getHeight() / 2);
		crocodileHead.setFitWidth(getImage().getHeight() / 2);
		return crocodileHead.intersects(frogBoundsInLocal);
	}
}
