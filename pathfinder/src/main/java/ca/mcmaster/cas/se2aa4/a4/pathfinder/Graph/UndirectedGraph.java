package ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph;

import java.util.ArrayList;
import java.util.Map;

public class UndirectedGraph<T> extends GraphADT<T> {

    public void addNode(Node<T> node){
        adjacencyList.put(node, new ArrayList<>());
        nodes.add(node);
    }

    public void addEdge(Node<T> n1, Node<T> n2, double weight){ // edge without attributes
        for(Edge<T> edge: adjacencyList.get(n1)){ // if edge already exists, do not add anything
            if(edge.getNode().equals(n2)){
                return;
            }
        }

        Edge<T> edge = new Edge<>(n2, weight);
        adjacencyList.get(n1).add(edge);
        edges.add(edge);

        // add edge in other direction because graph is undirected
        edge = new Edge<>(n1, weight);
        adjacencyList.get(n2).add(edge);
        edges.add(edge);
    }

    public void addEdge(Node<T> n1, Node<T> n2, double weight, Map<String, Object> properties){
        for(Edge<T> edge: adjacencyList.get(n1)){ // if edge already exists, do not add anything
            if(edge.getNode().equals(n2)){
                return;
            }
        }

        Edge<T> edge = new Edge<>(n2, weight);
        for(Map.Entry<String, Object> entry: properties.entrySet()){
            edge.setProperty(entry.getKey(), entry.getValue());
        }
        adjacencyList.get(n1).add(edge);
        edges.add(edge);

        // add edge in other direction because graph is undirected
        edge = new Edge<>(n1, weight);
        for(Map.Entry<String, Object> entry: properties.entrySet()){
            edge.setProperty(entry.getKey(), entry.getValue());
        }
        adjacencyList.get(n2).add(edge);
        edges.add(edge);
    }

    public double getEdgeWeight(Node<T> n1, Node<T> n2){
        for(Edge<T> edge: adjacencyList.get(n1)){
            if (edge.getNode().equals(n2)){
                return edge.getWeight();
            }
        }
        return Double.MAX_VALUE;
    }
}
