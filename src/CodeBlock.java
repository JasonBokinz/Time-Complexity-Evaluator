/**
 * This class describes a nested block of code.
 * 
 * @author jasonbokinz, ID: 112555537, R:03
 *
 */
public class CodeBlock {
	/**
	 * Below are the static final parameters for CodeBlock
	 * @param BLOCK_TYPES
	 * Array of Strings of different types of code blocks
	 * @param DEF
	 * index of the def block type
	 * @param FOR
	 * index of the for block type
	 * @param WHILE
	 * index of the while block type
	 * @param IF
	 * index of the if block type
	 * @param ELIF
	 * index of the elif block type
	 * @param ELSE
	 * index of the else block type
	 */
	public static final String[] BLOCK_TYPES = {"def", "for", "while", "if", "elif", "else"};
	public static final int DEF=0, FOR=1, WHILE = 2, IF=3, ELIF=4, ELSE=5;
	
	
	/**
	 * Below are the regular private parameters for CodeBlock
	 * @param name
	 * keeps track of the nested structure of the blocks
	 * @param loopVariable
	 * keeps track of the while loop variables
	 * @param blockComplexity
	 * keeps track of the Big-Oh complexity of this block
	 * @param highestSubComplexity
	 * keeps track of the Big-Oh complexity of the highest-order block nested within this block
	 * @param numOfIndents
	 * number of indents of the code block
	 * @param updated
	 * if the code block has been updated
	 */
	private String name, loopVariable;
	private Complexity blockComplexity, highestSubComplexity;
	private int numOfIndents;
	private boolean updated = false;
	
	/**
	 * The constructor creates a default instance of CodeBlock
	 */
	public CodeBlock() {
		loopVariable = null;
	}
	
	/**
	 * This method sets if the code block's complexity has been updated
	 * @param updated
	 * If the code block's complexity has been updated
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}
	
	/**
	 * This method is used to check if the code block's complexity has been updated
	 * @return
	 * Whether or not the code block complexity has been updated
	 */
	public boolean getUpdated() {
		return updated;
	}
	/**
	 * This method is used to access CodeBlock's name
	 * @return
	 * name (keeps track of the nested structure of the blocks)
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method is used to access CodeBlock's loopVariable
	 * @return
	 * loopVariable (keeps track of the while loops)
	 */
	public String getLoopVariable() {
		return loopVariable;
	}
	
	/**
	 * This method is used to access CodeBlock's blockComplexity
	 * @return
	 * blockComplexity (keeps track of the Big-Oh complexity of this block)
	 */
	public Complexity getBlockComplexity() {
		return blockComplexity;
	}
	
	/**
	 * This method is used to access CodeBlock's highestSubComplexity
	 * @return
	 * highestSubComplexity (keeps track of the Big-Oh complexity of the highest-order block nested within this block)
	 */
	public Complexity getHighestSubComplexity() {
		return highestSubComplexity;
	}
	
	/**
	 * This method is used to set name to the newName
	 * @param name
	 * New name you want to set as name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method is used to set loopVariable to the new loopVariable
	 * @param loopVariable
	 * New loopVariable you want to set as loopVariable
	 */
	public void setLoopVariable(String loopVariable) {
		this.loopVariable = loopVariable;
	}
	
	/**
	 * This method is used to set blockComplexity to the new blockComplexity
	 * @param blockComplexity
	 * New blockComplexity you want to set as blockComplexity
	 */
	public void setBlockComplexity(Complexity blockComplexity) {
		this.blockComplexity = blockComplexity;
	}
	
	/**
	 * This method is used to set highestSubComplexity to the new highestSubComplexity
	 * @param highestSubComplexity
	 * New highestSubComplexity you want to set as highestSubComplexity
	 */
	public void setHighestSubComplexity(Complexity highestSubComplexity) {
		this.highestSubComplexity = highestSubComplexity;
	}
	
	/**
	 * This method is used to access the number of indents in the code block
	 * @return
	 * Number of indents in the code block
	 */
	public int getNumOfIndents() {
		return numOfIndents;
	}
	
	/**
	 * This method sets the number of indents of the code block
	 * @param indents
	 * Number of indents you want to set indents to
	 */
	public void setNumOfIndents(int indents) {
		numOfIndents = indents;
	}
	
	/**
	 * This method is used to neatly format each block's information
	 * @overrides
	 * Overrides the object class's toString() method
	 */
	public String toString() {
		return String.format("%-20s%-35s%s", " BLOCK " + name + ":", "block complexity = " + blockComplexity, "highest sub-complexity = " + highestSubComplexity);
	}
}
