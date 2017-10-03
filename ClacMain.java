import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ClacMain {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		boolean again = false;
		do {
			String code = scanner.nextLine();
			again = Clac.evaluate(buildQueue(code));
		}
		while (again);
		
		scanner.close();
	}
	
	private static Queue<String> buildQueue(String code) {
		Queue<String> tokenQueue = new LinkedList<String>();
		
		for (String tok : code.split(" ")) {
			tokenQueue.offer(tok);
		}
		
		return tokenQueue;
	}
}
