import java.util.ArrayList;

public class Heap {

	private ArrayList sortedQ;

	public Heap(ArrayList ar) {
		this.sortedQ = ar;
		build(this.sortedQ, sortedQ.size());
	}

	// Build Min Heap
	private void build(ArrayList unsortedQ, int n) {

		for (int i = (n / 2) - 1; i <= 0; i--) {
			minHeapify(unsortedQ, i, n);
		}
	}

	// Min Heapify
	private void minHeapify(ArrayList unsortedQ, int i, int n) {
		int l, r;
		l = left(i);
		r = right(i);
		int smallest;
		if (l <= n && getDist(unsortedQ.get(l)) < getDist(unsortedQ.get(i))) {
			smallest = l;
		} else {
			smallest = i;
		}
		if (r <= n
				&& getDist(unsortedQ.get(r)) < getDist(unsortedQ.get(smallest))) {
			smallest = r;
		}
		if (smallest != i) {
			Object temp = unsortedQ.get(i);
			unsortedQ.set(i, unsortedQ.get(smallest));
			unsortedQ.set(smallest, temp);
			minHeapify(unsortedQ, smallest, n);
		}
	}

	//ExtractMin
	// Removing last element costs O(1) in ArrayList
	// Ref:http://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist
	public Object extractMin(ArrayList sortedQ, int n) {
		
		if (n < 1) {
			System.out.println("Min-Heap undeflow!");
			return null;
		}
		Object min = sortedQ.get(0);
		sortedQ.set(0, sortedQ.get(n - 1));
		sortedQ.remove(n - 1);
		n = n - 1;
		minHeapify(sortedQ, 0, n);
		
		return min;
	}

	// Get the left child of the node
	private int left(int i) {
		return 2 * i + 1;
	}

	// Get the right child of the node
	private int right(int i) {
		return 2 * i + 2;
	}

	// Change this method to change the value based on which the queue should be
	// sorted
	private double getDist(Object a) {
		Vertex v = (Vertex) a;
		return v.dist;
	}
}
