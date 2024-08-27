/**
 * This class uses stack to handle code blocks.
 * 
 * @author jasonbokinz, ID: 112555537, R:03
 *
 */
import java.util.Stack;
public class BlockStack {
	/**
	 * Below are the parameters for BlockStack
	 * @param stack
	 * Stack of CodeBlocks
	 * @param total
	 * number of elements in the stack
	 */
	private Stack<CodeBlock> stack;
	private int total=0;
	
	/**
	 * The constructor creates a default instance of Block Stack and creates a new stack of code blocks
	 */
	public BlockStack() {
		stack = new Stack<CodeBlock>();
	}
	
	/**
	 * This method adds CodeBlock block to the stack
	 * @param block
	 * CodeBlock being added
	 */
	public void push(CodeBlock block) {
		total++;
		stack.push(block);
	}
	
	/**
	 * This method removes the top of the CodeBlockStack
	 * @return
	 * The CodeBlock at the top of the stack that was removed
	 */
	public CodeBlock pop() {
		if (!stack.isEmpty())
			total--;
		return stack.pop();
	}
	
	/**
	 * This method is used to look at the CodeBlock at the top of the stack
	 * @return
	 * The CodeBlock at the top of the stack
	 */
	public CodeBlock peek() {
		return stack.peek();
	}
	
	/**
	 * This method is used to get the number of elements in the CodeBlock stack
	 * @return
	 * Number of elements in the stack
	 */
	public int size() {
		return total;
	}
	
	/**
	 * This method is used to see if the CodeBlock stack has no elements
	 * @return
	 * True if the stack is empty, false if not
	 */
	public boolean isEmpty() {
		return stack.size() == 0;
	}
}
