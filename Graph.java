import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * This class name has nothing to do with the code.
 * This file just contains the main method to trigger the program
 * Name is given as Graph, because the instructions are given to do so.
 */
public class Graph {

	public static void main(String[] args) {

		WeightedGraph g = new WeightedGraph();
		try {
			FileReader fin = new FileReader(args[0]);
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
			//Output: print
			g.printGraph();
			
			//Output: addedge
			g.addEdge("Prem", "Kumar", 9.9);
			g.printGraph();
			//Output: deleteedge
			g.deleteEdge("Kumar","Prem");
			g.printGraph();
			
		} 
		catch (IOException e) {
			System.err.println(e);
		}
	}

}
