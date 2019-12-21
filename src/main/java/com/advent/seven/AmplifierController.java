package com.advent.seven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 21.12.19.
 */
public class AmplifierController {

    public final String computerCode;

    public AmplifierController(final String input) {
        this.computerCode = input;
    }

    public static int run(final String computerCode, final String sequence) {
        return new AmplifierController(computerCode).run(sequence);
    }

    public int run(final String sequence) {

        List<Integer> inputs = Arrays.stream(sequence.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        return run(inputs);
    }

//        int i0 = Amplifier.of(computerCode).execute(inputs.get(0), 0);
//        int i1 = Amplifier.of(computerCode).execute(inputs.get(1), i0);
//        int i2 = Amplifier.of(computerCode).execute(inputs.get(2), i1);
//        int i3 = Amplifier.of(computerCode).execute(inputs.get(3), i2);
//        return Amplifier.of(computerCode).execute(inputs.get(4), i3);
    private int run(final List<Integer> inputs){
        return inputs.stream()
                .reduce(0, (input1, input2) -> Amplifier.of(computerCode).execute(input2, input1));
    }

    public int highestSignal() {
        return Permutations.of(Arrays.asList(0, 1, 2, 3, 4))
                .mapToInt(it -> run(it.collect(Collectors.toList())))
                .max().getAsInt();
    }

}
