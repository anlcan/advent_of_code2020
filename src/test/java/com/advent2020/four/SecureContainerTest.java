package com.advent2020.four;

import com.advent.four.PasswordCracker;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created on 08.12.19.
 */
public class SecureContainerTest {

    @Test
    public void predicateTest() {

        assertFalse(PasswordCracker.getDoubleDigit().test(1234));
        assertTrue(PasswordCracker.getDoubleDigit().test(1224));
        assertTrue(PasswordCracker.getDoubleDigit().test(1433));



        assertTrue(PasswordCracker.getIncreasing().test(1234));
        assertTrue(PasswordCracker.getIncreasing().test(1224));
        assertFalse(PasswordCracker.getIncreasing().test(1433));
        assertFalse(PasswordCracker.getIncreasing().test(1423));
    }

    @Test
    public void trickyTest(){
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(1224));

        //no longer meets the criteria (the repeated 44 is part of a
        //larger group of 444)
        assertFalse(PasswordCracker.getDoubleDigitTricky().test(123444));
        assertFalse(PasswordCracker.getDoubleDigitTricky().test(12344441));
        // meets the criteria (even though 1 is repeated more than twice,
        //it still contains a double 22)
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(111122));
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(1111122));
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(11111122));
        assertFalse(PasswordCracker.getDoubleDigitTricky().test(377777));
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(1553561222));
        assertTrue(PasswordCracker.getDoubleDigitTricky().test(377899));

        assertTrue(PasswordCracker.getDoubleDigitTricky().test(22101222));



    }

    @Test
    public void testPartOne() {
        var pc = new PasswordCracker(372304, 847060);
        System.out.println(pc.possibilities()); //475
    }

    @Test
    public void testPartTwo() {

        //trickyTest();
        var pc = new PasswordCracker(372304, 847060);
        //System.out.println(pc.possibilitiesRefined()); //297
        assertEquals(297, pc.possibilitiesRefined()); //297
    }
}
