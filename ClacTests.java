import static org.junit.Assert.assertTrue;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ClacTests {
	Queue<String> tokenQueue;
	Stack<Integer> stack;
	Stack<Queue<String>> state;
	Stack<Integer> resultStack;
	Map<String, String> dict;
	
	@Before
	public void setup() {
		stack = new Stack<Integer>();
		state = new Stack<Queue<String>>();
		dict = new HashMap<String, String>();
	}
	
	
	@Test
	public void a_testPrint_0() {
		Logger.addTask(1, "(arithmetic expressions)");
		Logger.addTest("print-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 print");
		resultStack = buildStack("1 2");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		assertTrue(stack.equals(resultStack));
	}
	
	
	private static Queue<String> buildQueue(String code) {
		Queue<String> tokenQueue = new LinkedList<String>();
		
		for (String tok : code.split(" ")) {
			tokenQueue.offer(tok);
		}
		
		return tokenQueue;
	}
	
	private static Stack<Integer> buildStack(String stackString) {
		Stack<Integer> stack = new Stack<Integer>();
		
		for (String tok : stackString.split(" ")) {
			stack.push(Integer.parseInt(tok));
		}
		
		return stack;
	}
}