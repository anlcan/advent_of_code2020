package com.advent.seven;

import com.advent.five.Computer;

/**
 * Created on 21.12.19.
 */
public class Amplifier {

    private final Computer computer;

    public static Amplifier of(final String input){
        return new Amplifier(Computer.of(input));
    }

    private Amplifier(Computer computer) {
        this.computer = computer;
    }

    public int execute(int inputOne, int inputTwo) {
        return computer.execute(inputOne, inputTwo);
    }
}
