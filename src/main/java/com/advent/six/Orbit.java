package com.advent.six;

import org.jgrapht.Graph;
import org.jgrapht.graph.Multigraph;

import java.util.List;

/**
 * Created on 15.12.19.
 */
public class Orbit {

    private Graph<String, Edge>  orbits = new Multigraph<>(Edge.class);

    public Orbit(List<String> orbitMap) {

        orbitMap
                .forEach(it -> {

//                    orbits.addEdge(split[0], split[1]);
                });
    }





}
