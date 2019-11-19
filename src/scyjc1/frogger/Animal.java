package scyjc1.frogger;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Animal extends Actor {
	private Image imgW1;
	private Image imgA1;
	private Image imgS1;
	private Image imgD1;
	private Image imgW2;
	private Image imgA2;
	private Image imgS2;
	private Image imgD2;
	private int points = 0;
	private int end = 0;
	private boolean second = false;
	private boolean noMove = false;
	private double movement = 13.3333333 * 2;
	private double movementX = 10.666666 * 2;
	private int imgSize = 40;
	private boolean carDeath = false;
	private boolean waterDeath = false;
	private boolean stop = false;
	private boolean changeScore = false;
	private int carD = 0;
	private double w = 800;
	private ArrayList<End> inter = new ArrayList<End>();

	Animal(String imageLink) {
		setImage(new Image(imageLink, imgSize, imgSize, true, true));
		setX(300);
		setY(679.8 + movement);
		imgW1 = new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true);
		imgA1 = new Image("file:resources/froggerLeft.png", imgSize, imgSize, true, true);
		imgS1 = new Image("file:resources/froggerDown.png", imgSize, imgSize, true, true);
		imgD1 = new Image("file:resources/froggerRight.png", imgSize, imgSize, true, true);
		imgW2 = new Image("file:resources/froggerUpJump.png", imgSize, imgSize, true, true);
		imgA2 = new Image("file:resources/froggerLeftJump.png", imgSize, imgSize, true, true);
		imgS2 = new Image("file:resources/froggerDownJump.png", imgSize, imgSize, true, true);
		imgD2 = new Image("file:resources/froggerRightJump.png", imgSize, imgSize, true, true);
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					if (second) {
						if (event.getCode() == KeyCode.W) {
							move(0, -movement);
							changeScore = false;
							setImage(imgW1);
							second = false;
						} else if (event.getCode() == KeyCode.A) {
							move(-movementX, 0);
							setImage(imgA1);
							second = false;
						} else if (event.getCode() == KeyCode.S) {
							move(0, movement);
							setImage(imgS1);
							second = false;
						} else if (event.getCode() == KeyCode.D) {
							move(movementX, 0);
							setImage(imgD1);
							second = false;
						}
					} else if (event.getCode() == KeyCode.W) {
						move(0, -movement);
						setImage(imgW2);
						second = true;
					} else if (event.getCode() == KeyCode.A) {
						move(-movementX, 0);
						setImage(imgA2);
						second = true;
					} else if (event.getCode() == KeyCode.S) {
						move(0, movement);
						setImage(imgS2);
						second = true;
					} else if (event.getCode() == KeyCode.D) {
						move(movementX, 0);
						setImage(imgD2);
						second = true;
					}
				}
			}
		});

		setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if (!noMove) {
					if (event.getCode() == KeyCode.W) {
						if (getY() < w) {
							changeScore = true;
							w = getY();
							points += 10;
						}
						move(0, -movement);
						setImage(imgW1);
						second = false;
					} else if (event.getCode() == KeyCode.A) {
						move(-movementX, 0);
						setImage(imgA1);
						second = false;
					} else if (event.getCode() == KeyCode.S) {
						move(0, movement);
						setImage(imgS1);
						second = false;
					} else if (event.getCode() == KeyCode.D) {
						move(movementX, 0);
						setImage(imgD1);
						second = false;
					}
				}
			}
		});
	}

	@Override
	public void act(long now) {
		int bounds = 0;
		if (getY() < 0 || getY() > 734) {
			setX(300);
			setY(679.8 + movement);
		}
		if (getX() < 0) {
			move(movement * 2, 0);
		}
		if (carDeath) {
			noMove = true;
			if ((now) % 11 == 0) {
				carD++;
			}
			if (carD == 1) {
				setImage(new Image("file:resources/cardeath1.png", imgSize, imgSize, true, true));
			}
			if (carD == 2) {
				setImage(new Image("file:resources/cardeath2.png", imgSize, imgSize, true, true));
			}
			if (carD == 3) {
				setImage(new Image("file:resources/cardeath3.png", imgSize, imgSize, true, true));
			}
			if (carD == 4) {
				setX(300);
				setY(679.8 + movement);
				carDeath = false;
				carD = 0;
				setImage(new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true));
				noMove = false;
				if (points > 50) {
					points -= 50;
					changeScore = true;
				}
			}

		}
		if (waterDeath) {
			noMove = true;
			if ((now) % 11 == 0) {
				carD++;
			}
			if (carD == 1) {
				setImage(new Image("file:resources/waterdeath1.png", imgSize, imgSize, true, true));
			}
			if (carD == 2) {
				setImage(new Image("file:resources/waterdeath2.png", imgSize, imgSize, true, true));
			}
			if (carD == 3) {
				setImage(new Image("file:resources/waterdeath3.png", imgSize, imgSize, true, true));
			}
			if (carD == 4) {
				setImage(new Image("file:resources/waterdeath4.png", imgSize, imgSize, true, true));
			}
			if (carD == 5) {
				setX(300);
				setY(679.8 + movement);
				waterDeath = false;
				carD = 0;
				setImage(new Image("file:resources/froggerUp.png", imgSize, imgSize, true, true));
				noMove = false;
				if (points > 50) {
					points -= 50;
					changeScore = true;
				}
			}

		}

		if (getX() > 600) {
			move(-movement * 2, 0);
		}
		if (getIntersectingActors(Obstacle.class).size() >= 1) {
			carDeath = true;
		}
		if (getX() == 240 && getY() == 82) {
			stop = true;
		}
		if (getIntersectingActors(Log.class).size() >= 1 && !noMove) {
			if (getIntersectingActors(Log.class).get(0).getLeft())
				move(-2, 0);
			else
				move(.75, 0);
		} else if (getIntersectingActors(Turtle.class).size() >= 1 && !noMove) {
			move(-1, 0);
		} else if (getIntersectingActors(WetTurtle.class).size() >= 1) {
			if (getIntersectingActors(WetTurtle.class).get(0).isSunk()) {
				waterDeath = true;
			} else {
				move(-1, 0);
			}
		} else if (getIntersectingActors(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingActors(End.class);
			if (getIntersectingActors(End.class).get(0).isActivated()) {
				end--;
				points -= 50;
			}
			points += 50;
			changeScore = true;
			w = 800;
			getIntersectingActors(End.class).get(0).setEnd();
			end++;
			setX(300);
			setY(679.8 + movement);
		} else if (getY() < 413) {
			waterDeath = true;
			//setX(300);
			//setY(679.8+movement);
		}
	}

	boolean getStop() {
		return end == 5;
	}

	int getPoints() {
		return points;
	}

	boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
	}
}
