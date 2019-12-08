package com.advent.three;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 08.12.19.
 */
public class Wire {

    private Coordinate current = new Coordinate(0,0, 0);
    private final List<String> instructions;
    private final List<Coordinate> path = new ArrayList<>();

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

    public List<Coordinate> getPath() {
        return new ArrayList<>(path);
    }

    public List<Coordinate> intersection(final Wire otherWire) {
        List<Coordinate> intersection = new ArrayList<>(path);
        intersection.retainAll(otherWire.getPath());
        intersection.sort(Coordinate::compareTo);
        intersection.remove(Coordinate.ZERO);

        return intersection;
    }

    /**
     * calculate the number of steps each wire takes to reach each intersection;
     * choose the intersection where the sum of both wires' steps is lowest.
     * If a wire visits a position on the grid multiple times, use the steps
     * value from the first time it visits that position when calculating the total value of a specific
     * intersection.
     */
    public List<Coordinate> fastestIntersection(final Wire that) {
        var intersection = this.intersection(that);
        var p1 = this.getPath();
        var p2 = that.getPath();

        return intersection.stream()
                .map(c -> {
                    var c1 = p1.get(p1.indexOf(c));
                    var c2 = p2.get(p2.indexOf(c));
                    return new Coordinate(c.getX(), c.getY(), c1.getStep() + c2.getStep());
                })
                .sorted(Comparator.comparingInt(Coordinate::getStep))
                .collect(Collectors.toList());
    }
}
