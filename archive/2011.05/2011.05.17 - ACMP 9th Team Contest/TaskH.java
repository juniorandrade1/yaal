import net.egork.collections.ReverseComparator;
import net.egork.collections.sequence.ArrayWrapper;
import net.egork.collections.sequence.SequenceUtils;
import net.egork.io.IOUtils;
import net.egork.utils.Solver;

import java.io.PrintWriter;

public class TaskH implements Solver {
	public void solve(int testNumber, net.egork.utils.io.old.InputReader in, PrintWriter out) {
		int count = in.readInt();
		int[] points = IOUtils.readIntArray(in, count);
		int firstWinner = SequenceUtils.maxIndex(ArrayWrapper.wrap(points));
		int maxPoints = 0;
		for (int i = firstWinner + 1; i < count - 1; i++) {
			if (points[i] % 10 == 5 && points[i] > points[i + 1])
				maxPoints = Math.max(maxPoints, points[i]);
		}
		if (maxPoints == 0)
			out.println(0);
		else {
			SequenceUtils.sort(ArrayWrapper.wrap(points), new ReverseComparator<Integer>());
			out.println(SequenceUtils.find(ArrayWrapper.wrap(points), maxPoints) + 1);
		}
	}
}

