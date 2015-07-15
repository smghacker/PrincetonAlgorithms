package princeton.datastructures.stack;

public class StackMain {

	public static void main(String[] args) {
		Stack<Integer> stack = new ArrayStack<Integer>();
		int n = 50;
		for(int i = 0; i < n; i++){
			stack.push(i);
			System.out.println(i + " " + stack.size());
		}
		
		for(int i = 0; i < n; i++){
			try {
				Integer num = stack.pop();
				System.out.println(num + " " + stack.size());
			} catch (EmptyStackException e) {
				e.printStackTrace();
			}
		}
	}

}
