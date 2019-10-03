import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2042
class SegmentTree {
	BufferedWriter bw;
	long[] T;
	int N, M, K;
	int MAX = (int) 1e9;
	int TN;

	SegmentTree() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		T = new long[N * 4 + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			set(i, n, 1, 1, N);
		}

		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (type == 1) {
				set(a, b, 1, 1, N);
			} else {
				bw.write(get(a, b, 1, 1, N) + "\n");
			}
		}

		bw.flush();
		bw.close();
	}

	private long get(int x, int y, int idx, int l, int r) {
		if (r < x || y < l) {
			return 0;
		} else if (x <= l && r <= y) {
			return T[idx];
		} else {
			int mid = (l + r) / 2;
			return get(x, y, idx * 2, l, mid) + get(x, y, idx * 2 + 1, mid + 1, r);
		}
	}

	private void set(int pos, int val, int idx, int l, int r) {
		if (l == r) {
			T[idx] = val;
			return;
		}

		int mid = (l + r) / 2;
		if (pos <= mid) {
			set(pos, val, idx * 2, l, mid);
		} else {
			set(pos, val, idx * 2 + 1, mid + 1, r);
		}
		T[idx] = T[idx * 2] + T[idx * 2 + 1];
	}

}