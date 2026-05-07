import java.util.*;

public class GraphTraversal {
    private Map<String, List<String>> adj = new HashMap<>();

    public void addEdge(String u, String v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void dfs(String start, Set<String> visited) {
        visited.add(start);
        System.out.print(start + " ");
        for (String neighbor : adj.getOrDefault(start, new ArrayList<>())) {
            if (!visited.contains(neighbor)) dfs(neighbor, visited);
        }
    }

    public void bfs(String start) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            String v = queue.poll();
            System.out.print(v + " ");
            for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GraphTraversal g = new GraphTraversal();


        System.out.print("Enter number of edges: ");
        int edges = sc.nextInt();

        System.out.println("Enter edges (source destination):");
        for (int i = 0; i < edges; i++) {
            g.addEdge(sc.next(), sc.next());
        }

        System.out.print("Enter starting node for DFS/BFS: ");
        String startNode = sc.next();

        System.out.print("\nDFS Traversal result: ");
        g.dfs(startNode, new HashSet<>());

        System.out.print("\nBFS Traversal result: ");
        g.bfs(startNode);

        sc.close();
    }
}