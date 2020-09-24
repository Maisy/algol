import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Dijkstra {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solver();
	}
}

class Node {
	int u, k;
	long c;

	public Node(int u, int k, long c) {
		super();
		this.u = u;
		this.k = k;
		this.c = c;
	}

}

class Solver {
	BufferedWriter bw;
	int T, N, M, K;
	ArrayList<Point>[] A;
	long[][] D;
	int S, E;
	long MAX = (long) 1e18 + 1;

	Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		A = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<Point>();
		}
		D = new long[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j <= K; j++) {
				D[i][j] = MAX;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[u].add(new Point(v, c));
			A[v].add(new Point(u, c));
		}

		PriorityQueue<Node> q = new PriorityQueue<Node>((a, b) -> {
			return a.c > b.c ? 1 : -1;
		});
		q.add(new Node(1, 0, 0));
		D[1][0] = 0;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			int cv = cur.u;
			int ck = cur.k;
			long cost = cur.c;
			if (D[cv][ck] < cost) {
				continue;
			}

			for (Point n : A[cv]) {
				if (D[n.x][ck] > cost + n.y) {
					D[n.x][ck] = cost + n.y;
					q.add(new Node(n.x, ck, D[n.x][ck]));
				}

				if (ck < K && D[n.x][ck + 1] > cost) {
					D[n.x][ck + 1] = cost;
					q.add(new Node(n.x, ck + 1, D[n.x][ck + 1]));
				}
			}
		}

		long sol = MAX;
		for (int i = 0; i <= K; i++) {
			sol = Math.min(sol, D[N][i]);
		}

		bw.write(sol + "\n");
		bw.flush();
		bw.close();
	}

}
