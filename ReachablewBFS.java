/**
 * 
 * Author   : Prem Kumar Murugesan
 * StudentID: 800888499
 * 
 * Implemented an algorithm using BFS graph traversal to find all the reachable nodes 
 * from all nodes in the graph with the exponential running time of O(V(V+E))
 * 
 * Note: Running time computation are in the comments.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class ReachablewBFS {

	public static void reachableBfs(WeightedGraph g) {

		//Copy the key into an arraylist to sort
		ArrayList<String> vertexList = new ArrayList<String>(
				g.vertexMap.keySet());
		// Ignore Sort time - This is to print in sorted order
		Collections.sort(vertexList);
		
		
		for (String vName : vertexList) {

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

			// Each vertex is enqueued and dequeued at most once, and each operation takes O (1) . 
			// So, total time for queuing is O(V)
			while (!q.isEmpty()) {
				Vertex v = q.remove();

				// The adjacency list of each vertex is scanned at most once. The
				// sum of lengths of all adjacency lists is Θ (|E|)
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
			// Total running time of running BFS for each vertex is O(V+E)
			// So we have V vertex and it takes O(V(V+E)) in total to run this reachable algorithm
			// But to print in the sorted order it takes some additional time which can be ommited
			
			System.out.println(vName);

			//Running time of print: V(V-1)
			printReachable(vertexList, g, vName);
		}

	}

	private static void printReachable(ArrayList<String> vertexList,
			WeightedGraph g, String source) {

		for (String vName : vertexList) {
			if (g.vertexMap.get(vName).color.equals("BLACK")
					&& !vName.equals(source)) {
				System.out.println("  " + vName);
			}
		}
	}

	// Running time is O(V)
	private static void resetVertices(WeightedGraph g) {

		for (Vertex v : g.vertexMap.values()) {
			v.color = "WHITE";
			v.dist = WeightedGraph.INFINITY;
			v.prev = null;
		}
	}
}
