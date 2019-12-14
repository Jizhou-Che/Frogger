package frogger.test;

import org.junit.jupiter.api.Test;
import frogger.model.Log;
import frogger.model.World;

import static org.junit.jupiter.api.Assertions.*;

public class WorldTest {
	private World testWorld = new World();
	private ActorImpl testActor = new ActorImpl();

	@Test
	public void addTest() {
		testWorld.add(testActor);
		assertSame(testWorld, testActor.getWorld());
	}

	@Test
	public void removeTest() {
		testWorld.remove(testActor);
		assertNotSame(testWorld, testActor.getWorld());
	}

	@Test
	public void getObjectsTest() {
		ActorImpl validObject1 = new ActorImpl();
		ActorImpl validObject2 = new ActorImpl();
		testWorld.add(validObject1);
		testWorld.add(validObject2);
		assertEquals(0, testWorld.getObjects(Log.class).size());
		assertEquals(2, testWorld.getObjects(ActorImpl.class).size());
	}
}
