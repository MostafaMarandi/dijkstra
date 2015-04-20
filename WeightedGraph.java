import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightedGraph {
	public static final int INFINITY = Integer.MAX_VALUE;
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
		if (vertexMap.get(tailName) != null && vertexMap.get(headName) != null) {
			Vertex u = getVertex(tailName);
			for (Edge edge : u.adjEdges) {
				if (edge.to.name.equals(headName)) {
					edge.cost = cost;
				}
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

	
	public void vertexDown(String vertex){
		if (vertexMap.get(vertex)!=null) {
			Vertex v = getVertex(vertex);
			v.status = "DOWN";
		}
	}
	
	public void vertexUp(String vertex){
		if (vertexMap.get(vertex)!=null) {
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
			// TODO: Add the status for DOWN nodes
			System.out.println(w.name+" "+w.status);
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

}
