import java.util.*;

public class DijkstraTask {
    static class Edge {
        String to; int weight;
        Edge(String to, int w) { this.to = to; this.weight = w; }
    }

    public static void dijkstra(Map<String, List<Edge>> graph, String start, String end) {
        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>(Comparator.comparingInt(dist::get));

        for (String node : graph.keySet()) dist.put(node, Integer.MAX_VALUE);
        dist.put(start, 0);
        pq.add(start);

        while (!pq.isEmpty()) {
            String curr = pq.poll();
            if (curr.equals(end)) break;

            for (Edge edge : graph.getOrDefault(curr, new ArrayList<>())) {
                int newDist = dist.get(curr) + edge.weight;
                if (newDist < dist.get(edge.to)) {
                    dist.put(edge.to, newDist);
                    prev.put(edge.to, curr);
                    pq.add(edge.to);
                }
            }
        }

        // Print path
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = prev.get(at)) path.add(at);
        Collections.reverse(path);

        System.out.println("Shortest path: " + String.join(" -> ", path));
        System.out.println("Total distance: " + dist.get(end));
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();
        // Sample weights for the Scottish road network nodes
        graph.put("Edinburgh", Arrays.asList(new Edge("Perth", 45), new Edge("Stirling", 37)));
        graph.put("Stirling", Arrays.asList(new Edge("Perth", 34)));
        graph.put("Perth", Arrays.asList(new Edge("Dundee", 22)));
        graph.put("Dundee", new ArrayList<>());

        dijkstra(graph, "Edinburgh", "Dundee");
    }
}