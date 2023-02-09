package dev.denux.Lotto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class SortTest {
    private final int[] array;
    private final int[] sortedArray;

    private SortTest() {
        array = generateIntArray(10_000);
        sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
    }

    @Test
    public void runTests() {
        System.out.println("Running tests with array length of " + array.length);
        System.out.println("Bubble Sort:");
        bubbleSortTest();
        System.out.println("\nDual Pivot Quick Sort:");
        dualPivotQuickSortTest();
        System.out.println("\n");
    }

    private void bubbleSortTest() {
        calculatePerformance(Sort::bubbleSort, array);
        Assertions.assertArrayEquals(array, sortedArray);
    }

    private void dualPivotQuickSortTest() {
        calculatePerformance(Sort::dualPivotQuicksort, array);
        Assertions.assertArrayEquals(array, sortedArray);
    }

    private void calculatePerformance(Consumer<int[]> fn, int[] array) {
        long startTime = System.nanoTime();
        fn.accept(array);
        long endTime = System.nanoTime();
        System.out.println("Time in nano-seconds: " + (endTime - startTime));
        System.out.println("Time in milli-seconds: " + (endTime - startTime) / 1000000);
    }

    private int[] generateIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt();
        }
        return array;
    }
}
