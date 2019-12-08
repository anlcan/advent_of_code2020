package com.advent.four;

import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 08.12.19.
 */
public class PasswordCracker {

    private final int start;
    private final int end;


    public PasswordCracker(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int possibilities() {
        List<Integer> collect = IntStream.rangeClosed(start, end)
                .filter(getDoubleDigit())
                .filter(getIncreasing())
                .boxed()
                .collect(Collectors.toList());


        return collect.size();
    }

    public static IntPredicate getDoubleDigit(){
        return (int x) -> {
            String s = String.valueOf(x);
            for (int i = 0; i < s.length()-1; i++) {
                if (s.charAt(i) == s.charAt(i+1))
                    return true;
            }
            return false;
        };
    }

    public static IntPredicate getIncreasing() {
        return (int x) -> {

            String s1 = String.valueOf(x);
            int[] s = s1.chars().toArray();

            for (int i = 0; i < s1.length()-1; i++) {

                if (s[i] > s[i+1])
                    return false;
            }
            return true;
        };
    }

}
