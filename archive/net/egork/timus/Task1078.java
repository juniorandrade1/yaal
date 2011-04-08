package net.egork.timus;

import net.egork.arrays.Array;
import net.egork.arrays.ArrayUtils;
import net.egork.collections.CollectionUtils;
import net.egork.collections.Pair;
import net.egork.io.IOUtils;
import net.egork.misc.MiscUtils;
import net.egork.numbers.IntegerUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task1078 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt();
		final Pair<Long, Long>[] segments = Pair.readArray(n, in);
		Integer[] order = ArrayUtils.generateOrder(n);
		Arrays.sort(order, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return IntegerUtils.longCompare(segments[o1].second() - segments[o1].first(),
					segments[o2].second() - segments[o2].first());
			}
		});
		int[] answer = new int[n];
		int[] last = new int[n];
		for (int ii = 0; ii < n; ii++) {
			int i = order[ii];
			answer[i] = 1;
			last[i] = -1;
			for (int jj = 0; jj < ii; jj++) {
				int j = order[jj];
				if (segments[j].first() > segments[i].first() && segments[j].second() < segments[i].second() && answer[j] >= answer[i]) {
					answer[i] = answer[j] + 1;
					last[i] = j;
				}
			}
		}
		int index = ArrayUtils.maxIndex(Array.create(answer));
		out.println(answer[index]);
		List<Integer> path = MiscUtils.getPath(last, index);
		CollectionUtils.increment(path);
		IOUtils.printCollection(path, out);
	}
}
