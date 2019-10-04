import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class LCA {
	BufferedWriter bw;
	int T, N, M;
	int[] D;
	int[][] P;
	ArrayList<Integer>[] A;
	int MAX = (int) 1e9 + 1;
	int K;

	LCA() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		K = 1;
		for (int i = 1; i <= N; i*=2) {
			K++;
		}

		A = new ArrayList[N + 1];
		P = new int[N + 1][K + 1];
		D = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = new ArrayList<Integer>();
			P[i][0] = i;
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			A[u].add(v);
			A[v].add(u);
		}

		dfs(1, 1, 0);

		for (int j = 0; j < K; j++) {
			for (int i = 1; i <= N; i++) {
				P[i][j + 1] = P[P[i][j]][j];
			}
		}

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			bw.write(lca(u, v) + "\n");
		}

		bw.flush();
		bw.close();

	}

	private void dfs(int cv, int d, int p) {
		D[cv] = d;
		P[cv][0] = p;
		for (int nv : A[cv]) {
			if (nv != p) {
				dfs(nv, d + 1, cv);
			}
		}
	}

	private int lca(int u, int v) {
		int a = D[u] > D[v] ? u : v;
		int b = D[u] > D[v] ? v : u;

		int diff = D[a] - D[b];
		for (int i = 0; i <= K; i++) {
			if ((diff & (1 << i)) > 0) {
				a = P[a][i];
			}
		}

		if (a == b) {
			return a;
		}

		for (int i = K; i >= 0; i--) {
			if (P[a][i] != P[b][i]) {
				a = P[a][i];
				b = P[b][i];
			}
		}

		return P[a][0];
	}

}