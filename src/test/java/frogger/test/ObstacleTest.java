package frogger.test;

import org.junit.jupiter.api.Test;
import frogger.model.Obstacle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObstacleTest {
	@Test
	public void actTest1() {
		Obstacle testLog = new Obstacle(0, 150, 590, 0, 10);
		testLog.act(0);
		assertEquals(600, testLog.getX());
		testLog.act(0);
		assertEquals(-200, testLog.getX());
		testLog.act(0);
		assertEquals(-190, testLog.getX());
		assertEquals(0, testLog.getY());
	}

	@Test
	public void actTest2() {
		Obstacle testLog = new Obstacle(0, 150, -40, 0, -10);
		testLog.act(0);
		assertEquals(-50, testLog.getX());
		testLog.act(0);
		assertEquals(600, testLog.getX());
		testLog.act(0);
		assertEquals(590, testLog.getX());
		assertEquals(0, testLog.getY());
	}
}
