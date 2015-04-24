import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class WeightedGraph {
	public static final double INFINITY = Double.MAX_VALUE;
	private Map<String, Vertex> vertexMap = new HashMap<String, Vertex>();

	public void addBiEdge(String sourceName, String destName, double cost) {
		Vertex u = getVertex(sourceName);
		Vertex v = getVertex(destName);
		Edge uv = new Edge(u, v, cost);
		Edge vu = new Edge(v, u, cost);
		u.adjEdges.add(uv);
		v.adjEdges.add(vu);
		// Debug Message: - Created printGraph so disabling this
		// System.out.println(uv.toString());
		// System.out.println(vu.toString());
	}

	/**
	 * Add an unidirectional edge from/to a new vertex, or modify the cost
	 * between two existing vertices in one direction (From tailName to
	 * headName)
	 */
	public void addEdge(String tailName, String headName, double cost) {
		Boolean updated = false;
		if (vertexMap.get(tailName) != null && vertexMap.get(headName) != null) {
			Vertex u = getVertex(tailName);
			Vertex v = getVertex(headName);
			for (Edge edge : u.adjEdges) {
				if (edge.to.name.equals(headName)) {
					// Edge already exist so Updated
					edge.cost = cost;
					updated = true;
				}
			}
			if (!updated) {
				Edge uv = new Edge(u, v, cost);
				u.adjEdges.add(uv);
			}
		} else {
			Vertex u = getVertex(tailName);
			Vertex v = getVertex(headName);
			Edge uv = new Edge(u, v, cost);
			// System.out.println("String from"+uv.toString());
			u.adjEdges.add(uv);
		}

	}

	/**
	 * Delete an edge from tailVertex to headVertex if exists (Opposite
	 * direction edge will remain unchanged)
	 */
	public void deleteEdge(String tailVertex, String headVertex) {
		if (vertexMap.get(tailVertex) != null
				&& vertexMap.get(headVertex) != null) {
			Vertex u = getVertex(tailVertex);
			for (Edge edge : u.adjEdges) {
				if (edge.to.name.equals(headVertex)) {
					u.adjEdges.remove(edge);
					break;
				}
			}
		}
	}

	/**
	 * Mark the directed edge as 'DOWN' and therefore unavailable for use if
	 * there is an edge from tailVertex and headVertex
	 */
	public void edgeDown(String tailVertex, String headVertex) {
		if (vertexMap.get(tailVertex) != null
				&& vertexMap.get(headVertex) != null) {
			Vertex u = getVertex(tailVertex);
			for (Edge edge : u.adjEdges) {
				if (edge.to.name.equals(headVertex)) {
					edge.status = "DOWN";
				}
			}
		}
	}

	/**
	 * UnMark the directed edge from 'DOWN' to empty string and therefore
	 * available for use if there is an edge from tailVertex and headVertex
	 */
	public void edgeUp(String tailVertex, String headVertex) {
		if (vertexMap.get(tailVertex) != null
				&& vertexMap.get(headVertex) != null) {
			Vertex u = getVertex(tailVertex);
			for (Edge edge : u.adjEdges) {
				if (edge.to.name.equals(headVertex)) {
					edge.status = "";
				}
			}
		}
	}

	public void vertexDown(String vertex) {
		if (vertexMap.get(vertex) != null) {
			Vertex v = getVertex(vertex);
			v.status = "DOWN";
		}
	}

	public void vertexUp(String vertex) {
		if (vertexMap.get(vertex) != null) {
			Vertex v = getVertex(vertex);
			v.status = "";
		}
	}

	/**
	 * If vertexName is not present, it adds a vertex to vertexMap with the
	 * given name, else return the existing Vertex for the given name.
	 */
	private Vertex getVertex(String vertexName) {
		Vertex w = vertexMap.get(vertexName);
		if (w == null) {
			w = new Vertex(vertexName);
			vertexMap.put(vertexName, w);
		}
		return w;
	}

	/**
	 * Prints the entire graph with its reachable vertices, cost and status of
	 * the vertex(only if DOWN) Sorted the vertices using their name
	 */
	public void printGraph() {
		ArrayList<String> sortedVertex = new ArrayList<String>(
				vertexMap.keySet());
		Collections.sort(sortedVertex);
		for (String key : sortedVertex) {
			Vertex w = vertexMap.get(key);
			System.out.println(w.name + " " + w.status);
			ArrayList<String> neighborList = new ArrayList<String>();
			for (Edge edge : w.adjEdges) {
				neighborList.add(edge.to.name + " " + edge.cost + " "
						+ edge.status);
			}
			Collections.sort(neighborList);
			for (String neighbor : neighborList) {
				System.out.println("  " + neighbor);
			}
		}
	}

	/**
	 * Dijkstra's Algorithm
	 */

	// 1 function Dijkstra(Graph, source):
	// 2
	// 3 dist[source] ← 0 // Distance from source to source
	// 4 prev[source] ← undefined // Previous node in optimal path
	// initialization
	// 5
	// 6 for each vertex v in Graph: // Initialization
	// 7 if v ≠ source // Where v has not yet been removed from Q (unvisited
	// nodes)
	// 8 dist[v] ← infinity // Unknown distance function from source to v
	// 9 prev[v] ← undefined // Previous node in optimal path from source
	// 10 end if
	// 11 add v to Q // All nodes initially in Q (unvisited nodes)
	// 12 end for
	// 13
	// 14 while Q is not empty:
	// 15 u ← vertex in Q with min dist[u] // Source node in first case
	// 16 remove u from Q
	// 17
	// 18 for each neighbor v of u: // where v is still in Q.
	// 19 alt ← dist[u] + length(u, v)
	// 20 if alt < dist[v]: // A shorter path to v has been found
	// 21 dist[v] ← alt
	// 22 prev[v] ← u
	// 23 end if
	// 24 end for
	// 25 end while
	// 26
	// 27 return dist[], prev[]
	// 28
	// 29 end function

	public void dijPath(String startName) {

		// Initialization
		for (Vertex v : vertexMap.values()) {
			v.dist = INFINITY;
			v.prev = null;
		}

		// Initial Check
		Vertex start = vertexMap.get(startName);
		if (start == null) {
			System.out.println("Start vertex not found!");
			return;
		}
		if (start.status.equals("DOWN")) {
			System.out.println("Start vertex is DOWN!");
			return;
		}

		start.dist = 0.0;
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		for (Vertex v : vertexMap.values()) {
			if (!v.status.equals("DOWN")) {
				q.add(v);
			}
		}

		// Algorithm
		while (!q.isEmpty()) {

			Vertex u = returnMin(q);
			q.remove(u);

			for (Edge edge : u.adjEdges) {
				if (!edge.to.status.equals("DOWN")
						&& !edge.status.equals("DOWN")) {
					double alt = u.dist + edge.cost;
					alt = (double) Math.round(alt * 100) / 100;
					if (alt < edge.to.dist) {
						edge.to.dist = alt;
						edge.to.prev = u;
					}
				}
			}

		}
	}

	/**
	 * Takes destination vertex name as destination name and convert to the
	 * Vertex object and calls the printDijPath Method
	 */
	public void printPath(String destName) {
		Vertex w = vertexMap.get(destName);
		if (w == null)
			System.out.println("Destination vertex not found");
		else if (w.dist == INFINITY)
			System.out.println(destName + " is unreachable");
		else {
			System.out.print("(Distance is: " + w.dist + ") ");
			printDijPath(w);
			System.out.println();
		}
	}

	/**
	 * Recursive method prints the path between the source and destination
	 */
	private void printDijPath(Vertex end) {
		if (end.prev != null) {
			printDijPath(end.prev);
			System.out.print(" to ");
		}
		System.out.print(end.name);
	}

	private Vertex returnMin(LinkedList<Vertex> l) {

		Vertex v = l.getFirst();
		double currentMin = INFINITY;
		for (Vertex i : l) {
			if (currentMin > i.dist) {
				currentMin = i.dist;
				v = i;
			}
		}
		return v;
	}

}
