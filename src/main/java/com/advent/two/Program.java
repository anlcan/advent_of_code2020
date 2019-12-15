package com.advent.two;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 08.12.19.
 */
public class Program {

    protected final List<Integer> intCode;
    protected int instructionPointer = 0;

    public Program(List<Integer> intCode) {
        this.intCode = intCode;
    }

    public void setNounAndVerb(int noun, int verb){
        intCode.set(1, noun);
        intCode.set(2, verb);
    }

    public static Program of(String input) {
        return new Program(Arrays.stream(input.split(",")).map(Integer::valueOf).collect(Collectors.toList()));
    }
    /*
    Opcode 1 adds together numbers read from two positions and stores the result in a third position.
    The three integers immediately after the opcode tell you these three positions - the first two indicate the positions
    from which you should read the input values, and the third indicates the position at which the output should be stored.
    */
    public int execute() {
        //System.out.println(String.format("executing code at %s: %s", instructionPointer, intCode.get(instructionPointer)));
        final Integer instruction = intCode.get(instructionPointer);
        return switch (instruction) {
            case 1: {
                opCodeOne();
                yield execute();
            }
            case 2: {
                opCodeTwo();
                yield execute();
            }
            case 99:
                yield intCode.get(0);
            default:
                //throw new RuntimeException("unknown opcode: " + intCode.get(instructionPointer));
                //System.out.println("unknown code: " + instruction);
                yield -1;
        };
    }

    protected void opCodeOne() {
        var par1 = intCode.get(intCode.get(instructionPointer +1));
        var par2 = intCode.get(intCode.get(instructionPointer +2));
        var target = intCode.get(instructionPointer +3);
        intCode.set(target, par1 + par2);
        instructionPointer += 4;
    }

    protected void opCodeTwo() {
        var one = intCode.get(intCode.get(instructionPointer +1));
        var two = intCode.get(intCode.get(instructionPointer +2));
        var target = intCode.get(instructionPointer +3);
        intCode.set(target, one * two);
        instructionPointer += 4;

    }
}
