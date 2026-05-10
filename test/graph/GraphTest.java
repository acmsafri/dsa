package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new Graph();
    }

    @Test
    void testAddNode() {
        graph.addNode("A");
        // Since nodes are private, we can test by trying to add edges or other operations
        // For now, assume addNode works if no exception is thrown
        assertDoesNotThrow(() -> graph.addNode("B"));
    }

    @Test
    void testAddEdgeDirected() {
        graph.addNode("A");
        graph.addNode("B");
        assertDoesNotThrow(() -> graph.addEdge("A", "B", Direction.DIRECTED));
        assertTrue(graph.toString().contains("A->B,B->,"));
    }

    @Test
    void testAddEdgeBidirectional() {
        graph.addNode("A");
        graph.addNode("B");
        assertDoesNotThrow(() -> graph.addEdge("A", "B", Direction.BIDIRECTION));
        assertTrue(graph.toString().contains("A->B,B->A,"));
    }

    @Test
    void testAddEdgeWithNonExistentNode() {
        graph.addNode("A");
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", Direction.DIRECTED));
        assertTrue(graph.toString().contains("A->,"));

    }

    @Test
    void testAddEdgeBothNodesNonExistent() {
        assertThrows(IllegalArgumentException.class, () -> graph.addEdge("A", "B", Direction.DIRECTED));
        assertTrue(graph.toString().isEmpty());
    }

    // Note: Since adjList is private, we can't directly test the adjacency list.
    // In a real scenario, you might add getter methods or test through traversals if implemented.

}