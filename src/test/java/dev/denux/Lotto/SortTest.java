package dev.denux.Lotto;

import com.sun.tools.javac.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class SortTest {

    @Test
    public void runTests() {
        int length = 50_000;
        System.out.println("\nRunning tests with array length of " + length);
        System.out.println("Bubble Sort:");
        bubbleSortTest(length);
        System.out.println("\nSelection Sort:");
        selectionSortTest(length);
        System.out.println("\nDual Pivot Quick Sort:");
        dualPivotQuickSortTest(length);
        System.out.println("\n");
    }

    private void bubbleSortTest(int length) {
        performTest(IntSorter::bubbleSort, length);
    }

    private void dualPivotQuickSortTest(int length) {
        performTest(IntSorter::dualPivotQuicksort, length);
    }

    private void selectionSortTest(int length) {
        performTest(IntSorter::selectionSort, length);
    }

    private void performTest(Consumer<int[]> fn, int length) {
        Pair<int[], int[]> pair = generateArrayPair(length);
        long startTime = System.nanoTime();
        fn.accept(pair.fst);
        long endTime = System.nanoTime();
        Assertions.assertArrayEquals(pair.snd, pair.fst);
        System.out.println("Time in nano-seconds: " + (endTime - startTime));
        System.out.println("Time in milli-seconds: " + (endTime - startTime) / 1000000);
    }

    private Pair<int[], int[]> generateArrayPair(int length) {
        int[] array = generateIntArray(length);
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        return new Pair<>(array, sortedArray);
    }

    private int[] generateIntArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = ThreadLocalRandom.current().nextInt();
        }
        return array;
    }
}
