import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;
    public Edge(int s, int d, int w) { src = s; dest = d; weight = w; }
    public int compareTo(Edge e) { return Integer.compare(this.weight, e.weight); }
}

public class ProjectMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n==============================================");
            System.out.println("   CSEN 3302 / CSE 353 - Algorithms Project   ");
            System.out.println("   Group # 4 Main Menu                        ");
            System.out.println("==============================================");
            System.out.println("1. Algorithm 4: Merging Two Sorted Lists");
            System.out.println("2. Algorithm 7: Selection Problem (k-th Smallest)");
            System.out.println("3. Algorithm 15: Kruskal's MST Algorithm");
            System.out.println("4. Exit");
            System.out.println("==============================================");
            System.out.print("Enter your choice (1-4): ");

            try {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;
                choice = Integer.parseInt(line);

                switch (choice) {
                    case 1 -> runMerge(sc);
                    case 2 -> runSelection(sc);
                    case 3 -> runKruskal(sc);
                    case 4 -> System.out.println("Exiting the program. Goodbye!");
                    default -> System.out.println("Invalid choice! Please select between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        } while (choice != 4);
    }

    // 1. Merging Two Sorted Lists (Algo 4)
        // =================================================
    public static void merge(int[] A, int p, int q, int r) {
        int[] L = Arrays.copyOfRange(A, p, q + 1);
        int[] R = Arrays.copyOfRange(A, q + 1, r + 1);

        int i = 0, j = 0, k = p;
        while (i < L.length && j < R.length) {
            A[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < L.length) A[k++] = L[i++];
        while (j < R.length) A[k++] = R[j++];
    }

    public static void runMerge(Scanner sc) {
        System.out.println("\n--- 1. Merging Two Sorted Lists ---");
        System.out.print("Enter array size (m): ");
        int m = Integer.parseInt(sc.nextLine().trim());
        
        int[] A = new int[m];
        System.out.println("Enter " + m + " elements (separated by space):");
        String[] parts = sc.nextLine().trim().split("\\s+");
        for (int i = 0; i < m; i++) A[i] = Integer.parseInt(parts[i]);

        System.out.println("Enter indices p, q, r (e.g., 0 2 4):");
        String[] pqr = sc.nextLine().trim().split("\\s+");
        int p = Integer.parseInt(pqr[0]);
        int q = Integer.parseInt(pqr[1]);
        int r = Integer.parseInt(pqr[2]);

        if (p < 0 || p > q || q >= r || r >= m) {
            System.out.println("Error: Invalid indices! Ensure that 0 <= p <= q < r < " + m);
            return;
        }

        merge(A, p, q, r);
        System.out.println("Merged Array: " + Arrays.toString(A));
    }

    // 2. Selection Problem - Median-of-Medians (Algo 7)
    // ===========================================
    public static int select(int[] a, int k) {
        if (a.length <= 5) {
            Arrays.sort(a);
            return a[k - 1];
        }
        int groups = (int) Math.ceil(a.length / 5.0);
        int[] medians = new int[groups];
        for (int i = 0; i < groups; i++) {
            int subLen = Math.min(5, a.length - i * 5);
            int[] sub = new int[subLen];
            System.arraycopy(a, i * 5, sub, 0, subLen);
            Arrays.sort(sub);
            medians[i] = sub[subLen / 2];
        }
        int pivot = select(medians, (medians.length + 1) / 2);

        List<Integer> L = new ArrayList<>(), E = new ArrayList<>(), G = new ArrayList<>();
        for (int x : a) {
            if (x < pivot) L.add(x);
            else if (x == pivot) E.add(x);
            else G.add(x);
        }

        if (k <= L.size()) return select(L.stream().mapToInt(Integer::intValue).toArray(), k);
        if (k <= L.size() + E.size()) return pivot;
        return select(G.stream().mapToInt(Integer::intValue).toArray(), k - L.size() - E.size());
    }

    public static void runSelection(Scanner sc) {
        System.out.println("\n--- 2. Selection Problem (k-th Smallest) ---");
        System.out.print("Enter array size (n): ");
        int n = Integer.parseInt(sc.nextLine().trim());
        
        int[] A = new int[n];
        System.out.println("Enter " + n + " elements (separated by space):");
        String[] parts = sc.nextLine().trim().split("\\s+");
        for (int i = 0; i < n; i++) A[i] = Integer.parseInt(parts[i]);

        System.out.print("Enter k (1.." + n + "): ");
        int k = Integer.parseInt(sc.nextLine().trim());

        if (k < 1 || k > n) {
            System.out.println("Error: k must be between 1 and " + n);
            return;
        }

        System.out.println("The " + k + "-th Smallest Element is: " + select(A, k));
    }

    // 3. Kruskal's MST Algorithm (Algo 15)
    // =========================================================
    public static void runKruskal(Scanner sc) {
        System.out.println("\n--- 3. Kruskal's MST Algorithm ---");
        System.out.print("Enter Vertices (V) and Edges (E) separated by space: ");
        String[] ve = sc.nextLine().trim().split("\\s+");
        int V = Integer.parseInt(ve[0]);
        int E = Integer.parseInt(ve[1]);

        List<Edge> edges = new ArrayList<>();
        System.out.println("Enter " + E + " edges in format (src dest weight):");
        for (int i = 0; i < E; i++) {
            String[] line = sc.nextLine().trim().split("\\s+");
            edges.add(new Edge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2])));
        }

        Collections.sort(edges);
        int[] parent = new int[V + 1];
        for (int i = 1; i <= V; i++) parent[i] = i;

        int totalWeight = 0, count = 0;
        System.out.println("\nMST Edges:");
        for (Edge e : edges) {
            int rootSrc = find(parent, e.src);
            int rootDest = find(parent, e.dest);

            if (rootSrc != rootDest) {
                System.out.println("(" + e.src + " - " + e.dest + ") Weight: " + e.weight);
                totalWeight += e.weight;
                parent[rootSrc] = rootDest;
                if (++count == V - 1) break;
            }
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    private static int find(int[] parent, int i) {
        if (parent[i] == i) return i;
        return parent[i] = find(parent, parent[i]);
    }
}
