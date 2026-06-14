package graph;

import java.util.HashMap;
import java.util.List;

enum Direction{
    BIDIRECTION,DIRECTED;
}

public class Graph {


    public class Node{
       private String value;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private HashMap<String,Node> nodes=new HashMap<>();
    private HashMap<Node, List<Node>> adjList=new HashMap<>();

    public Node addNode(String value){
        Node node=new Node(value);
        nodes.put(value, node);
        return node;
    }

    public void addEdge(String from, String to,Direction direction){
        Node fromNode=nodes.get(from);
        Node toNode=nodes.get(to);
        if(fromNode==null || toNode==null){
            throw new IllegalArgumentException("Both nodes must exist in the graph");
        }
        adjList.computeIfAbsent(fromNode, k -> new java.util.ArrayList<>()).add(toNode);

        if(Direction.BIDIRECTION.equals(direction)){
            adjList.computeIfAbsent(toNode, k -> new java.util.ArrayList<>()).add(fromNode);
        }
    }

    public List<Node> getBFS(Node start) {
        List<Node> result = new java.util.ArrayList<>();
        java.util.Queue<Node> queue = new java.util.LinkedList<>();
        java.util.Set<Node> visited = new java.util.HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.add(current);

            List<Node> neighbors = adjList.get(current);
            if (neighbors != null) {
                for (Node neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        for(Node node:nodes.values()){
            sb.append(node.value).append("->");
            List<Node> neighbors=adjList.get(node);
            if(neighbors!=null){
                for(Node neighbor:neighbors){
                    sb.append(neighbor.value);
                }
            }
            sb.append(",");
        }
        return sb.toString();
    }
}
