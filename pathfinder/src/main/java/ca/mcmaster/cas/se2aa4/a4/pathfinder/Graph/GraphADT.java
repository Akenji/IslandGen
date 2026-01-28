package ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GraphADT <T> {
    protected List<Node<T>> nodes;
    protected List<Edge<T>> edges;
    protected Map<Node<T>, ArrayList<Edge<T>>> adjacencyList;

    public GraphADT(){
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.adjacencyList = new HashMap<>();
    }
    public abstract void addNode(Node<T> node);
    public abstract void addEdge(Node<T> n1, Node<T> n2, double weight, Map<String, Object> properties);
    public abstract double getEdgeWeight(Node<T> n1, Node<T> n2);
    public Node<T> getNode(T id){
        for(Node<T> node: nodes){
            if(node.getId().equals(id)){
                return node;
            }
        }
        return null;
    }
    public List<Node<T>> getNodes(){
        return nodes;
    }

    public List<Edge<T>> getEdges(){
        return edges;
    }
    public List<Edge<T>> getConnectedEdges(Node<T> source){
        return adjacencyList.get(source);
    }

}
