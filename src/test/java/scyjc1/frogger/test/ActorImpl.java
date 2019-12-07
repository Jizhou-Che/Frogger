package scyjc1.frogger.test;

import scyjc1.frogger.model.Actor;

public class ActorImpl extends Actor {

	void setSize(double width, double height) {
		setFitWidth(width);
		setFitHeight(height);
	}

	void setPosition(double xpos, double ypos) {
		setX(xpos);
		setY(ypos);
	}

	@Override
	public void act(long now) {
	}

}
