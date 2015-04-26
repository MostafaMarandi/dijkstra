import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class name has nothing to do with the code. This file just contains the
 * main method to trigger the program Name is given as Graph, because the
 * instructions are given to do so.
 */
public class Graph {

	private static Boolean quit = false;

	public static void main(String[] args) {

		WeightedGraph g = new WeightedGraph();
		try {
			 FileReader fin = new FileReader(args[0]);
//			FileReader fin = new FileReader("network.txt");
			Scanner graphFile = new Scanner(fin);

			// Read the edges and insert
			String line;
			while (graphFile.hasNextLine()) {
				line = graphFile.nextLine();
				StringTokenizer st = new StringTokenizer(line);

				try {
					if (st.countTokens() != 3) {
						System.err.println("Skipping ill-formatted line "
								+ line);
						continue;
					}
					String source = st.nextToken();
					String dest = st.nextToken();
					double cost = Double.parseDouble(st.nextToken());
					g.addBiEdge(source, dest, cost);
				} catch (NumberFormatException e) {
					System.err.println("Skipping ill-formatted line " + line);
				}
			}

			Scanner input = new Scanner(System.in);
			String query;
			while (!quit) {
				query = input.nextLine();
				queryMatcher(query, g);

			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	private static void queryMatcher(String queryString, WeightedGraph g) {

		String query = queryString;
		StringTokenizer st = new StringTokenizer(query);
		int queryLength = st.countTokens();
		if (queryLength > 4) {
			System.out
					.println("Check your query! Queries must be in lowercase!");
		} else if (queryLength == 1) {
			String tempQuery = st.nextToken();

			switch (tempQuery) {
			case "print":
				g.printGraph();
				break;
			case "reachable":
				// TODO: Add reachable function
//				GraphReachable.reachable(g);
				ReachablewBFS.reachableBfs(g);
				break;
			case "quit":
				quit = true;
				break;
			default:
				System.out.println("Check your query!");
				break;
			}

		} else if (queryLength == 2) {
			String tempQuery0 = st.nextToken();
			String tempQuery1 = st.nextToken();
			switch (tempQuery0) {
			case "vertexdown":
				g.vertexDown(tempQuery1);
				break;
			case "vertexup":
				g.vertexUp(tempQuery1);
				break;
			default:
				System.out.println("vertexup or vertexdown!");
				break;
			}
		} else if (queryLength == 3) {
			String tempQuery0 = st.nextToken();
			String tempQuery1 = st.nextToken();
			String tempQuery2 = st.nextToken();

			switch (tempQuery0) {

			case "path":
				g.dijPath(tempQuery1);
				g.printPath(tempQuery2);
				break;
			case "deleteedge":
				g.deleteEdge(tempQuery1, tempQuery2);
				break;
			case "edgedown":
				g.edgeDown(tempQuery1, tempQuery2);
				break;
			case "edgeup":
				g.edgeUp(tempQuery1, tempQuery2);
				break;
			default:
				System.out.println("3 parameters wrong!");
			}
		} else {

			String tempQuery0 = st.nextToken();
			String tempQuery1 = st.nextToken();
			String tempQuery2 = st.nextToken();
			Double tempQuery3 = Double.parseDouble(st.nextToken());
			switch (tempQuery0) {

			case "addedge":
				g.addEdge(tempQuery1, tempQuery2, tempQuery3);
				break;
			default:
				System.out.println("4 parameters wrong");
				break;
			}

		}
	}

}
