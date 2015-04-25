import java.util.LinkedList;


public class GraphReachable {

	public static void reachable(WeightedGraph g){
		
		//Initialize
		for (Vertex v : g.vertexMap.values()) {
			v.color = "WHITE";
			v.reachMap.clear();
		}
		
		for (Vertex v : g.vertexMap.values()) {
			if (v.color.equals("WHITE")) {
				// BFS VISIT
				bfsVisit(v);
				printReachable(v);
			}
		}
		
		
	}
	
	private static void bfsVisit(Vertex s){
		
		LinkedList<Vertex> q = new LinkedList<Vertex>();
		q.add(s); //Adding source
		
		while (!q.isEmpty()) {
			Vertex u = q.removeFirst(); //Constant time operation
			
			for (Edge edge : u.adjEdges) {
				if (!edge.to.status.equals("DOWN") && !edge.status.equals("DOWN") && edge.to.color.equals("WHITE")) {
					
					edge.to.color = "GREY";
					u.reachMap.put(edge.to.name,edge.to);
					//TODO:Try PUT ALL map
					q.add(edge.to); //Enqueue
				}
			}
		}

	}
	public static void printReachable(Vertex s){
		
		System.out.println(s.name);
		for (Vertex v : s.reachMap.values()) {
			System.out.println("  "+v.name+"  "+v.color);
		}
		
	}
	
}
