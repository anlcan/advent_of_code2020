package com.advent.five;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.IntFunction;
import java.util.stream.Collectors;

/**
 * Created on 15.12.19.
 */
public class Computer {

    protected final List<Integer> intCode;
    protected int instructionPointer = 0;
    protected Stack<Integer> stack = new Stack<>();
    ;

    public Computer(List<Integer> intCode) {
        this.intCode = intCode;
    }

    public static Computer of(String input) {
        return new Computer(Arrays.stream(input.split(",")).map(Integer::valueOf).collect(Collectors.toList()));
    }

    /**
     * the first parameter's mode is in the hundreds digit,
     * the second parameter's mode is in the thousands digit,
     * the third parameter's mode is in the ten-thousands digit,
     * and so on. Any missing modes are 0.
     */

    public static MODE dereferenceMode(int parameterCount, int instruction) {
        String s = new StringBuilder(String.valueOf(instruction)).reverse().toString();
        MODE mode = MODE.POSITION; // 0 position mode, 1 immediate mode
        if (s.length() > parameterCount + 1) {
            mode = MODE.of(s.charAt(parameterCount + 1));
        }
        return mode;
    }

    public int execute(int input) {
        stack.push(input);
        return execute();
    }

    public int execute() {
        //System.out.println(String.format("executing code at %s: %s", instructionPointer, intCode.get(instructionPointer)));
        final Integer instruction = intCode.get(instructionPointer);
        System.out.print(instruction + " ");
        var opCode = instruction % 100;
        return switch (opCode) {
            case 1: {
                new opOne().apply(instruction);
                yield execute();
            }
            case 2: {
                new opTwo().apply(instruction);
                yield execute();
            }
            case 3: {
                new opThree().apply(instruction);
                yield execute();
            }
            case 4: {
                new opFour().apply(instruction);
                yield execute();
            }
            case 99:
                //System.out.println(stack.empty()?-9999:stack.peek());
                System.out.println("halted");
                yield intCode.get(0);
            default:
                //throw new RuntimeException("unknown opcode: " + intCode.get(instructionPointer));
                System.out.println("unknown code: " + instruction);
                yield -1;
        };
    }

    private int dereferenceWithMode(int pointer, MODE mode) {
        if (mode == MODE.POSITION) { //position mode
            return intCode.get(intCode.get(pointer));
        } else { // 1 immediate mode
            return intCode.get(pointer);
        }
    }

    public int dereference(int parameterCount, int instruction) {
        MODE mode = dereferenceMode(parameterCount, instruction);
        return dereferenceWithMode(instructionPointer + parameterCount, mode);
    }


    private enum MODE {
        POSITION, IMMEDIATE;

        static MODE of(char c) {
            if (c == '0') {
                return MODE.POSITION;
            } else {
                return MODE.IMMEDIATE;
            }
        }

    }

    //Parameters that an instruction writes to will never be in immediate mode.
    public class opOne implements IntFunction<Integer> {
        @Override
        public Integer apply(int value) {
            var par1 = dereference(1, value);
            var par2 = dereference(2, value);
            var target = dereferenceWithMode(instructionPointer + 3, MODE.IMMEDIATE);

            //System.out.println(String.format("%s,%s,%s", par1, par2, target));
            intCode.set(target, par1 + par2);
            instructionPointer += 4;

            return 0;
        }
    }

    public class opTwo implements IntFunction<Integer> {
        @Override
        public Integer apply(int value) {
            var par1 = dereference(1, value);
            var par2 = dereference(2, value);
            var target = dereferenceWithMode(instructionPointer + 3, MODE.IMMEDIATE);
            intCode.set(target, par1 * par2);
            instructionPointer += 4;

            //System.out.println(String.format("%s,%s,%s", par1, par2, target));

            return 0;
        }
    }

    /*
    Opcode 3 takes a single integer as input and saves it to the position given by its
    only parameter.
    For example, the instruction 3,50 would take an input value and store it at address 50.
    */
    public class opThree implements IntFunction<Integer> {
        @Override
        public Integer apply(int value) {
            Integer par1 = stack.pop();
            var target = dereferenceWithMode(instructionPointer + 1, MODE.IMMEDIATE);
//           var target = dereference(1, value);
            intCode.set(target, par1);
            instructionPointer += 2;

            //System.out.println(String.format("%s,%s", par1, target));
            return 0;
        }
    }

    /*
     Opcode 4 outputs the value of its only parameter.
     For example, the instruction 4,50 would output the value at address 50.
     */
    public class opFour implements IntFunction<Integer> {
        @Override
        public Integer apply(int value) {
            //var one = dereferenceWithMode(instructionPointer + 1, MODE.POSITION); //intCode.get(intCode.get(instructionPointer + 1));;
            var one = dereference(1, value);
            //stack.push(one);
            System.out.println(one);
            instructionPointer += 2;
           // System.out.println(String.format("%s", one));
            return 0;
        }
    }

}
