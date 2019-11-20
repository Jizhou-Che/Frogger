package scyjc1.frogger;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ActorTest {
	private WorldImpl testWorld = new WorldImpl();
	private ActorImpl testActor = new ActorImpl();

	@Test
	void moveTest() {
		testActor.setPosition(0, 0);
		testActor.move(9.9, -9.9);
		assertEquals(9.9, testActor.getX());
		assertEquals(-9.9, testActor.getY());
	}

	@Test
	void getWorldTest() {
		testWorld.add(testActor);
		assertSame(testWorld, testActor.getWorld());
	}

	@Test
	void getIntersectingObjectsTest() {
		testActor.setPosition(0, 0);
		testActor.setSize(40, 40);
		testWorld.add(testActor);

		ActorImpl testActorSubset = new ActorImpl();
		testActorSubset.setPosition(0, 0);
		testActorSubset.setSize(20, 20);
		testWorld.add(testActorSubset);

		ActorImpl testActorIntersect = new ActorImpl();
		testActorIntersect.setPosition(40, 40);
		testActorIntersect.setSize(30, 30);
		testWorld.add(testActorIntersect);

		ActorImpl testActorSuperset = new ActorImpl();
		testActorSuperset.setPosition(0, 0);
		testActorSuperset.setSize(60, 60);
		testWorld.add(testActorSuperset);

		ActorImpl testActorDisjointed = new ActorImpl();
		testActorDisjointed.setPosition(80, 80);
		testActorDisjointed.setSize(20, 20);
		testWorld.add(testActorDisjointed);

		ArrayList<ActorImpl> intersectingActors = new ArrayList<ActorImpl>();
		intersectingActors.add(testActorSubset);
		intersectingActors.add(testActorIntersect);
		intersectingActors.add(testActorSuperset);
		assertEquals(intersectingActors, (ArrayList<ActorImpl>) testActor.getIntersectingObjects(ActorImpl.class));
	}
}
