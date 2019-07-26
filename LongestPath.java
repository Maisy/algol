import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// acmicpc
//https://www.acmicpc.net/problem/10254
class Solver {
	BufferedWriter bw;
	int T;
	int N, M, K;
	int MAX = (int) 1e9;
	ArrayList<Point> A;

	Solver() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		for (int t = 0; t < T; t++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			ArrayList<Point> A = new ArrayList<Point>();
			Point p = new Point(MAX, MAX);
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				A.add(new Point(x, y));
				if (x < p.x || (x == p.x && y < p.y)) {
					p = new Point(x, y);
				}
			}

			Point pp = p;
			Collections.sort(A, (a, b) -> {
				long val = ccw(pp, a, b);
				if (val > 0)
					return -1;
				else if (val < 0)
					return 1;
				return Long.compare(dist(pp, a), dist(pp, b));
			});

			Point[] S = new Point[N];
			int top = 0;
			for (int i = 0; i < N; i++) {
				Point cur = A.get(i);
				while (top > 1 && ccw(S[top - 2], S[top - 1], cur) <= 0) {
					--top;
				}
				S[top++] = cur;
			}

//			for (int i = 0; i < top; i++) {
//				System.out.println(S[i]);
//			}

			int j = 1;
			long max = 0;
			Point sa = null, sb = null;

			for (int i = 0; i < top; i++) {
				int ni = (i + 1) % top;
				for (;;) {
					int nj = (j + 1) % top;
					Point tp = new Point(0, 0);
					Point ta = new Point(S[ni].x - S[i].x, S[ni].y - S[i].y);
					Point tb = new Point(S[nj].x - S[j].x, S[nj].y - S[j].y);
					long val = ccw(tp, ta, tb);
					if (val > 0)
						j = nj;
					else
						break;
				}
				long d = dist(S[i], S[j]);
				if (max < d) {
					max = d;
					sa = S[i];
					sb = S[j];
				}
			}

			bw.write(sa.x + " " + sa.y + " " + sb.x + " " + sb.y + "\n");
		}
		bw.flush();
		bw.close();
	}

	private long dist(Point p, Point a) {
		long dx = a.x - p.x;
		long dy = a.y - p.y;
		return dx * dx + dy * dy;
	}

	private long ccw(Point p, Point a, Point b) {
		return ccw(new Point(a.x - p.x, a.y - p.y), new Point(b.x - p.x, b.y - p.y));
	}

	private long ccw(Point a, Point b) {
		return (long) a.x * b.y - (long) b.x * a.y;
	}

}