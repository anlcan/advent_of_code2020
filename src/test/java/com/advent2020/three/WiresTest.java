package com.advent2020.three;

import com.advent.three.Wire;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 08.12.19.
 */
public class WiresTest {

    @Test
    public void intersectionOne(){
        var w1 = Wire.of("R75,D30,R83,U83,L12,D49,R71,U7,L72");
        var w2 = Wire.of("U62,R66,U55,R34,D71,R55,D58,R83");

        w1.tracePath();
        w2.tracePath();

        var intersection = w1.intersection(w2);
        assertEquals(159, intersection.get(1).distance());
    }

    @Test
    public void intersectionTwo(){
        var w1 = Wire.of("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51");
        var w2 = Wire.of("U98,R91,D20,R16,D67,R40,U7,R15,U6,R7");

        w1.tracePath();
        w2.tracePath();

        var intersection = w1.intersection(w2);
        assertEquals(135, intersection.get(1).distance());
    }

    @Test
    public void testInputOne(){
        List<String> inputs = Util.readStrings("/three1/input1.txt");

        var w1 = Wire.of(inputs.get(0));
        var w2 = Wire.of(inputs.get(1));

        w1.tracePath();
        w2.tracePath();

        var intersection = w1.intersection(w2);
        System.out.println(intersection.get(1).distance());

    }
}
