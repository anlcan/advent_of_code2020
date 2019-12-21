package com.advent2020.seven;

import com.advent.seven.AmplifierController;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 21.12.19.
 */
public class AmplifierControllerTest {



    @Test
    public void amplifierControllerTest() {
        String input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        assertEquals(43210,AmplifierController.run(input, "4,3,2,1,0"));

        String computerCode = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        assertEquals(54321, AmplifierController.run(computerCode, "0,1,2,3,4"));
    }

    @Test
    public void amplifierHighTest() {
        String input = "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0";
        assertEquals(43210, new AmplifierController(input).highestSignal());

        String computerCode = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        assertEquals(54321, new AmplifierController(computerCode).highestSignal());
    }

    @Test
    public void inputOneTest() {
        List<String> input1 = Util.readStrings("/seven/input1.txt");
        var amp = new AmplifierController(input1.get(0));
        System.out.println(amp.highestSignal());
    }
}
