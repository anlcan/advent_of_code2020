package com.advent.seven;

import com.advent.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 21.12.19.
 */
public class AmplifierController {

    public final String computerCode;
    public final List<Amplifier> amplifiers = new ArrayList<>();

    public AmplifierController(final String input) {
        this.computerCode = input;
        IntStream.rangeClosed(0, 4).forEach(i -> amplifiers.add(Amplifier.of(input)));
    }

    public static int run(final String computerCode, final String sequence) {
        return new AmplifierController(computerCode).run(sequence);
    }

    public static int loopback(final String computerCode, final String sequence) {
        return new AmplifierController(computerCode).loopback(sequence);
    }

    public int run(final String sequence) {
        return run(Util.parseCSI(sequence));
    }

    private int run(final List<Integer> sequences) {
//        return sequences.stream()
//                .reduce(firstSignal, (input1, input2) -> Amplifier.of(computerCode).setPhase(input2).execute(input1));

        amplifiers.forEach(it -> it.stopOnOUtput(false));
        return loopback(sequences);
    }



    /**** LOOPBACK ****/

    public int loopback(final String sequence) {
        return loopback(Util.parseCSI(sequence));
    }

    private int loopback(List<Integer> sequence) {
        IntStream.rangeClosed(0, 4).forEach(it -> {
            amplifiers.get(it).setPhase(sequence.get(it));
        });

        int value = IntStream.rangeClosed(0, 4)
                .reduce(0, (acc, index) -> amplifiers.get(index).execute(acc));

        while (!amplifiers.stream().allMatch(Amplifier::isHalted)) {

            value = IntStream.rangeClosed(0, 4)
                    .reduce(value, (acc, index) -> {
                        System.out.println(acc + " " + index + " " + amplifiers.get(index).phase);
                        return amplifiers.get(index).computer.execute(acc);

                    });
            System.out.println("thrust: " + value);
        } ;
        return value;

    }

    public static int highestLoopbackSignal(final String computerCode) {
        return Permutations.of(Arrays.asList(5,6,7,8,9))
                .map(it -> it.map(String::valueOf).collect(Collectors.joining(",")))
                .mapToInt(it -> AmplifierController.loopback(computerCode,it))
                .max().getAsInt();
    }


    public static int highestSignal(final String computerCode) {
        return Permutations.of(Arrays.asList(0,1,2,3,4))
                .map(it -> it.map(String::valueOf).collect(Collectors.joining(",")))
                .mapToInt(it -> AmplifierController.run(computerCode, it))
                .max().getAsInt();
    }
}
