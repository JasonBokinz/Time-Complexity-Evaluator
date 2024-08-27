/**
 * This class is used to read a Python file, push each keyword code block on to a stack,
 * and pop when necessary while also updating the total complexity depending on if the block is nested or not
 * 
 * @author jasonbokinz, ID: 112555537, R:03
 *
 */
import java.io.File;
import java.util.Scanner;
public class PythonTracer {
	
	/**
	 * Below are the static parameters of the main and traceFile methods
	 * @param SPACE_COUNT
	 * number of spaces that determines one indents
	 * @param format
	 * Array of integers to format the block's names
	 */
	public static final int SPACE_COUNT = 4;
	private static int [] format = new int[20];
	
	/**
	 * This method is used to compare complexities based on if they are nested or not
	 * @param stack
	 * The stack of code blocks
	 */
	public static void compareComplexities(BlockStack stack) {
		CodeBlock oldTop = stack.pop();
		Complexity oldTopComplexity = oldTop.getBlockComplexity();
		Complexity oldHighSubTopComplexity = oldTop.getHighestSubComplexity();

		Complexity currentTopComplexity = stack.peek().getHighestSubComplexity();
		
		int prevNumOfIndents = oldTop.getNumOfIndents();
		int currentNumOfIndents = stack.peek().getNumOfIndents();
		
		/**
		 * Below runs if the two complexities being compared that are nested (you have to add the exponents)
		 */
		if((currentNumOfIndents > prevNumOfIndents) || ((stack.peek().getHighestSubComplexity().getN_power() == 0) && (stack.peek().getHighestSubComplexity().getLog_power() == 0))) {

			if(currentTopComplexity.getN_power() < oldTopComplexity.getN_power()){
				currentTopComplexity.setN_power(currentTopComplexity.getN_power()+oldTopComplexity.getN_power());
				stack.peek().setHighestSubComplexity(currentTopComplexity);
				stack.peek().setUpdated(true);
			}
			if(currentTopComplexity.getN_power() <= oldHighSubTopComplexity.getN_power()){
				currentTopComplexity.setN_power(currentTopComplexity.getN_power()+oldHighSubTopComplexity.getN_power());
				stack.peek().setHighestSubComplexity(currentTopComplexity);
				stack.peek().setUpdated(true);
			}
			if(currentTopComplexity.getLog_power() < oldTopComplexity.getLog_power()){
				currentTopComplexity.setLog_power(currentTopComplexity.getLog_power()+oldTopComplexity.getLog_power());
				stack.peek().setHighestSubComplexity(currentTopComplexity);
				stack.peek().setUpdated(true);
			}
			if(currentTopComplexity.getLog_power() <= oldHighSubTopComplexity.getLog_power()){
				currentTopComplexity.setLog_power(currentTopComplexity.getLog_power()+oldHighSubTopComplexity.getLog_power());
				stack.peek().setHighestSubComplexity(currentTopComplexity);
				stack.peek().setUpdated(true);
			}
		}
		/**
		 * Below runs if the two complexities being compared that are NOT nested
		 * 
		 * Plugging in a large enough value for n is a pretty efficient way to compare the complexities instead of many lines of if cases
		 */
		else {
			double n = 80000.0;
			double logN = Math.log10(n);
			double oldExpression = (Math.pow(n, oldTopComplexity.getN_power())) * (Math.pow(logN , oldTopComplexity.getLog_power()));
			double currentExpression = (Math.pow(n, currentTopComplexity.getN_power())) * (Math.pow(logN , currentTopComplexity.getLog_power()));
			if (oldExpression > currentExpression) {
				stack.peek().setHighestSubComplexity(oldTopComplexity);
				stack.peek().setUpdated(true);
			}
			else
				stack.peek().setUpdated(false);
		}
		
		ifUpdated(stack, oldTop);

	}
	
	/**
	 * This method determines if the current complexity was updated or not
	 * 
	 * Prints the leaving block statements to the console
	 */
	public static void ifUpdated(BlockStack stack, CodeBlock oldTop) {
		if (stack.peek().getUpdated()) {
			
			if ((stack.peek().getHighestSubComplexity().getN_power() == 0) && (stack.peek().getHighestSubComplexity().getLog_power() == 0)) {
				System.out.println("\n    Leaving block "+oldTop.getName()+", nothing to update.");
				System.out.println("       " + stack.peek());
			}
			else {
			System.out.println("\n    Leaving block "+oldTop.getName()+", updating block "+stack.peek().getName()+":");
			System.out.println("       " + stack.peek());
			}
		}
		else{
			System.out.println("\n    Leaving block "+oldTop.getName()+", nothing to update.");
			System.out.println("       " + stack.peek());
		}
	}
	
