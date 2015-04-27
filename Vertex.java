/**
 * 
 * Author   : Prem Kumar Murugesan
 * StudentID: 800888499
 * 
 * Gives the structure for Vertex object.
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Vertex {

	public String name; // Vertex name
	public LinkedList<Edge> adjEdges; // Adjacent vertices
	public Vertex prev; // Previous vertex on shortest path
	public double dist; // Distance of path
	public String status = "";
	public int heap_index; // Used to maintain the current position in the
							// Binary Heap

	// Reachability's DataStructures
	public String color;
	// public Map<String,Vertex> reachMap;
	public static Map<String, HashMap<String, Vertex>> reachMap = new HashMap<String, HashMap<String, Vertex>>();
	public HashMap<String, Vertex> reached;
	public HashMap<String, Vertex> adjMap;

	public Vertex(String nm) {
		name = nm;
		adjEdges = new LinkedList<Edge>();
		adjMap = new HashMap<String, Vertex>();
		reached = new HashMap<String, Vertex>();
		// reachMap = new HashMap<String, HashMap<String,Vertex>>();
		reset();
	}

	public void reset() {
		dist = WeightedGraph.INFINITY;
		prev = null;
		color = "WHITE";
	}

}
