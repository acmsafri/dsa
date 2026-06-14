package graph;

import heap.MaxHeap;
import org.w3c.dom.Node;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Graph graph=new Graph();
        Graph.Node nodeA=graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A","B",Direction.DIRECTED);
        graph.addEdge("B","C",Direction.DIRECTED);
        graph.addEdge("C","A",Direction.DIRECTED);
        graph.addEdge("A","D",Direction.DIRECTED);

        System.out.println(graph);

        System.out.println(graph.getBFS(nodeA));

     }
}