	/**
	 * This method is used to update the code block name format
	 * @param indents
	 * current lines's number of indents
	 * @return
	 * Integer array of the code block
	 */
	public static int [] updateBlockFormat(int indents) {
		for (int i=0; i < format.length; i++) {
			if (indents == i) {
				format[i] = format[i] + 1;
				if (format[i] != 0)
					format[i+1] = 0;
				break;
			}
		}
		return format;
	}
	
	/**
	 * This method is used to store the current integer array of the code block format as the name when a keyword is found
	 * @param array
	 * Integer array of the code block format
	 * @return
	 * String form of the code block format
	 */
	public static String blockFormatToString(int [] array) {
		String result = "";
		for (int i=0; i< array.length;i++) {
			result += array[i];
			if (array[i+1] != 0)
				result += ".";
			else
				break;
		}
		return result;
	}
	
	/**
	 * This method is used to test if the current line has a keyword
	 * @param line
	 * The current line you are on
	 * @param keywords
	 * String array of the keywords
	 * @return
	 * True if the line contains a keyword, false if not
	 */
	public static boolean lineContainsKeyword(String line, String[] keywords) {
		line = line.trim();
		for(int i=0; i < keywords.length; i++) {
			if(line.startsWith(keywords[i] + " "))
				return true;
			if (i == 5)
				if (line.startsWith(keywords[i] + ":"))
					return true;
	    }
	    return false;
	}
	
	/**
	 * This method is used to get the keyword if the current line contains a keyword
	 * @param line
	 * The current line you are on
	 * @param keywords
	 * String array of the keywords
	 * @return
	 * The keyword the line contains
	 */
	public static String getKeyword(String line, String[] keywords) {
		line = line.trim();
		for(int i=0; i < keywords.length; i++) {
			if(line.startsWith(keywords[i] + " "))
				return keywords[i];
			if (i == 5)
				if (line.startsWith(keywords[i] + ":"))
					return keywords[i];
	    }
	    return null;
	}
	
	/**
	 * This method creates a new O(1) when needed
	 * @return
	 * The new code block created
	 */
	public static CodeBlock createCodeBlock() {
		CodeBlock newCodeBlock = new CodeBlock();
		Complexity blockComplexity = new Complexity(0,0);
		Complexity highestSubComplexity = new Complexity(0,0);

		newCodeBlock.setBlockComplexity(blockComplexity);
		newCodeBlock.setHighestSubComplexity(highestSubComplexity);
		
		return newCodeBlock;
	}
	
	/**
	 * This method is called when a for loop is found: It determines the complexity of the for loop
	 * and creates an appropriate complexity for the new code block complexity 
	 * @param str
	 * The complexity of the for loop
	 * @return
	 * The new code block created
	 */
	public static CodeBlock storeComplexity(String str) {
		if (str.equals("N")) {
			CodeBlock newCodeBlock = new CodeBlock();
			Complexity blockComplexity = new Complexity(1,0);
			Complexity highestSubComplexity = new Complexity(0,0);
			
			newCodeBlock.setBlockComplexity(blockComplexity);
			newCodeBlock.setHighestSubComplexity(highestSubComplexity);
			
			return newCodeBlock;
		}
		else if (str.equals("log_N")) {
			CodeBlock newCodeBlock = new CodeBlock();
			Complexity blockComplexity = new Complexity(0,1);
			Complexity highestSubComplexity = new Complexity(0,0);
			
			newCodeBlock.setBlockComplexity(blockComplexity);
			newCodeBlock.setHighestSubComplexity(highestSubComplexity);
			
			return newCodeBlock;
		}
		else
			return null;
	}
	
	/**
	 * This method is used to determine the complexity based on the loopVariable
	 * Only used with while loops
	 * @param line
	 * Current line you are on
	 * @param variable
	 * Loop variable from the beginning of the while loop
	 * @param stack
	 * Stack of code blocks
	 */
	public static void whileLoop(String line, String loopVariable, BlockStack stack) {
		String [] lineSplit = line.trim().split(" ");

		if (lineSplit[0].equals(loopVariable)) {
			switch(lineSplit[1]) {
			
			case "*=":
				
			case "/=":
				stack.peek().getBlockComplexity().setLog_power(stack.peek().getBlockComplexity().getLog_power() + 1);
				System.out.println("\n    Found update statement, updating block " + stack.peek().getName() + ":\n       " + stack.peek());
				break;
				
			case "+=":
				
			case "-=":
				stack.peek().getBlockComplexity().setN_power(stack.peek().getBlockComplexity().getN_power() + 1);
				System.out.println("\n    Found update statement, updating block " + stack.peek().getName() + ":\n       " + stack.peek());
				break;
			}
		}
	}
	
	/**
	 * This is the main method: takes in a user input of a file name, passes the file to traceFile and prints out the highest complexity of the file
	 * @param args
	 * Array of string arguments
	 * @throws Exception
	 * If the file is not found
	 */
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		String action = "";
		
