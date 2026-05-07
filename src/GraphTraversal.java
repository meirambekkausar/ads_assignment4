import java.util.*;

public class GraphTraversal {
    private Map<String, List<String>> adj = new HashMap<>();

    public void addEdge(String u, String v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
    }

    public void dfs(String start) {
        Set<String> visited = new LinkedHashSet<>();
        System.out.print("DFS Order: ");
        dfsRecursive(start, visited);
        System.out.println();
    }

    private void dfsRecursive(String v, Set<String> visited) {
        visited.add(v);
        System.out.print(v + " ");
        for (String neighbor : adj.getOrDefault(v, new ArrayList<>())) {
            if (!visited.contains(neighbor)) dfsRecursive(neighbor, visited);
        }
    }

    public void bfs(String start) {
        Set<String> visited = new LinkedHashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);

        System.out.print("BFS Order: ");
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
        System.out.println();
    }

    public static void main(String[] args) {
        GraphTraversal g = new GraphTraversal();
        g.addEdge("A", "C"); g.addEdge("A", "B"); g.addEdge("A", "D");
        g.addEdge("B", "C"); g.addEdge("B", "E"); g.addEdge("B", "G");
        g.addEdge("C", "D"); g.addEdge("E", "G"); g.addEdge("E", "F");
        g.addEdge("F", "G");

        g.dfs("A");
        g.bfs("A");
    }
}