package princeton.datastructures.stack;

public class EmptyStackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String MESSAGE = "Empty stack exception!";

	public EmptyStackException(){
		super(MESSAGE);
	}
	
}
