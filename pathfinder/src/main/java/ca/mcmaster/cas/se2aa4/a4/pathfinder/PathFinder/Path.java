package ca.mcmaster.cas.se2aa4.a4.pathfinder.PathFinder;

import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.GraphADT;
import ca.mcmaster.cas.se2aa4.a4.pathfinder.Graph.Node;

import java.util.List;

public interface Path<T> {
    public List<Node<T>> findPath(GraphADT<T> graph, Node<T> source, Node<T> target);
}
