package com.advent.six;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created on 15.12.19.
 */
public class Graph {

    public final Map<String, Vertex> vertices = new HashMap<>();
    public final List<Edge> edges = new ArrayList<>();

    public Vertex root;
    public Vertex you;
    public Vertex santa;

    public static Graph of(final List<String> map) {
        var g = new Graph();
        map.forEach(g::readEdge);
        return g;
    }

    public void readEdge(String edge) {

        String[] split = edge.split("\\)");

        Edge e = new Edge(vertex(split[0]), vertex(split[1]));
        addEdge(e);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Vertex vertex(String id) {
        Vertex vertex = new Vertex(id);
        vertices.putIfAbsent(id, vertex);
        if (id.equals("COM")) {
            root = vertex;
        } else if (id.equals("YOU")) {
            you = vertex;
        } else if (id.equals("SAN")) {
            santa = vertex;
        }
        return vertices.getOrDefault(id, vertex);
    }

    public Vertex ortbitOf(Vertex v) {
        return v.edges.stream().filter(it -> it.getTarget().id.endsWith(v.id)).findFirst().get().getSource();
    }


    public int checkSum() {
        return vertices.values().stream().filter(v -> v != root)
                .mapToInt(v -> orbitCount(v, root).size()).sum();
    }

    public List<Vertex> orbitCount(final Vertex from, final Vertex to) {
        List<Vertex> list = new ArrayList<>();
        Vertex orbit = ortbitOf(from);
        while (true) {
            list.add(orbit);
            if (orbit == to) {
                break;
            }
            orbit = ortbitOf(orbit);

        }
        return list;
    }

    public int you2santaEX() {
        List<Vertex> yourPath = orbitCount(you, root);
        List<Vertex> santasPath = orbitCount(santa, root);

        List<Edge> yourEdges = yourPath.stream().flatMap(v -> v.edges.stream()).collect(Collectors.toList());
        List<Edge> santasEdges = santasPath.stream().flatMap(v -> v.edges.stream()).collect(Collectors.toList());

        Set<Edge> s1 = new HashSet<>(yourEdges);
        s1.removeAll(santasEdges);

        Set<Edge> s2 = new HashSet<>(santasEdges);
        s2.removeAll(yourEdges);

        s1.addAll(s2);

        return s1.size() - 1;
    }

    public int you2santa(){
//        return simple();
        return you2santaEX();
    }

    public int simple() {
        Multigraph<String, DefaultEdge> g = new Multigraph<>(DefaultEdge.class);
        vertices.values().forEach(v -> g.addVertex(v.id));
        edges.forEach(e -> g.addEdge(e.getSource().id, e.getTarget().id));

        ShortestPathAlgorithm<String, DefaultEdge> spa = new DijkstraShortestPath<>(g);
        GraphPath<String, DefaultEdge> path = spa.getPath(santa.id, you.id);

        return path.getEdgeList().size()-2;
    }

}
