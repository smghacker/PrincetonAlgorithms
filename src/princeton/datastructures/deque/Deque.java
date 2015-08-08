package princeton.datastructures.deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int numOfElements;

	public Deque() {
		first = null;
		last = null;
		numOfElements = 0;
	}

	public boolean isEmpty() {
		return numOfElements == 0;
	}

	public int size() {
		return numOfElements;
	}

	public void addFirst(Item item) {
		if (isEmpty()) {
			first = new Node(item, null, null);
			last = new Node(item, null, null);
		} else {
			Node newNode = new Node(item, null, first);
			first.setPrevious(newNode);
			first = newNode;
			if (numOfElements == 1) {
				last.setPrevious(last);
			}
		}
		numOfElements++;
	}

	public void addLast(Item item) {
		if (isEmpty()) {
			first = new Node(item, null, null);
			last = new Node(item, null, null);
		} else {
			Node newNode = new Node(item, last, null);
			last.setNext(newNode);
			last = newNode;
			if (numOfElements == 1) {
				first.setNext(last);
			}
		}
		numOfElements++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			Node toReturn = first;
			first = first.getNext();
			if (first != null) {
				first.setPrevious(null);
			}
			numOfElements--;
			return toReturn.getData();
		}

	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			Node toReturn = last;
			last = last.getPrevious();
			if (last != null) {
				last.setNext(null);
			}
			numOfElements--;
			return toReturn.getData();
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator(first);
	}

	private class Node {
		private Node previous;
		private Node next;
		private Item data;

		public Node(Item data, Node previous, Node next) {
			this.data = data;
			this.previous = previous;
			this.next = next;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Item getData() {
			return data;
		}
	}

	private class DequeIterator implements Iterator<Item> {
		private Node front;

		public DequeIterator(Node front) {
			this.front = front;
		}

		@Override
		public boolean hasNext() {
			if (front != null) {
				return true;
			}
			return false;
		}

		@Override
		public Item next() {
			if (front != null) {
				Node toReturn = front;
				front = front.getNext();
				return toReturn.getData();
			}

			throw new NoSuchElementException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();

		}

	}

	public static void main(String[] args) {
		int n = 10;
		Deque<Integer> deq = new Deque<Integer>();
		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				deq.addFirst(i);
			} else {
				deq.addLast(i);
			}
		}

		for (Integer i : deq) {
			System.out.println(i);
		}
	}
}
