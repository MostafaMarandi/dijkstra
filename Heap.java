/**
 * 
 * Author   : Prem Kumar Murugesan
 * StudentID: 800888499
 * 
 * Implemented the Binary Min Heap, which is used as the priority queue in dijkstra's algorithm.
 */

import java.util.ArrayList;

public class Heap {

	private ArrayList<Vertex> sortedQ;

	// Constructor
	public Heap(ArrayList<Vertex> ar) {
		this.sortedQ = ar;
		build(this.sortedQ, sortedQ.size());
	}

	// Build Min Heap
	private void build(ArrayList<Vertex> unsortedQ, int n) {

		for (int i = (n / 2) - 1; i >= 0; i--) {
			minHeapify(unsortedQ, i, n);
		}
	}

	// Min Heapify
	// Additional conditions to keep track of heap_index
	private void minHeapify(ArrayList<Vertex> unsortedQ, int i, int n) {
		int l, r;
		l = left(i);
		r = right(i);
		int smallest;
		if (l <= n - 1) {
			if (getDist(unsortedQ.get(l)) < getDist(unsortedQ.get(i))) {
				smallest = l;
				unsortedQ.get(l).heap_index = i;
				unsortedQ.get(i).heap_index = l;
			} else {
				unsortedQ.get(l).heap_index = l;
				unsortedQ.get(i).heap_index = i;
				smallest = i;
			}

		} else {
			unsortedQ.get(i).heap_index = i;
			smallest = i;
		}
		if (r <= n - 1) {
			if (getDist(unsortedQ.get(r)) < getDist(unsortedQ.get(smallest))) {
				unsortedQ.get(r).heap_index = i;
				unsortedQ.get(i).heap_index = r;
				smallest = r;
			} else {
				unsortedQ.get(r).heap_index = r;
				unsortedQ.get(i).heap_index = i;
			}
		}

		if (smallest != i) {
			Vertex temp = unsortedQ.get(i);
			unsortedQ.set(i, unsortedQ.get(smallest));
			unsortedQ.set(smallest, temp);
			minHeapify(unsortedQ, smallest, n);
		}
	}

	// ExtractMin
	// Removing last element costs O(1) in ArrayList
	// Ref:http://stackoverflow.com/questions/322715/when-to-use-linkedlist-over-arraylist
	public Vertex extractMin(ArrayList<Vertex> sortedQ, int n) {

		if (n < 1) {
			System.out.println("Min-Heap undeflow!");
			return null;
		}
		Vertex min = sortedQ.get(0);
		sortedQ.set(0, sortedQ.get(n - 1));
		sortedQ.remove(n - 1);
		n = n - 1;
		if (n > 0) {
			minHeapify(sortedQ, 0, n);
		}

		return min;
	}

	// Decrease Priority/Key
	public void decreaseKey(int i, double key) {
		if (sortedQ.get(i).dist < key) {
			return;
		}
		sortedQ.get(i).dist = key;
		while (i > 0 && sortedQ.get(parent(i)).dist > sortedQ.get(i).dist) {
			// Change heap_index
			sortedQ.get(parent(i)).heap_index = i;
			sortedQ.get(i).heap_index = parent(i);

			Vertex temp = sortedQ.get(i);
			sortedQ.set(i, sortedQ.get(parent(i)));
			sortedQ.set(parent(i), temp);
			i = parent(i);
		}
	}

	// Get the Parent of a node
	private int parent(int i) {
		return (i - 1) / 2;
	}

	// Get the left child of the node
	private int left(int i) {
		return 2 * i + 1;
	}

	// Get the right child of the node
	private int right(int i) {
		return 2 * i + 2;
	}

	// Check the heap is empty
	public boolean isEmpty() {
		return sortedQ.isEmpty();
	}

	// Dummy extraxtMin for DataAbstraction
	public Vertex extractMin() {
		return extractMin(sortedQ, sortedQ.size());
	}

	// Change this method to change the value based on which the queue should be
	// sorted
	private double getDist(Vertex v) {
		return v.dist;
	}
}
