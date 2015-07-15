package princeton.datastructures.stack;


public class ArrayStack<E> implements Stack<E> {
	private static final int INITIAL_SIZE = 16;
	private E arr[];
	private int top;
	
	public ArrayStack(){
		arr = (E[]) new Object[INITIAL_SIZE];
		top = -1;
	}
	
	@Override
	public void push(E item) {
		if(top == arr.length - 1){
			resize();
		}
		
		top++;
		arr[top] = item;
	}

	@Override
	public E pop() throws EmptyStackException {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		E item = arr[top];
		arr[top] = null;
		top--;
		if(top == arr.length/4 - 1){
			shrink();
		}
		return item;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public int size() {
		return arr.length;
	}

	private void resize(){
		E newArr[] = (E[]) new Object[2*arr.length];
		for(int i = 0; i <= top; i++){
			newArr[i] = arr[i];
		}
		
		arr = newArr;
	}
	
	private void shrink(){
		E newArr[] = (E[]) new Object[arr.length/2];
		for(int i = 0; i <= top; i++){
			newArr[i] = arr[i];
		}
		arr = newArr;
	}
}
