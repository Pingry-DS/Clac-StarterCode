import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ClacTester {

	public static void main(String[] args) {
		//Run the test classes
		Result result = JUnitCore.runClasses(ClacTests.class);

		logStatistics(result);
		System.out.println(Logger.log);

		try {
			Logger.export("clac-tests");
		} catch (IOException e) {
			System.out.print("Error while exporting log.");
		}
		
	}

	public static void logStatistics(Result result) {
		Logger.logln("\n==================================================");
		Logger.logln("Tests run in: " + result.getRunTime() +" ms");
		Logger.logln("Tests passed: " + (result.getRunCount() - result.getFailureCount()));
		Logger.logln("Tests failed: " + result.getFailureCount());
	   }
	
}




class Logger {
	public static String log = "\n";

	  public static void addTask(int t) {
	    log = log.concat("\n==================================================\nTask " + t + "\n--------------------------------------------------\n");
	  }

	  public static void addTask(int t, String s) {
	    log = log.concat("\n==================================================\nTask " + t + ", " + s + "\n--------------------------------------------------\n");
	  }

	  public static void addMajorSectionBreak() {
	    log = log.concat("==================================================\n\n");
	  }

	  public static void addSectionBreak() {
	    log = log.concat("--------------------------------------------------\n");
	  }

	  public static void addTest(String s) {
	    log = log.concat("Test " + s);
	  }

	  public static void expectSuccess() {
	    log = log.concat(", expect successful execution");
	  }

	  public static void expectException() {
	    log = log.concat(", expect exception");
	  }

	  public static void pass() {
	    log = log.concat(" - good, this worked\n");
	  }

	  public static void wrongValue() {
	    int index = log.lastIndexOf("Test");
	    if (index >= 2 && log.charAt(index-2) != '-') log = log.substring(0, index) + "--------------------------\n" + log.substring(index);
	    log = log.concat("\n*** TEST FAILED!\n*** Actual outcome: The program, when run, produced the wrong answer\n--------------------------\n");
	  }

	  public static void noException() {
	    int index = log.lastIndexOf("Test");
	    if (index >= 2 && log.charAt(index-2) != '-') log = log.substring(0, index) + "--------------------------\n" + log.substring(index);
	    log = log.concat("\n*** TEST FAILED!\n*** Actual outcome: The program, when run, did not throw an exception when it should have\n--------------------------\n");
	  }

	  public static void fail(String s) {
	    int index = log.lastIndexOf("Test");
	    if (index >= 2 && log.charAt(index-2) != '-') log = log.substring(0, index) + "--------------------------\n" + log.substring(index);
	    log = log.concat("\n*** TEST FAILED!\n*** Actual outcome: " + s + "\n--------------------------\n");
	  }

	  public static void log(String s) {
	    log = log.concat(s);
	  }

	  public static void logln(String s) {
	    log = log.concat(s).concat("\n");
	  }

	  public static void export() throws IOException{
	    BufferedWriter writer = new BufferedWriter(new FileWriter("test.log"));
	    writer.write(log);
	    writer.close();
	    System.out.println("This log has been saved in the \"test.log\" file.\n");
	  }

	  public static void export(String s) throws IOException{
	    BufferedWriter writer = new BufferedWriter(new FileWriter(s + ".log"));
	    writer.write(log);
	    writer.close();
	    System.out.println("This log has been saved in the \"" + s + ".log\" file.\n");
	  }
}