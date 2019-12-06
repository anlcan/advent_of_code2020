package com.advent.one;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created on 06.12.19.
 */
public class Module {

    private final BigDecimal mass;
    private static final BigDecimal TWO =  BigDecimal.valueOf(2);
    private static final BigDecimal THREE =  BigDecimal.valueOf(3);

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
        return mass.divide(THREE, RoundingMode.DOWN).subtract(TWO);
    }

    public int requiredFullFuel (){
        return requiredFullFuel(this.mass).intValue();
    }

    /**
     * Fuel itself requires fuel just like a module - take its mass, divide by three, round down, and subtract 2.
     * However, that fuel also requires fuel, and that fuel requires fuel, and so on. Any mass that would require negative fuel should
     * instead be treated as if it requires zero fuel; the remaining mass, if any, is instead handled by wishing really hard,
     * which has no mass and is outside the scope of this calculation.
     */
    private static BigDecimal requiredFullFuel(BigDecimal mass){
        BigDecimal fuel =  requiredFuel(mass);

        switch (fuel.compareTo(BigDecimal.ZERO)){
            case 0: return fuel;
            case 1: return fuel.add(requiredFullFuel(fuel));
            default:  return BigDecimal.ZERO;
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
