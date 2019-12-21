package com.advent2020.six;

import com.advent.six.Graph;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created on 15.12.19.
 */
public class Orbital {

    @Test
    public void basic (){
        var input = """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L""";
        var g = Graph.of(input.lines().map(String::trim).collect(Collectors.toList()));

        assertEquals(12, g.vertices.size());
        assertEquals(11, g.edges.size());
        assertEquals(42, g.checkSum());
    }

    @Test
    public void inputOne() {
        List<String> input = Util.readStrings("/six/input1.txt");
        Graph g = Graph.of(input);
        assertEquals(154386, g.checkSum());
    }

    @Test
    public void inputSanta(){
        var input = """
                COM)B
                B)C
                C)D
                D)E
                E)F
                B)G
                G)H
                D)I
                E)J
                J)K
                K)L
                K)YOU
                I)SAN
                """;
        var g = Graph.of(input.lines().map(String::trim).collect(Collectors.toList()));
        assertEquals(4, g.you2santa());

    }



    @Test
    public void inputTwo() {
        List<String> input = Util.readStrings("/six/input2.txt");
        Graph g = Graph.of(input);
//        assertEquals(154386, g.checkSum());
        System.out.println(g.you2santa()); //not 346
    }
}
