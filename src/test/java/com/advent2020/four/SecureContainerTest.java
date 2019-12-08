package com.advent2020.four;

import com.advent.four.PasswordCracker;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    public void testPartOne() {
        var pc = new PasswordCracker(372304, 847060);
        System.out.println(pc.possibilities());
    }
}
