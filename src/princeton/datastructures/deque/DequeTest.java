package princeton.datastructures.deque;

import static org.junit.Assert.assertEquals;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DequeTest {

	private Deque<Integer> deque;
	private static Integer TEST_NUM = 1;
	
	@Before
	public void setUp(){
		deque = new Deque<Integer>();
	}
	@Test
	public void testIsEmptyWhenDequeIsEmpty() {
		boolean isEmpty = deque.isEmpty();
		assertEquals(true, isEmpty);
	}
	
	@Test
	public void testIsEmptyWhenDequeHasElements() {
		deque.addFirst(TEST_NUM);
		boolean isEmpty = deque.isEmpty();
		assertEquals(false, isEmpty);
	}

	@Test
	public void testSizeWhenDequeIsEmpty() {
		int size = deque.size();
		assertEquals(0, size);
	}
	
	@Test
	public void testSizeWhenDequeHasASingleELement() {
		deque.addFirst(TEST_NUM);
		int size = deque.size();
		assertEquals(1, size);
	}

	@Test
	public void testAddFirst() {
		deque.addFirst(TEST_NUM);
		int size = deque.size();
		assertEquals(1, size);
	}

	@Test
	public void testAddLast() {
		deque.addLast(TEST_NUM);
		int size = deque.size();
		assertEquals(1, size);
	}

	@Test
	public void testRemoveFirstWithSuccess() {
		deque.addFirst(TEST_NUM);
		deque.removeFirst();
		int size = deque.size();
		assertEquals(0, size);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstEmptyStack(){
		deque.removeFirst();
	}

	@Test
	public void testRemoveLastWithSuccess() {
		deque.addFirst(TEST_NUM);
		deque.removeLast();
		int size = deque.size();
		assertEquals(0, size);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastEmptyStack(){
		deque.removeLast();
	}

}
