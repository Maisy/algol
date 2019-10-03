import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://koitp.org/problem/MILITARY_ROAD_NETWORK/read/
class City {
	int u, v, c, m;

	public City(int u, int v, int c, int m) {
		super();
		this.u = u;
		this.v = v;
		this.c = c;
		this.m = m;
	}

	@Override
	public String toString() {
		return "City [u=" + u + ", v=" + v + ", c=" + c + ", m=" + m + "]";
	}

}

class UnionRank {
	int N, M, K;
	City[] A, B;
	int[] P, R;
	int MAX = (int) 1e9 + 1;

	UnionRank() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		P = new int[N + 1];
		R = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			P[i] = i;
		}

		A = new City[M + K];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[i] = new City(u, v, -c, 0);
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[M + i] = new City(u, v, c, 0);
		}
		Arrays.sort(A, (a, b) -> a.c - b.c);

//		for (int i = 0; i < M + K; i++) {
//			System.out.println(A[i]);
//		}
		long rst = 0;
		for (City cv : A) {
			int cost = cv.c;

			if (union(cv.u, cv.v)) {
				cv.m++;
				if (cost > 0) {
					rst += cost;
				}
			} else {
				if (cost < 0) {
					rst += -cost;
				}
			}
		}
//		for (City city : A) {
//			System.out.println(city);
//		}

		Arrays.sort(A, (a, b) -> {
			if (a.c == b.c) {
				return a.m - b.m;
			}
			return a.c - b.c;
		});
		
		for (int i = 1; i <= N; i++) {
			P[i] = i;
			R[i] = 0;
		}
		
		//check unique
		boolean u = true;
		for (City cv : A) {
			if (union(cv.u, cv.v)) {
				if(cv.m == 0) {
					u = false;
					break;
				}
			}
		}

		System.out.println(rst + " " + (u ? "unique" : "not unique"));
	}

	private boolean union(int a, int b) {
		int ap = find(a);
		int bp = find(b);

		if (ap != bp) {
			if(R[ap] < R[bp]) {
				P[ap] = bp;
			}else if(R[bp] < R[ap]) {
				P[bp] = ap;
			}else {
				P[ap] = bp;
				R[bp]++;
			}
			return true;
		}
		return false;
	}

	private int find(int a) {
		if (a == P[a]) {
			return a;
		}
		return P[a] = find(P[a]);
	}
}