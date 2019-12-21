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
        assertEquals(43210, AmplifierController.highestSignal(input));

        String computerCode = "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0";
        assertEquals(54321, AmplifierController.highestSignal(input));
    }

    @Test
    public void inputOneTest() {
        List<String> input1 = Util.readStrings("/seven/input1.txt");
        assertEquals(47064, AmplifierController.highestSignal(input1.get(0)));
    }

    @Test
    public void amplifierLoopbackTestOne() {

        String computerCode1 = "3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26," +
                "27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5";
        assertEquals(139629729, AmplifierController.loopback(computerCode1, "9,8,7,6,5"));

    }

    @Test
    public void amplifierLoopbackTesTwot() {

        String computerCode2 = "3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54," +
                "-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4," +
                "53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10";
        assertEquals(18216, AmplifierController.loopback(computerCode2, "9,7,8,5,6"));

    }

    @Test
    public void inputTwoTest() {
        List<String> input1 = Util.readStrings("/seven/input1.txt");
        assertEquals(4248984, AmplifierController.highestLoopbackSignal(input1.get(0)));
    }
}
