package approved.test1313985257;

import net.egork.utils.test.Test;
import java.util.Collection;
import net.egork.utils.Exit;
import net.egork.utils.io.StreamInputReader;
import java.io.*;
import net.egork.utils.io.old.InputReader;
import net.egork.utils.Solver;
public class Main {
	public static void main(String[] args) {
		InputReader in = new StreamInputReader(System.in);
		PrintWriter out = new PrintWriter(System.out);
		run(in, out);
	}

	public static void run(InputReader in, PrintWriter out) {
		Solver solver = new TaskD();
		solver.solve(1, in, out);
		Exit.exit(in, out);
	}
}


class MainChecker {
	public static String check(InputReader input, InputReader expectedOutput, InputReader actualOutput) {
		return new TaskDChecker().check(input, expectedOutput, actualOutput);
	}

	public static Collection<Test> generateTests() {
		return new TaskDChecker().generateTests();
	}
}

