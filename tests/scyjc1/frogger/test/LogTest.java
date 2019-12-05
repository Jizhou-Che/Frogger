package scyjc1.frogger.test;

import org.junit.jupiter.api.Test;
import scyjc1.frogger.model.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogTest {
	@Test
	void actTest() {
		Log testLog2 = new Log(0, 150, 590, 0, 10);
		testLog2.act(0);
		assertEquals(600, testLog2.getX());
		testLog2.act(0);
		assertEquals(-180, testLog2.getX());
		testLog2.act(0);
		assertEquals(-170, testLog2.getX());

		Log testLog1 = new Log(0, 150, -290, 0, -10);
		testLog1.act(0);
		assertEquals(-300, testLog1.getX());
		testLog1.act(0);
		assertEquals(700, testLog1.getX());
		testLog1.act(0);
		assertEquals(690, testLog1.getX());
	}
}
