package com.advent.six;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 15.12.19.
 */
public class Vertex {

    public final String id;
    public final List<Edge> edges = new ArrayList<>();

    Vertex(String id) {
        this.id = id;
    }

    public String ortbitOf(){
        return edges.stream().filter(it -> it.getTarget().id.endsWith(id)).findFirst().get().getSource().id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vertex vertex = (Vertex) o;

        return id != null ? id.equals(vertex.id) : vertex.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
