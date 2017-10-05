import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class ClacRunner {
	public static void main (String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Queue<String>> state = new Stack<Queue<String>>();
		
		boolean again = false;
		do {
			System.out.print("clac>> ");
			String code = scanner.nextLine();
			again = Clac.evaluate(buildQueue(code), stack, state);
			
			printStack(stack);
		}
		while (again);
		
		scanner.close();
	}
	
	private static void printStack(Stack<Integer> stack) {
		System.out.print("stack] ");
		
		for (int num : stack) {
			System.out.print(num + " ");
		}
		
		System.out.println();
	}
	
	private static Queue<String> buildQueue(String code) {
		Queue<String> tokenQueue = new LinkedList<String>();
		
		for (String tok : code.split(" ")) {
			tokenQueue.offer(tok);
		}
		
		return tokenQueue;
	}
}
