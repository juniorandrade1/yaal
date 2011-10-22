package approved.test1421529331;

import net.egork.utils.checker.Checker;
import net.egork.utils.io.old.InputReader;
import java.util.Collections;
import java.util.Collection;
import net.egork.utils.test.Test;

public class TaskCChecker extends Checker {
	@Override
	public String check(InputReader input, InputReader expectedOutput, net.egork.utils.io.old.InputReader actualOutput) {
		return tokenCheck(expectedOutput, actualOutput);
	}

	@Override
	public double getCertainty() {
		return 0;
	}

	@Override
	public Collection<Test> generateTests() {
		return Collections.<Test>emptyList();
	}
}

