package com.advent;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 21.12.19.
 */
public class Util {


    public static List<Integer> parseCSI(final String input){

        return  Arrays.stream(input.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }
}