		while(!action.equalsIgnoreCase("quit")) {
			
			try {
			
				System.out.println("Please enter a file name (or 'quit' to quit):");
				action = input.nextLine().trim();
				if (action.equalsIgnoreCase("quit"))
					break;
				Complexity overallComplexity = traceFile(action);
				String fileName = action.substring(0, action.lastIndexOf("."));
		
				System.out.println("\nOverall complexity of " + fileName + ": " + overallComplexity + "\n");
				
				
			} catch (Exception e) {
				System.out.println("File not found! Please re-enter file name.\n");
			}
		}
		input.close();
		System.out.println("Program terminating successfully...");
	}
	
	/**
	 * This method takes in a fileName and then determines the complexities at each code block and handles them appropriately
	 * @param file
	 * Name of the file
	 * @return
	 * @throws Exception
	 * If the file is null
	 */
	public static Complexity traceFile(String file) throws Exception {
		if (file == null)
			throw new Exception("File is null!");
		BlockStack stack = new BlockStack();
		String currentLine = "";
		File newFile = new File(file);
		Scanner scanFile = new Scanner(newFile);
		
		/**
		 * loop ran for each line of the file until there is no next line
		 */
		while (scanFile.hasNextLine()) {
			currentLine = scanFile.nextLine();
			if ((!currentLine.isBlank()) && (!currentLine.trim().startsWith("#"))) {
				int spaces = 0, indents;
				for (int i=0; i < currentLine.length(); i++) {
					if (currentLine.charAt(i) == ' ')
						spaces++;
					else
						break;
				}
				indents = spaces / SPACE_COUNT;
				/**
				 * Below pops and compares complexities
				 */
				while (indents < stack.size()) {
					if (indents == 0) {
						scanFile.close();
						System.out.println("\n    Leaving block " + stack.peek().getName()+".");
						return stack.pop().getHighestSubComplexity();
					}
					else {

						compareComplexities(stack);
						
					}
				}
				if (lineContainsKeyword(currentLine, CodeBlock.BLOCK_TYPES)) {
					String keyword = getKeyword(currentLine, CodeBlock.BLOCK_TYPES);
					
					switch(keyword) {
					
					case "for":
						int [] forArray = updateBlockFormat(indents);
						int index = currentLine.indexOf(keyword);
						int inIndex = currentLine.indexOf( " in ", index) + 4;
						int symbol = currentLine.indexOf(":", inIndex);
						String forComplexity = currentLine.substring(inIndex, symbol);
						CodeBlock forCodeBlock = storeComplexity(forComplexity);
						forCodeBlock.setName(blockFormatToString(forArray));
						forCodeBlock.setNumOfIndents(indents);
						
						System.out.println("\n    Entering block "+ forCodeBlock.getName() + " '" + keyword + "':" + "\n       " + forCodeBlock);
						
						stack.push(forCodeBlock);
						break;
						
					case "while":
						int [] whileArray = updateBlockFormat(indents);
						
						CodeBlock whileCodeBlock = createCodeBlock();
						
						currentLine = currentLine.trim();
						String noWhileLine = currentLine.substring(6, currentLine.length());
						String loop = noWhileLine.substring(0, noWhileLine.indexOf(" "));
						whileCodeBlock.setLoopVariable(loop);
						whileCodeBlock.setName(blockFormatToString(whileArray));
						whileCodeBlock.setNumOfIndents(indents);
						
						System.out.println("\n    Entering block "+ whileCodeBlock.getName() + " '" + keyword + "':" + "\n       " + whileCodeBlock);
						
						stack.push(whileCodeBlock);
						break;
						
					default:
						int [] defautlArray = updateBlockFormat(indents);
						CodeBlock newCodeBlock = createCodeBlock();
						newCodeBlock.setName(blockFormatToString(defautlArray));
						newCodeBlock.setNumOfIndents(indents);
						
						System.out.println("\n    Entering block "+ newCodeBlock.getName() + " '" + keyword + "':" + "\n       " + newCodeBlock);
						
						stack.push(newCodeBlock);
						break;
					}
				}
				/**
				 * Determines the complexity of the previously found while loop by using the loopVariable
				 */
				else if (!stack.isEmpty()) {
					if ((stack.peek().getLoopVariable() != null) ) {
						whileLoop(currentLine, stack.peek().getLoopVariable(), stack);
					}
				}
				
			}
			else
				continue;
		}
		/**
		 * Below pops and compares complexities
		 */
		while (stack.size() > 1) {

			compareComplexities(stack);
			
		}
		scanFile.close();
		System.out.println("\n    Leaving block " + stack.peek().getName()+".");
		return stack.pop().getHighestSubComplexity();
	}
}
	