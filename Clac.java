import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Clac {
	public static boolean evaluate(Queue<String> tokenQueue, Stack<Integer> stack, Stack<Queue<String>> state, Map<String, Queue<String>> dict) {
		String token;
		
		while(!tokenQueue.isEmpty() || !state.isEmpty()) {
			if (tokenQueue.isEmpty()) {
				tokenQueue = state.pop();
			}
			
			token = tokenQueue.poll();
			
			//Print
			if (token.equals("print")) {
				int x = stack.pop();
				System.out.println(x);
			} 
		    
			//Quit
			else if (token.equals("quit")) {
				return false;
			} 
			
			
			
			/* ************************************** */
			/*   WRITE ALL OF YOUR TASK 1 CODE HERE   */
			/* DO NOT MODIFY ANY CODE BELOW THIS AREA */
			/* ************************************** */
			
			
			//Function definition
			else if (token.equals(":")) {
				Queue<String> functionQueue = new LinkedList<String>();
				
				/* ******************************* *
				/* FINISH FUNCTION DEFINITION HERE */
				/* ******************************* */
				
				dict.put(functionQueue.poll(), functionQueue);
				
			}
			
			else {
				Queue<String> dictQueue = dict.get(token);
				if (dictQueue != null) {
					Queue<String> functionQueue = copyQueue(dictQueue);
					Queue<String> currentQueue = copyQueue(tokenQueue);
					if (!currentQueue.isEmpty()) state.push(currentQueue);
					
					tokenQueue = functionQueue;
					 
				}
				else {
					int number = Integer.parseInt(token); //Handle Error
					stack.push(number);
				}
			}   
		}
		
		return true;
	}
	
	private static Queue<String> copyQueue(Queue<String> queue) {
		Queue<String> newQueue = new LinkedList<String>();
		Queue<String> oldQueue = new LinkedList<String>();
		
		while(!queue.isEmpty()) {
			newQueue.offer(queue.peek());
			oldQueue.offer(queue.poll());
		}
		
		while(!oldQueue.isEmpty()) {
			queue.offer(oldQueue.poll());
		}
		
		
		return newQueue;
	}
}
