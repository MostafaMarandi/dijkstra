import java.util.LinkedList;


public class Vertex {

	public String name; // Vertex name
	public LinkedList<Edge> adjEdges; // Adjacent vertices
	public Vertex prev; // Previous vertex on shortest path
	public int dist; // Distance of path

	public Vertex(String nm) {
		name = nm;
		adjEdges = new LinkedList<Edge>();
		reset();
	}

	public void reset() {
		dist = WeightedGraph.INFINITY;
		prev = null;
	}
	
}
