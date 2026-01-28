package ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;

import java.util.HashMap;
import java.util.Map;

public class Node<T> implements Comparable<Node<T>>{
    private T id;
    private boolean isVisited; // to track whether node has been visited already in Dijkstra's algorithm
    private Map<String, Object> properties;
    private double cost;
    public Node(T id){
        this.id = id;
        this.properties = new HashMap<>();
        this.cost = Double.POSITIVE_INFINITY;
        this.isVisited = false;
    }

    public int compareTo(Node<T> otherNode){
        return Double.compare(cost, otherNode.getCost());
    }

    public T getId(){
        return id;
    }

    public void setCost(double cost){
        this.cost = cost;
    }
    public double getCost(){
        return cost;
    }

    public void setProperty(String property, Object value){
        properties.put(property, value);
    }

    public Object getPropertyValue(String property){
        return properties.get(property);
    }

    public boolean hasProperty(String property){
        return properties.containsKey(property);
    }

    public void setVisited(boolean visited){
        this.isVisited = visited;
    }
    public boolean isVisited(){
        return isVisited;
    }
}
