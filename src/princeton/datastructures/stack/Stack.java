package princeton.datastructures.stack;

public interface Stack<E> {
	void push(E item);
	E pop() throws EmptyStackException;
	boolean isEmpty();
	int size();
}
