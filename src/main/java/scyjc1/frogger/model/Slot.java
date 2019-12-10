package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

/**
 * A static element in the game.
 * Possible status: 0 - empty, 1 - occupied, 2 - crocodile, 3 - fly.
 * The {@link Frog} can get killed or rewarded on entering depending on the status.
 */
public class Slot extends Actor {
	// 0 for empty, 1 for occupied, 2 for crocodile, 3 for fly.
	private int status = 0;
	private Image imgEmpty;
	private Image imgOccupied;
	private Image imgCrocodile;
	private Image imgFly;

	/**
	 * Initialises the {@link Slot} with image and position.
	 *
	 * @param x the x position of the {@link Slot} as an integer.
	 * @param y the y position of the {@link Slot} as an integer.
	 */
	public Slot(@NamedArg("x") int x, @NamedArg("y") int y) {
		imgEmpty = new Image("file:src/main/resources/images/slot.png", 60, 60, true, true);
		imgOccupied = new Image("file:src/main/resources/images/slot_occupied.png", 65, 65, true, true);
		imgCrocodile = new Image("file:src/main/resources/images/slot_crocodile.png", 65, 65, true, true);
		imgFly = new Image("file:src/main/resources/images/slot_fly.png", 65, 65, true, true);
		setImage(imgEmpty);
		setX(x);
		setY(y);
	}

	/**
	 * Defines the behaviour of {@link Slot}.
	 *
	 * @param now the timestamp of the current frame given in nanoseconds.
	 */
	@Override
	public void act(long now) {}

	/**
	 * Sets the status of the {@link Slot}.
	 *
	 * @param status the desired status of the {@link Slot}, 0 to 3, as an integer.
	 */
	public void setStatus(int status) {
		this.status = status;
		switch (status) {
			case 0:
				setImage(imgEmpty);
				break;
			case 1:
				setImage(imgOccupied);
				break;
			case 2:
				setImage(imgCrocodile);
				break;
			case 3:
				setImage(imgFly);
				break;
		}
	}

	/**
	 * Gets the status of the {@link Slot}.
	 *
	 * @return the status of the {@link Slot}, 0 to 3, as an integer.
	 */
	public int getStatus() {
		return status;
	}
}
