package com.advent.five;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created on 15.12.19.
 */
public class Computer {

    public final Map<Integer, IntFunction<Integer>> operations = new HashMap<>();
    protected final List<Integer> intCode;
    protected int instructionPointer = 0;
    protected Stack<Integer> stack = new Stack<>();
    ;

    public Computer(List<Integer> intCode) {
        this.intCode = intCode;
        operations.put(1, new opOne());
        operations.put(2, new opTwo());
        operations.put(3, new opThree());
        operations.put(4, new opFour());
        operations.put(5, new opFive());
        operations.put(6, new opSix());
        operations.put(7, new opSeven());
        operations.put(8, new opEight());
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

    public static MODE dereferenceMode(int paramIndex, int instruction) {
        String s = new StringBuilder(String.valueOf(instruction)).reverse().toString();
        MODE mode = MODE.POSITION; // 0 position mode, 1 immediate mode
        if (s.length() > paramIndex + 1) {
            mode = MODE.of(s.charAt(paramIndex + 1));
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
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                {
                operations.get(opCode).apply(instruction);
                yield execute();
            }
            case 99:
                System.out.println(stack.empty()?-9999:stack.peek());
                System.out.println("halted");
                //yield intCode.get(0);
                yield stack.peek();
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
            var one = dereference(1, value);
            stack.push(one);
            System.out.println(one);
            instructionPointer += 2;
           // System.out.println(String.format("%s", one));
            return 0;
        }
    }

    public Integer genericJump(int value, Predicate<Integer> p) {
        var param = dereference(1, value);
        if (p.test(param)) {
//            instructionPointer = dereferenceWithMode(instructionPointer + 2, MODE.IMMEDIATE);
            instructionPointer = dereference( 2, value);
        } else {
            instructionPointer += 3;
        }
        return 0;
    }



    // Opcode 5 is jump-if-true
    public class opFive implements IntFunction<Integer> {

        @Override
        public Integer apply(int value) {
            genericJump(value, it -> it != 0);
            return 0;
        }
    }

    // Opcode 6 is jump-if-false
    public class opSix implements IntFunction<Integer> {

        @Override
        public Integer apply(int value) {
            genericJump(value, it -> it == 0);
            return 0;
        }
    }

    public Integer genericCompare(int value, BiPredicate<Integer, Integer> p){
        //var one = dereferenceWithMode(instructionPointer + 1, MODE.POSITION); //intCode.get(intCode.get(instructionPointer + 1));;
        var param1 = dereference(1, value);
        var param2 = dereference(2, value);
        var target = dereferenceWithMode(instructionPointer + 3, MODE.IMMEDIATE);

        if (p.test(param1, param2)) {
//            var target = dereference(3, value);
            intCode.set(target, 1);
        } else {
            intCode.set(target, 0);
        }
        instructionPointer += 4;
        return 0;
    }

    // Opcode 7 is less than: if the first parameter is less than the second parameter,
    //it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
    public class opSeven implements IntFunction<Integer> {

        @Override
        public Integer apply(int value) {
            genericCompare(value, (p1, p2 )->  p1 < p2);
            return 0;
        }
    }

    // Opcode 8 is equals: if the first parameter is equal to the second parameter,
    //it stores 1 in the position given by the third parameter. Otherwise, it stores 0.
    public class opEight implements IntFunction<Integer> {

        @Override
        public Integer apply(int value) {
            genericCompare(value, Integer::equals);
            return 0;
        }
    }
}
