package scyjc1.frogger.model;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * <h1>Crocodile</h1>
 * <h2>Extends {@link MovingActor}.</h2>
 * <p>
 *     A moving element in the game.
 *     The frog can land on its back but not on its mouth area.
 * </p>
 */
public class Crocodile extends MovingActor {
	private Image crocodile1;
	private Image crocodile2;
	private double speed;

	/**
	 * Initialises the Crocodile with size, position and speed.
	 *
	 * @param size the desired width or height of the crocodile image, whichever is smaller, as an integer.
	 * @param x the initial x position of the crocodile in the game world as an integer.
	 * @param y the initial y position of the crocodile in the game world as an integer.
	 * @param speed the speed of the crocodile as a double.
	 */
	public Crocodile(int size, int x, int y, double speed) {
		crocodile1 = new Image("file:src/main/resources/images/crocodile_animation_1.png", size, size, true, true);
		crocodile2 = new Image("file:src/main/resources/images/crocodile_animation_2.png", size, size, true, true);
		setImage(crocodile1);
		setX(x);
		setY(y);
		setSpeed(speed);
	}

	/**
	 * Defines the behaviour of crocodiles.
	 * This includes image animation, moving and position resetting on boundaries.
	 *
	 * @param now the current time.
	 */
	@Override
	public void act(long now) {
		// Change picture periodically.
		if (now / 900000000 % 2 == 0) {
			setImage(crocodile1);
		} else {
			setImage(crocodile2);
		}

		move(speed, 0);

		// Reset position on boundaries.
		if (getX() > 600 && speed > 0) {
			setX(-180);
		}
		if (getX() < -300 && speed < 0) {
			setX(700);
		}
	}

	/**
	 * Gets the speed of the crocodile.
	 *
	 * @return the speed of the crocodile as a double.
	 */
	@Override
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed of the crocodile.
	 *
	 * @param speed the desired speed of the crocodile as a double.
	 */
	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Checks whether the frog is killed by the crocodile.
	 *
	 * @param frogBoundsInLocal the Bounds object of the frog.
	 * @return whether the frog is killed by the crocodile as a boolean.
	 */
	public boolean killsFrog(Bounds frogBoundsInLocal) {
		ImageView crocodileHead = new ImageView();
		crocodileHead.setX(getX() + getImage().getWidth() - getImage().getHeight() / 2);
		crocodileHead.setY(getY() + getImage().getHeight() / 2);
		crocodileHead.setFitWidth(getImage().getHeight() / 2);
		return crocodileHead.intersects(frogBoundsInLocal);
	}
}
