package scyjc1.frogger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
	private World testWorld = new World();
	private ActorImpl testActor = new ActorImpl();

	@Test
	void addTest() {
		testWorld.add(testActor);
		assertSame(testWorld, testActor.getWorld());
//		testWorld.remove(testActor);
//		assertNotSame(testWorld, testActor.getWorld());
	}

	@Test
	void getObjectsTest() {
		ActorImpl validObject1 = new ActorImpl();
		ActorImpl validObject2 = new ActorImpl();
		testWorld.add(validObject1);
		testWorld.add(validObject2);
		assertEquals(0, testWorld.getObjects(Log.class).size());
		assertEquals(2, testWorld.getObjects(ActorImpl.class).size());
	}
}
