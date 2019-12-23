package com.advent.eight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created on 23.12.19.
 */
public class SpaceImageFormat {

    private final int width;
    private final int height;

    public Layer rendered;
    private List<Layer> layers = new ArrayList<>();

    public SpaceImageFormat(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public List<Layer> getLayers() {
        return layers;
    }

    public SpaceImageFormat read(final String input) {

        int size = width * height;
        IntStream.range(0, input.length() / size)
                .mapToObj(i -> new Layer(width, height).read(input.substring(size * i, size * ++i)))
                .forEach(l -> layers.add(l));

        return this;
    }

    public Layer layerWithFewestPixelCount(int pixel) {
        return layers.stream().sorted(Comparator.comparingInt(l -> l.countOf(pixel)))
                .collect(Collectors.toList()).get(0);
    }

    public Layer render() {

        this.rendered = new Layer(width, height);

        IntStream.range(0, height)
                .forEach(h -> IntStream.range(0, width)
                        .forEach(w -> {

                            for (int i = 0; i < layers.size(); i++) {
                                int c = layers.get(i).pixels[w][h];
                                if (c != 2) {
                                    rendered.pixels[w][h] = c;
                                    break;
                                }
                            }
                        }));


        return rendered;
    }

    public final class Layer {

        final int[][] pixels;
        private final int width;
        private final int height;
        int counts[];

        public Layer(int width, int height) {
            this.width = width;
            this.height = height;
            pixels = new int[width][height];
            counts = new int[3];
        }

        public Layer read(final String input) {
            //System.out.println(input);
            IntStream.range(0, height)
                    .forEach(h -> IntStream.range(0, width)
                            .forEach(w -> {
                                char c = input.charAt(width * h + w);
                                pixels[w][h] = Character.getNumericValue(c);
                                Integer index = Character.getNumericValue(c);
                                counts[index] = counts[index] + 1;
                            }));

            return this;
        }

        public int countOf(int i) {
            assert i < 3;
            return counts[i];
        }

        public void print() {
            IntStream.range(0, height)
                    .forEach(h -> IntStream.range(0, width)
                            .forEach(w -> {
                                if (pixels[w][h] ==1 ){
                                    System.out.print("\u25A0");
                                } else {
                                    System.out.print(" ");
                                }
                                if (w == width-1) {
                                    System.out.println();
                                }

                            }));
        }
    }
}
