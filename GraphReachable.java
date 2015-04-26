import java.util.HashMap;
import java.util.LinkedList;

public class GraphReachable {

	public static void reachable(WeightedGraph g) {

		// Initialize
		for (Vertex v : g.vertexMap.values()) {
			v.color = "WHITE";
			v.reachMap.clear();
		}

		for (Vertex v : g.vertexMap.values()) {
			if (v.color.equals("WHITE")) {
				// BFS VISIT
				v.color = "GREY";
				bfsVisit(v);
				reachedPostProcess(v);
				// printReachable(v);
			}
		}

		for (Vertex v : g.vertexMap.values()) {
			printReachable(v);
		}

	}

	private static void bfsVisit(Vertex s) {

		LinkedList<Vertex> q = new LinkedList<Vertex>();
		q.add(s); // Adding source

		while (!q.isEmpty()) {
			Vertex u = q.removeFirst(); // Constant time operation

			for (Edge edge : u.adjEdges) {
				if (!edge.to.status.equals("DOWN") && !edge.status.equals("DOWN")) {
					
					u.adjMap.put(edge.to.name, edge.to);
					u.reached.put(edge.to.name, edge.to);
					if (edge.to.color.equals("WHITE")) {
					
						edge.to.color = "GREY";
						
						// TODO:Try PUT ALL map
						q.add(edge.to); // Enqueue
					}
				}
			}
			Vertex.reachMap.put(u.name, u.adjMap);

		}

	}

	private static void reachedPostProcess(Vertex v) {
		for (String key1 : v.adjMap.keySet()) {
			for (String key2 : Vertex.reachMap.get(key1).keySet()) {
				if (!key2.equals(v.name) && !v.reached.containsKey(key2)) {
					v.reached.put(key2, Vertex.reachMap.get(key1).get(key2));
				}
			}
		}
	}

	public static void printReachable(Vertex s) {

		System.out.println(s.name);
		for (Vertex v : s.reached.values()) {
			System.out.println("  " + v.name + "  " + v.color);
		}

	}

}
