/**
 * This class represents the Big-Oh complexity of some block of code.
 * 
 * @author jasonbokinz, ID: 112555537, R:03
 *
 */
public class Complexity {
	/**
	 * Below are the parameters for Complexity
	 * @param n_power
	 * integer power value for n
	 * @param log_power
	 * integer power value for log
	 */
	private int n_power, log_power;
	
	/**
	 * The constructor creates a default instance of Complexity.
	 */
	public Complexity() {
		
	}
	
	/**
	 * This creates a new Complexity constructor
	 * @param n_power
	 * integer power value for n
	 * @param log_power
	 * integer power value for log
	 */
	public Complexity(int n_power, int log_power) {
		this.n_power = n_power;
		this.log_power = log_power;
	}
	
	/**
	 * This method is used to access Complexity's n_power
	 * @return
	 * n_power (integer power value for n)
	 */
	public int getN_power() {
		return n_power;
	}
	
	/**
	 * This method is used to access Complexity's log_power
	 * @return
	 * log_power (integer power value for log)
	 */
	public int getLog_power() {
		return log_power;
	}
	
	/**
	 * This method is used to change the integer value of n_power
	 * @param n_power
	 * new integer to store as n_power
	 */
	public void setN_power(int n_power) {
		this.n_power = n_power;
	}
	
	/**
	 * This method is used to change the integer value of log_power
	 * @param log_power
	 * new integer to store as log_power
	 */
	public void setLog_power(int log_power) {
		this.log_power = log_power;
	}
	
	/**
	 * This method is used to neatly format Complexity's information
	 * @overrides
	 * Overrides the object class's toString() method
	 */
	public String toString() {
		if(n_power == 0 && log_power == 0)
			return "O(1)";
		else if(n_power == 1 && log_power==1)
			return "O(n * log(n))";
		else if(n_power == 1 && log_power==0)
			return "O(n)";
		else if(log_power == 1 && n_power==0)
			return "O(log(n))";

		else if(log_power == 0 && n_power!=0)
			return "O(n^"+n_power+")";
		else
			if (n_power==1)
				return "O(n * log(n)^"+log_power+")";
			else if (n_power==0)
				return "O(log(n)^"+log_power+")";
			else
				return "O(n^"+n_power+" * log(n)^"+log_power+")";
	}
}
