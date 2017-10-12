import static org.junit.Assert.assertTrue;

import java.util.EmptyStackException;
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
	Map<String, Queue<String>> dict;
	
	@Before
	public void setup() {
		stack = new Stack<Integer>();
		state = new Stack<Queue<String>>();
		dict = new HashMap<String, Queue<String>>();
	}
	
	
	/* **************************** */
	/* ARITHMETIC EXPRESSIONS TESTS */
	/* **************************** */
	
	
	@Test
	public void a_testAdd_0() {
		Logger.addTask(1, "(arithmetic expressions)");
		Logger.addTest("addition-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 +");
		resultStack = buildStack("3");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void a_testAdd_1() {
		Logger.addTest("addition-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("3 1 2 + 2 +");
		resultStack = buildStack("3 5");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void a_testAdd_2() {
		Logger.addTest("addition-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("-1 1 + 2 +");
		resultStack = buildStack("2");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void a_testAdd_3() {
		Logger.addTest("addition-3");
		Logger.expectException();;
		
		tokenQueue = buildQueue("2 +");
		
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
			assertTrue(false);
		} catch (EmptyStackException e) {
			Logger.pass();
			assertTrue(true);
		}
	}
	
	@Test
	public void b_testSubtraction_0() {
		Logger.addSectionBreak();
		Logger.addTest("subtraction-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("3 1 2 -");
		resultStack = buildStack("3 -1");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void b_testSubtraction_1() {
		Logger.addTest("subtraction-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 -2 -");
		resultStack = buildStack("3");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void b_testSubtraction_2() {
		Logger.addTest("subtraction-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("-1 2 -");
		resultStack = buildStack("-3");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void b_testSubtraction_3() {
		Logger.addTest("subtraction-3");
		Logger.expectException();
		
		tokenQueue = buildQueue("-1 -");
		
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
			assertTrue(false);
		} catch (EmptyStackException e) {
			Logger.pass();
			assertTrue(true);
		}
		
	}
	
	@Test
	public void c_testMultiplication_0() {
		Logger.addSectionBreak();
		Logger.addTest("multiplication-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("3 3 -2 *");
		resultStack = buildStack("3 -6");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void c_testMultiplication_1() {
		Logger.addTest("multiplication-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("3 2 " + Integer.MAX_VALUE + " *");
		resultStack = buildStack("3 -2");
		
		Clac.evaluate(tokenQueue, stack, state, dict);
		
		if (stack.equals(resultStack)) {
			Logger.pass();
			assertTrue(true);
		}
		else {
			Logger.wrongValue();
			assertTrue(false);
		}
		
	}
	
	@Test
	public void c_testMultiplication_2() {
		Logger.addTest("multiplication-2");
		Logger.expectException();
		
		tokenQueue = buildQueue("-1 *");
		
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
			assertTrue(false);
		} catch (EmptyStackException e) {
			Logger.pass();
			assertTrue(true);
		}
		
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