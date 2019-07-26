import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solver {
	BufferedWriter bw;
	long[] T;
	int N, M, K;
	int MAX = (int) 1e9;
	int TN;
	int[] A;

	Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		for (TN = 1; TN < N; TN *= 2)
			;
		T = new long[TN * 2];

		A = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			A[i] = n;
		}

		tree(1); // init tree
		

		bw.flush();
		bw.close();
	}

	private void set(int idx, long val) {
		int i = TN + idx - 1;
		T[i] = val;

		while (i > 1) {
			i /= 2;
			T[i] = T[i * 2] + T[i * 2 + 1];
		}
	}

	private long get(int left, int right) {
		int l = left + TN - 1;
		int r = right + TN - 1;

		long s = 0;

		while (l <= r) {
			if (l % 2 == 1) {
				s += T[l];
				l += 1;
			}
			if (r % 2 == 0) {
				s += T[r];
				r -= 1;
			}
			l /= 2;
			r /= 2;
		}

		return s;
	}

	private long tree(int idx) {
		if (idx >= TN) {
			T[idx] = idx - TN + 1 <= N ? A[idx - TN + 1] : 0;
			return T[idx];
		}
		T[idx] = tree(idx * 2) + tree(idx * 2 + 1);
		return T[idx];

	}
}