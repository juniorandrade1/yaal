package net.egork.y2011.m4.d2;

import net.egork.utils.solver.Solver;
import net.egork.utils.io.inputreader.InputReader;

import java.io.PrintWriter;

public class NumberGameRevisited implements Solver {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.readInt() - 1;
		if ((n & 3) == 0)
			out.println("ALICE");
		else
			out.println("BOB");
	}
}
