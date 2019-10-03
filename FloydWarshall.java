import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// acmicpc
//https://www.acmicpc.net/problem/11404
public class FloydWarshall {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solver();
	}
}

class Solver {
	BufferedWriter bw;
	int T, N, M, K;
	int[][] A;
	int MAX = (int) 1e9 + 1;

	Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());

		A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i != j)
					A[i][j] = MAX;
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[u][v] = Math.min(A[u][v], c);
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					A[i][j] = Math.min(A[i][k] + A[k][j], A[i][j]);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(A[i][j] == MAX ? 0 + " " : A[i][j] + " ");
			}
			System.out.println();
		}

	}
}