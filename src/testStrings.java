import java.util.Scanner;
public class testStrings {
	public static void main(String[] args) {
		String s = "y";
		Scanner in = new Scanner(System.in);
		while (s.equalsIgnoreCase("y")) {
			System.out.println("Enter num");
			int i = in.nextInt();
			System.out.println("Enter new selection: ");
			s = in.next();
			System.out.println(s);
		}
	}
}