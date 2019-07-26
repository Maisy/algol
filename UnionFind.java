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
	int[] P;

	Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new int[N];
		P = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			A[i] = n;
		}


		bw.flush();
		bw.close();
	}

	private boolean union(int a, int b) {
		int ap = parent(a);
		int bp = parent(b);

		if (ap != bp) {
			P[bp] = ap;
			return false;
		}

		return true;
	}

	private int parent(int x) {
		if (x == P[x]) {
			return x;
		}
		P[x] = parent(P[x]);
		return P[x];
	}
}