package com.advent.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 08.12.19.
 */
public class Wire {

    private Coordinate current = new Coordinate(0,0);
    private final List<String> instructions;
    public final List<Coordinate> path = new ArrayList<>();

    public Wire(List<String> instructions) {
        this.instructions = instructions;
    }

    public static Wire of(final String input) {
        return new Wire(Arrays.asList(input.split(",")));
    }

    public void tracePath() {
        for(String ins : instructions){
            path.addAll(current.trace(ins));
            current = current.apply(ins);
        }
    }

    public List<Coordinate> intersection(final Wire otherWire) {
        List<Coordinate> intersection = new ArrayList<>(path);
        intersection.retainAll(otherWire.path);
        intersection.sort(Coordinate::compareTo);
        return intersection;
    }



}
