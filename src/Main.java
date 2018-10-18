import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    static final int MAXV = 20100;

    static ArrayList<HashSet<Integer>> graph = new ArrayList<>();
    static HashMap<String, Integer> edges = new HashMap<>();
    static TreeSet<Integer> answer = new TreeSet<>();
    static boolean[] used = new boolean[MAXV];
    static int[] tin = new int[MAXV];
    static int[] up = new int[MAXV];
    static int timer = 0;
    static int edgCounter = 0;

    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] argus = in.readLine().split(" ");
        n = Integer.parseInt(argus[0]);
        m = Integer.parseInt(argus[1]);

        for (int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            argus = in.readLine().split(" ");
            graph.get(Integer.parseInt(argus[0])).add(Integer.valueOf(argus[1]));
            graph.get(Integer.parseInt(argus[1])).add(Integer.valueOf(argus[0]));
            edges.put(argus[0] + " " + argus[1], i + 1);
            edges.put(argus[1] + " " + argus[0], i + 1);
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                dfs(i, -1);
            }
        }

        System.out.println(edgCounter);
        for (int p : answer) {
            System.out.println(p);
        }

        in.close();

    }

    static void dfs(int v, int p) {
        used[v] = true;
        tin[v] = up[v] = timer++;
        for (int u : graph.get(v)) {
            if (u == p) continue;
            if (used[u]) {
                up[v] = Math.min(up[v], tin[u]);
            } else {
                dfs(u, v);
                up[v] = Math.min(up[v], up[u]);
                if (up[u] > tin[v]) {
                    edgCounter++;
                    answer.add(edges.get(u + " " + v));
                }
            }
        }
    }
}
