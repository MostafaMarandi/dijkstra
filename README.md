----------------------------------------

Author		: Prem kumar Murugesan

StudentID	: 800888499

Email		: pmuruges@uncc.edu

Late Days	: 3 remaining

----------------------------------------

Contents
==========

1. Project Objective

2. Program Design

3. How to run the program?

4. Time Complexity for reachable


Project Objective
=================

(1) Construction of a weighted graph from the given 'network.txt' file.

(2) Update the constructed graph.

(3) Find the shortest path between any two vertices in the graph using Dijkstra's Algorithm.

(4) Print the graph.

(5) Find the reachable sets of vertices for all the nodes in the graph.



Program Design
===============

The java project attached contains the following files.

	1. Graph.java
	2. WeightedGraph.java
	3. Vertex.java
	4. Edge.java
	5. Heap.java
	6. ReachablewBFS.java ~> Implemented with BFS
	7. GraphReachable.java ~> Tried with dynamin programming, Still not found the solution


1. Graph.java

	It contains the main method, where the input query is processed to call the correct method.


2. WeightedGraph.java

	It contains the implementation of the Dijkstra's algorithm to find the shortest path, other methods to edit and print the graph.


3. Vertex.java

	Gives the structure for Vertex object.

4. Edge.java

	Gives the structure for Edge object.

5. Heap.java
	
	Implemented the Binary Min Heap, which is used as the priority queue in dijkstra's algorithm.

6. ReachablewBFS.java

	Implemented an algorithm using BFS graph traversal to find all the reachable nodes from all nodes in the graph with the exponential running time of O(V(V+E))
	Note: Running time computation are at the bottom of this file.

7. GraphReachable.java(Using Dynamic Programmin - INCOMPLETE)
	
	Tried the same reachable function with dynamic programming to find more efficient method.
	But this is incomplete as of now.
	Note: I may update in the next submission.


How to run the program?
=======================

1. Navigate to the source path that contains all the 7 above mentioned files.

2. Copy the input file <filename.txt> to this directory.

3. Execute the following command without quotes in cmd or terminal

	
   "javac *.java && java Graph filename.txt"


4. Replace the filename.txt with the appropriate input file's name.

5. Dont forget the .txt extension!

6. Once executed, the console will ask for input query.

7. Give 'quit' to terminate the program.


Time Complexity for reachable
=============================

Initialization - O(V) 
Running BFS - O(V+E)

For one vertex -> O(2V+E) ~> O(V+E)
Hence to find reachability for all V vertices -> O(V(V+E))
