package com.advent2020.five;

import com.advent.five.Computer;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 15.12.19.
 */
public class ComputerFiveTest {

    @Test
    public void simpleCaseOne() {
        var input = "1,0,0,5,99,0";
        var p1 = Computer.of(input);
        var input1 = p1.execute();
        assertEquals(2, input1);
    }

    @Test
    public void simpleCaseTwo() {

        var p2 = Computer.of("2,3,0,3,99");
        var input2 = p2.execute();
        assertEquals(2, input2);
    }

    @Test
    public void simpleCaseThree() {

        var p = Computer.of("1,1,1,4,99,5,6,0,99");
        var input = p.execute();
        assertEquals(30, input);
    }

    @Test
    public void simpleCaseFour() {

        var p2 = Computer.of("3,0,4,0,99");
        var input2 = p2.execute(100);
        assertEquals(100, input2);
    }

    @Test
    public void modeTestOne() {
        var p2 = Computer.of("1002,4,3,4,33");
        var input2 = p2.execute();
        assertEquals(99, input2);
    }

    @Test
    public void modeTestTwo() {
        var p2 = Computer.of("1101,100,-1,4,0");
        var input2 = p2.execute();
        assertEquals(1101, input2);
    }

    @Test
    public void derefTest() {
        var p2 = Computer.of("1101,100,-1,4,0,99");

        assertEquals(100, p2.dereference(1, 1101));
        assertEquals(-1, p2.dereference(2, 1101));

        var p3 = Computer.of("101,5,6,4,0,66,33");
        assertEquals(66, p3.dereference(1, 1));
    }

    @Test
    public void inputOne() {
        List<String> input1 = Util.readStrings("/five/input1.txt");
        var p = Computer.of(input1.get(0));

        p.execute(1); //14522484
    }

    @Test
    public void inputTwo() {
        List<String> input1 = Util.readStrings("/five/input1.txt");
        var p = Computer.of(input1.get(0));

        p.execute(5); //4655956
    }

    @Test
    public void inputHalf() {
        String input = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31," +
                "1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104," +
                "999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99";
        Computer c1 = Computer.of(input );
        assertEquals(999,c1.execute(5));

        Computer c2 = Computer.of(input );
        assertEquals(1000,c2.execute(8));

        Computer c3 = Computer.of(input );
        assertEquals(1001,c3.execute(10));


    }
}

