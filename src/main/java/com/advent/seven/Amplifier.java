package com.advent.seven;

import com.advent.five.Computer;

/**
 * Created on 21.12.19.
 */
public class Amplifier {

    final Computer computer;
    public int phase = -1;

    public Amplifier setPhase(int phase) {
        assert this.phase == -1;
        assert phase < 10 && phase >= 0;
        this.phase = phase;
        return this;
    }

    public static Amplifier of(final String input){
        return new Amplifier(Computer.of(input));
    }

    private Amplifier(Computer computer) {
        this.computer = computer;
        computer.stopOnOutput = true;
    }

    public int execute(int inputTwo) {
        return computer.execute(phase, inputTwo);
    }

    public boolean isHalted(){
        return computer.isHalted();
    }

    public void stopOnOUtput(boolean b){
        computer.stopOnOutput = b;
    }
}
