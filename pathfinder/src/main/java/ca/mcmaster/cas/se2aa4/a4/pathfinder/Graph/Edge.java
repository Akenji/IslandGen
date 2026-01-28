package ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;

import java.util.HashMap;
import java.util.Map;

public class Edge<T> {
    private Node<T> node;
    private double weight;
    private Map<String, Object> properties;

    public Edge(Node<T> node, double weight){
        this.node = node;
        this.weight = weight;
        this.properties = new HashMap<>();
    }

    public Node<T> getNode(){
        return node;
    }

    public double getWeight(){
        return weight;
    }

    public void setProperty(String property, Object value){
        properties.put(property, value);
    }

    public Object getPropertyValue(String property){
        return properties.get(property);
    }
}
