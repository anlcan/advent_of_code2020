package com.advent.six;

/**
 * Created on 15.12.19.
 */
public class Edge  {

    private final Vertex source;
    private final Vertex target;

    Edge(Vertex origin, Vertex target) {
        this.source = origin;
        this.target = target;

        origin.edges.add(this);
        target.edges.add(this);
    }

    public Vertex getSource() {
        return source;
    }

    public Vertex getTarget() {
        return target;
    }
}
