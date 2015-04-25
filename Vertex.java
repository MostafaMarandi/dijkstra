import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Vertex {

	public String name; // Vertex name
	public LinkedList<Edge> adjEdges; // Adjacent vertices
	public Vertex prev; // Previous vertex on shortest path
	public double dist; // Distance of path
	public String status = "";
	public int heap_index; //Used to maintain the current position in the Binary Heap
	
	// Reachability's DataStructures
	public String color;
	public Map<String,Vertex> reachMap;
	
	public Vertex(String nm) {
		name = nm;
		adjEdges = new LinkedList<Edge>();
		reachMap = new HashMap<String, Vertex>();
		reset();
	}

	public void reset() {
		dist = WeightedGraph.INFINITY;
		prev = null;
		color = "WHITE";
	}
	
}
