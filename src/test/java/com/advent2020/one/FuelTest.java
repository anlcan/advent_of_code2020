package com.advent2020.one;

import com.advent.one.Module;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 06.12.19.
 */
public class FuelTest {

    /*
    For a mass of 12, divide by 3 and round down to get 4, then subtract 2 to get 2.
    For a mass of 14, dividing by 3 and rounding down still yields 4, so the fuel required is also 2.
    For a mass of 1969, the fuel required is 654.
    For a mass of 100756, the fuel required is 33583.
     */
    @Test
    public void moduleTest(){
        assertEquals(new Module(12).requiredFuel(),2);
        assertEquals(new Module(12).requiredFuel(),2);
        assertEquals(new Module(1969).requiredFuel(),654);
        assertEquals(new Module(100756).requiredFuel(),33583);
    }

    @Test
    public void testOne(){
        List<String> input1 = Util.readStrings("/one/input1.txt");
        int sum = input1.stream()
                .map(it -> new Module(Integer.parseInt(it)))
                .mapToInt(Module::requiredFuel)
                .sum();

        System.out.println(sum);// 3315133
        assertEquals(3315133, sum);
    }


    /**
     * A module of mass 14 requires 2 fuel. This fuel requires no further fuel (2 divided by 3 and rounded down is 0, which would call for a negative fuel), so the total fuel required is still just 2.
     * At first, a module of mass 1969 requires 654 fuel. Then, this fuel requires 216 more fuel (654 / 3 - 2). 216 then requires 70 more fuel, which requires 21 fuel, which requires 5 fuel, which requires no further fuel. So, the total fuel required for a module of mass 1969 is 654 + 216 + 70 + 21 + 5 = 966.
     * The fuel required by a module of mass 100756 and its fuel is: 33583 + 11192 + 3728 + 1240 + 411 + 135 + 43 + 12 + 2 = 50346.
     */
    @Test
    public void modulefuelTest() {
        assertEquals(new Module(1969).requiredFullFuel(),966);
        assertEquals(new Module(100756).requiredFullFuel(),50346);
    }

    @Test
    public void testTwo(){
        List<String> input1 = Util.readStrings("/one/input2.txt");
        int sum = input1.stream()
                .map(it -> new Module(Integer.parseInt(it)))
                .mapToInt(Module::requiredFullFuel)
                .sum();

        System.out.println(sum);// 4969831
        assertEquals(4969831, sum);
    }



}
