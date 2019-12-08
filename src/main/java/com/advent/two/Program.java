package com.advent.two;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 08.12.19.
 */
public class Program {

    private final List<Integer> intCode;
    private int head = 0;

    public Program(List<Integer> intCode) {
        this.intCode = intCode;
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
        System.out.println(String.format("executing code at %s: %s", head, intCode.get(head)));
        return switch (intCode.get(head)) {
            case 1: {
                opCodeOne();
                head += 4;
                yield execute();
            }
            case 2: {
                opCodeTwo();
                head += 4;
                yield execute();
            }
            case 99:
                yield intCode.get(0);
            default:
                throw new RuntimeException("unknown opcode: " + intCode.get(head));
        };
    }

    private void opCodeOne() {
        var one = intCode.get(intCode.get(head+1));
        var two = intCode.get(intCode.get(head+2));
        var target = intCode.get(head+3);
        intCode.set(target, one + two);
    }

    private void opCodeTwo() {
        var one = intCode.get(intCode.get(head+1));
        var two = intCode.get(intCode.get(head+2));
        var target = intCode.get(head+3);
        intCode.set(target, one * two);
    }
}
