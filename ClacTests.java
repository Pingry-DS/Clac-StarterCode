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
	public void a_testAddition_0() {
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
	public void a_testAddition_1() {
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
	public void a_testAddition_2() {
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
	public void a_testAddition_3() {
		Logger.addTest("addition-3");
		Logger.expectException();;
		
		tokenQueue = buildQueue("2 +");
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
		} catch (EmptyStackException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
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
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
		} catch (EmptyStackException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
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
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
		} catch (EmptyStackException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
	}
	
	
	@Test
	public void d_testDivision_0() {
		Logger.addSectionBreak();
		Logger.addTest("division-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 4 2 /");
		resultStack = buildStack("2 2");
		
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
	public void d_testDivision_1() {
		Logger.addTest("division-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 4 /");
		resultStack = buildStack("2 0");
		
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
	public void d_testDivision_2() {
		Logger.addTest("division-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 -2147483648 2147483647 /");
		resultStack = buildStack("2 -1");
		
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
	public void d_testDivision_3() {
		Logger.addTest("division-3");
		Logger.expectException();
		
		tokenQueue = buildQueue("2 1 0 /");
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
		} catch (ArithmeticException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
	}
	
	
	@Test
	public void e_testMod_0() {
		Logger.addSectionBreak();
		Logger.addTest("modulo-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 4 2 %");
		resultStack = buildStack("2 0");
		
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
	public void e_testMod_1() {
		Logger.addTest("modulo-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 4 %");
		resultStack = buildStack("2 2");
		
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
	public void e_testMod_2() {
		Logger.addTest("modulo-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 -2147483648 2147483647 %");
		resultStack = buildStack("2 -1");
		
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
	public void e_testMod_3() {
		Logger.addTest("modulo-3");
		Logger.expectException();
		
		tokenQueue = buildQueue("2 1 0 %");
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
		} catch (ArithmeticException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
		
	}
	

	@Test
	public void f_testExponent_0() {
		Logger.addSectionBreak();
		Logger.addTest("exponentiation-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 4 2 **");
		resultStack = buildStack("2 16");
		
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
	public void f_testExponent_1() {
		Logger.addTest("exponentiation-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 4 **");
		resultStack = buildStack("2 16");
		
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
	public void f_testExponent_2() {
		Logger.addTest("exponentiation-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 0 **");
		resultStack = buildStack("2 1");
		
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
	public void f_testExponent_3() {
		Logger.addTest("exponentiation-3");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 -2 **");
		resultStack = buildStack("2 0");
		
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
	public void g_testLess_0() {
		Logger.addTest("less-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 4 <");
		resultStack = buildStack("1");
		
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
	public void g_testLess_1() {
		Logger.addTest("less-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 <");
		resultStack = buildStack("0");
		
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
	public void g_testLess_2() {
		Logger.addTest("less-2");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("3 2 <");
		resultStack = buildStack("0");
		
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
	public void g_testEqual_0() {
		Logger.addSectionBreak();
		
		Logger.addTest("equal-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("2 2 =");
		resultStack = buildStack("1");
		
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
	
	
	/* ******************* */
	/* MANIPULATION TESTS */
	/* ****************** */


	@Test
	public void h_testDrop_0() {
		Logger.addTask(2, "(stack manipulations)");
		Logger.addTest("drop");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 drop");
		resultStack = buildStack("1 2");
		
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
	public void i_testSwap_0() {
		Logger.addSectionBreak();
		Logger.addTest("swap");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 swap");
		resultStack = buildStack("1 3 2");
		
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
	public void j_testRot_0() {
		Logger.addSectionBreak();
		Logger.addTest("rot");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 rot");
		resultStack = buildStack("2 3 1");
		
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
	public void k_testIf_0() {
		Logger.addSectionBreak();
		Logger.addTest("if-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("0 if 1 2 3 4");
		resultStack = buildStack("4");
		
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
	public void k_testIf_1() {
		Logger.addTest("if-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("4 if 1 2 3 4");
		resultStack = buildStack("1 2 3 4");
		
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
	public void l_testPick_0() {
		Logger.addSectionBreak();
		Logger.addTest("pick-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 4 2 pick");
		resultStack = buildStack("1 2 3 4 3");
		
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
	public void l_testPick_1() {
		Logger.addTest("pick-1");
		Logger.expectException();
		
		tokenQueue = buildQueue("1 2 3 4 0 pick");
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
			Logger.noException();
		} catch (IndexOutOfBoundsException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
	}


	@Test
	public void m_testSkip_0() {
		Logger.addSectionBreak();
		Logger.addTest("skip-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("4 2 3 skip - * / **");
		resultStack = buildStack("16");
		
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
	public void m_testSkip_1() {
		Logger.addTest("skip-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue("1 2 3 0 skip + +");
		resultStack = buildStack("6");
		
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
	public void m_testSkip_2() {
		Logger.addTest("skip-2");
		Logger.expectException();
		
		tokenQueue = buildQueue("1 2 3 -1 skip");
		
		boolean thrown = false;
		try {
			Clac.evaluate(tokenQueue, stack, state, dict);
		} catch (IndexOutOfBoundsException e) {
			Logger.pass();
			thrown = true;
		}
		if (!thrown) Logger.noException();
		assertTrue(thrown);
		
	}
	
	
	@Test
	public void n_testFunction_0() {
		Logger.addTask(3, "(functions)");
		Logger.addTest("function-0");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue(": add + ; 2 2 add");
		resultStack = buildStack("4");
		
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
	public void n_testFunction_1() {
		Logger.addTest("function-1");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue(": add + ; 2 2 add : add - ; 3 add");
		resultStack = buildStack("1");
		
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
	public void n_testFunction_2() {
		Logger.addSectionBreak();
		Logger.addTest("decimal-2-binary");
		Logger.expectSuccess();
		
		tokenQueue = buildQueue(": convert 1 pick 2 / dec2bin 10 * swap 2 % + ; : dec2bin 1 pick if convert ; 37 dec2bin");
		resultStack = buildStack("100101");
		
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