package scyjc1.frogger.test;

import org.junit.jupiter.api.Test;
import scyjc1.frogger.model.Obstacle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ObstacleTest {
	@Test
	void actTest() {
		Obstacle testLog2 = new Obstacle(0, 150, 590, 0, 10);
		testLog2.act(0);
		assertEquals(600, testLog2.getX());
		testLog2.act(0);
		assertEquals(-200, testLog2.getX());
		testLog2.act(0);
		assertEquals(-190, testLog2.getX());

		Obstacle testLog1 = new Obstacle(0, 150, -40, 0, -10);
		testLog1.act(0);
		assertEquals(-50, testLog1.getX());
		testLog1.act(0);
		assertEquals(600, testLog1.getX());
		testLog1.act(0);
		assertEquals(590, testLog1.getX());
	}
}
