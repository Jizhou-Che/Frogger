package frogger.test;

import org.junit.jupiter.api.Test;
import frogger.model.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogTest {
	@Test
	public void actTest1() {
		Log testLog = new Log(0, 150, 590, 0, 10);
		testLog.act(0);
		assertEquals(600, testLog.getX());
		testLog.act(0);
		assertEquals(-180, testLog.getX());
		testLog.act(0);
		assertEquals(-170, testLog.getX());
		assertEquals(0, testLog.getY());
	}

	@Test
	public void actTest2() {
		Log testLog = new Log(0, 150, -290, 0, -10);
		testLog.act(0);
		assertEquals(-300, testLog.getX());
		testLog.act(0);
		assertEquals(700, testLog.getX());
		testLog.act(0);
		assertEquals(690, testLog.getX());
		assertEquals(0, testLog.getY());
	}
}
