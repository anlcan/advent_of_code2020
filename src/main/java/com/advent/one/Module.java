package com.advent.one;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created on 06.12.19.
 */
public class Module {

    private final BigDecimal mass;
    private static final BigDecimal TWO =  BigDecimal.valueOf(2);

    public Module(int mass) {
        this.mass = new BigDecimal(mass);
    }

    /*
     * Fuel required to launch a given module is based on its mass. Specifically,
     * to find the fuel required for a module, take its mass, divide by three, round down, and subtract 2.
     * @return
     */
    public int requiredFuel(){
        return requiredFuel(this.mass).intValue();
    }

    private static BigDecimal requiredFuel(BigDecimal mass){
        return mass.compareTo(BigDecimal.ZERO) < 1? BigDecimal.ZERO : mass.divide(BigDecimal.valueOf(3), RoundingMode.DOWN).subtract(TWO);
    }

    public int requiredFullFuel (){
        return requiredFullFuel(this.mass).intValue();
    }

    private static BigDecimal requiredFullFuel(BigDecimal mass){

        if (mass.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal module =  requiredFuel(mass);
            return module.add(requiredFullFuel(module));
        } else {
            return mass;
        }
    }

    public int requiredFullFuelEx() {
        BigDecimal fuel = requiredFuel(this.mass);
        BigDecimal sum = BigDecimal.ZERO;

        while (fuel.compareTo(BigDecimal.ZERO) > 0) {
            sum = sum.add(fuel);
            fuel = requiredFuel(fuel);
        }
        return sum.intValue();
    }
}
