
public class Edge {

	public Vertex from;
	public Vertex to;
	public double cost;
	
	public Edge(Vertex from, Vertex to, double cost) {
		super();
		this.from = from;
		this.to = to;
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from.name + ", to=" + to.name + ", cost=" + cost + "]";
	}
	
	
}
