package com.advent2020.eight;


import com.advent.eight.SpaceImageFormat;
import com.advent2020.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created on 23.12.19.
 */
public class SifTest {

    @Test
    public void readTest() {

        SpaceImageFormat sif = new SpaceImageFormat(3,2);
        sif.read("123456789012");
        assertEquals(2, sif.getLayers().size());

    }

    @Test
    public void inputOneTest() {
        List<String> inputOne = Util.readStrings("/eight/input1.txt");
        final SpaceImageFormat sif = new SpaceImageFormat(25,6).read(inputOne.get(0));
        SpaceImageFormat.Layer fewestZero = sif.layerWithFewestPixelCount(0);

        assertEquals(2460, fewestZero.countOf(1) * fewestZero.countOf(2));
    }

    @Test
    public void partTwoTest() {
        List<String> inputOne = Util.readStrings("/eight/input1.txt");
        final SpaceImageFormat sif = new SpaceImageFormat(25,6).read(inputOne.get(0));
        sif.render();
        sif.rendered.print(); // LRFKU
    }
}
