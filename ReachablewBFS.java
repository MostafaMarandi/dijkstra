import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ReachablewBFS {

	public static void reachableBfs(WeightedGraph g) {

		ArrayList<String> vertexList = new ArrayList<String>(
				g.vertexMap.keySet());
		// Ignore Sort time - This is to print in sorted order
		Collections.sort(vertexList);
		for (String vName : vertexList) {
			// TODO BFS for each vertex

			// Initialization - O(V) times
			resetVertices(g);
			Vertex s = g.vertexMap.get(vName);
			if (s.status.equals("DOWN")) {
				continue;
			}
			s.color = "GREY";
			s.dist = 0.0;
			s.prev = null;

			Queue<Vertex> q = new LinkedList<Vertex>();
			q.add(s);

			while (!q.isEmpty()) {
				Vertex v = q.remove();

				for (Edge e : v.adjEdges) {
					if (!e.to.status.equals("DOWN") && !e.status.equals("DOWN")
							&& e.to.dist == WeightedGraph.INFINITY
							&& e.to.color.equals("WHITE")) {
						
						e.to.color = "GREY";
						e.to.dist = v.dist + 1.0;
						e.to.prev = v;
						q.add(e.to);
					}
				}
				v.color = "BLACK";
				
			}
			System.out.println(vName);

			// TODO Print Before running BFS for next vertex
			printReachable(vertexList, g, vName);
		}

	}

	private static void printReachable(ArrayList<String> vertexList, WeightedGraph g, String source) {
		
		for (String vName : vertexList) {
			if (g.vertexMap.get(vName).color.equals("BLACK") && !vName.equals(source)) {
				System.out.println("  "+vName);
			}
		}
	}

	private static void resetVertices(WeightedGraph g) {

		for (Vertex v : g.vertexMap.values()) {
			v.color = "WHITE";
			v.dist = WeightedGraph.INFINITY;
			v.prev = null;
		}

	}
}
