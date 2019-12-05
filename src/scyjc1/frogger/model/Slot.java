package scyjc1.frogger.model;

import javafx.beans.NamedArg;
import javafx.scene.image.Image;

public class Slot extends Actor {
	// 0 for empty, 1 for occupied, 2 for crocodile, 3 for fly.
	private int status = 0;

	@Override
	public void act(long now) {
	}

	public Slot(@NamedArg("x") int x, @NamedArg("y") int y) {
		setX(x);
		setY(y);
		setImage(new Image("file:resources/images/slot.png", 60, 60, true, true));
	}

	public void setEmpty() {
		setImage(new Image("file:resources/images/slot.png", 60, 60, true, true));
		status = 0;
	}

	void setOccupied() {
		setImage(new Image("file:resources/images/slot_occupied.png", 65, 65, true, true));
		status = 1;
	}

	public void setCrocodile() {
		setImage(new Image("file:resources/images/slot_crocodile.png", 65, 65, true, true));
		status = 2;
	}

	public void setFly() {
		setImage(new Image("file:resources/images/slot_fly.png", 65, 65, true, true));
		status = 3;
	}

	public int getStatus() {
		return status;
	}
}
