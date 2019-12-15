package com.advent.six;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 15.12.19.
 */
public class Graph {

    public final Map<String, Vertex> vertices = new HashMap<>();
    public final List<Edge> edges = new ArrayList<>();

    public Vertex root;

    public static Graph of(final List<String> map){
        var g = new Graph();
        map.forEach(g::readEdge);
        return g;
    }

    public void readEdge(String edge) {

        String[] split = edge.split("\\)");

        Edge e  = new Edge(vertex(split[0]), vertex(split[1]));
        addEdge(e);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Vertex vertex(String id){
        Vertex vertex = new Vertex(id);
        vertices.putIfAbsent(id, vertex);
        if (id.equals("COM")){
            root = vertex;
        }
        return vertices.getOrDefault(id, vertex);
    }
    public Vertex ortbitOf(Vertex v){
        return v.edges.stream().filter(it -> it.getTarget().id.endsWith(v.id)).findFirst().get().getSource();
    }


    public int checkSum(){
        return vertices.values().stream().filter(v -> v != root)
                .mapToInt(v -> {
                    int count = 0;
                    Vertex orbit = ortbitOf(v);
                    while (true){
                        count++;
                        if (orbit == root) break;
                        orbit = ortbitOf(orbit);

                    }
                    return count;
                }).sum();
    }
}
