import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Clac {
	public static boolean evaluate(Queue<String> tokenQueue, Stack<Integer> stack, Stack<Queue<String>> state, Map<String, String> dict) {
		String token;
		
		while(!tokenQueue.isEmpty()) {
			token = tokenQueue.poll();
			
			//Print
			if (token.equals("print")) {
				int x = stack.pop(); //Handle Error
				System.out.println(x);
			} 
		    
			//Quit
			else if (token.equals("quit")) {
				return false;
			} 
			
			else {
				int number = Integer.parseInt(token); //Handle Error
				stack.push(number);
			}   
		}
		
		return true;
	}
}
