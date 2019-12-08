package com.advent.three;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 08.12.19.
 */
public class Coordinate implements Comparable{

    public static final Coordinate ZERO = new Coordinate(0,0,0);
    private final int x;
    private final int y;
    private final int step;

    public Coordinate(int x, int y, int step) {
        this.x = x;
        this.y = y;
        this.step = step;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinate that = (Coordinate) o;

        if (x != that.x) {
            return false;
        }
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    public int distance(){
        return Math.abs(x) + Math.abs(y);
    }

    public int getStep() {
        return step;
    }

    public Coordinate apply(String ins) {
        char c = ins.charAt(0);
        int digit = Integer.parseInt(ins.substring(1));
        int nextStep = step + digit;
        return switch (c) {
            case 'R': yield new Coordinate(x + digit, y, nextStep);
            case 'L': yield new Coordinate(x - digit, y, nextStep);
            case 'U': yield new Coordinate(x, y + digit, nextStep);
            case 'D': yield new Coordinate(x , y - digit, nextStep);

            default: throw new InvalidParameterException("unknown direction" + c);
        };
    }

    public List<Coordinate> trace(String ins) {
        char c = ins.charAt(0);
        int digit = Integer.parseInt(ins.substring(1));
        var range =  switch (c) {
            case 'R': yield IntStream.rangeClosed(x, x + digit).mapToObj(it -> new Coordinate(it, y, step + Math.abs(x-it)));
            case 'L': yield IntStream.rangeClosed(x - digit, x).mapToObj(it -> new Coordinate(it, y, step + Math.abs(x-it)));
            case 'U': yield IntStream.rangeClosed(y, y + digit).mapToObj(it -> new Coordinate(x, it, step + Math.abs(y-it)));
            case 'D': yield IntStream.rangeClosed(y - digit, y).mapToObj(it -> new Coordinate(x, it, step + Math.abs(y-it)));
            default: throw new InvalidParameterException("unknown direction" + c);
        };
        return range.collect(Collectors.toList());
    }

    @Override
    public int compareTo(Object o) {
        var that = (Coordinate)o;
        return Integer.compare(this.distance(),(that.distance()));
    }
}
