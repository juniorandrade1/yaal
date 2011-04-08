package net.egork.timus;

import net.egork.arrays.Array;
import net.egork.arrays.ArrayUtils;
import net.egork.utils.io.inputreader.InputReader;
import net.egork.utils.solver.Solver;

import java.io.PrintWriter;

public class Task1210 implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int[] last = new int[1];
		int n = in.readInt();
		for (int it = 0; it < n; it++) {
			if (it != 0)
				in.readCharacter();
			int[] next = new int[in.readInt()];
			for (int i = 0; i < next.length; i++) {
				next[i] = Integer.MAX_VALUE;
				while (true) {
					int source = in.readInt() - 1;
					if (source == -1)
						break;
					int cost = in.readInt();
					next[i] = Math.min(next[i], last[source] + cost);
				}
			}
			last = next;
		}
		out.println(last[ArrayUtils.minIndex(Array.create(last))]);
	}
}
