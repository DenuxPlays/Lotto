package dev.denux.Lotto.sorter;

import dev.denux.Lotto.IntSorter;

public class DualPivotSort {

    /**
     * Threshold of mixed insertion sort is incremented by this value.
     */
    private static final int DELTA = 3 << 1;
    /**
     * Max recursive partitioning depth before using heap sort.
     */
    private static final int MAX_RECURSION_DEPTH = 64 * DELTA;

    public static void sort(int[] array, int low, int high) {
        sort(array, 0, low, high);
    }

    /**
     * Sorts the specified array using the Dual-Pivot Quicksort and/or
     * other sorts in special-cases.
     *
     * @param array the array to be sorted
     * @param low   the index of the first element, inclusive, to be sorted
     * @param high  the index of the last element, exclusive, to be sorted
     */
    private static void sort(int[] array,int bits, int low, int high) {
        /*
         * Switch to heap sort if execution
         * time is becoming quadratic.
         */
        if ((bits += DELTA) > MAX_RECURSION_DEPTH) {
            heapSort(array, low, high);
            return;
        }

        if (low < high) {
            int[] pivots = partition(array, low, high);
            sort(array, bits | 1, low, pivots[0] - 1);
            sort(array, bits | 1, pivots[0] + 1, pivots[1] - 1);
            sort(array, bits | 1, pivots[1] + 1, high);
        }
    }

    private static int[] partition(int[] array, int low, int high) {
        if (array[low] > array[high]) IntSorter.swap(array, low, high);

        //p is the left pivot
        //q is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
                p = array[low], q = array[high];

        while (k <= g) {

            //checks if elements are less than the left pivot
            if (array[k] < p) {
                IntSorter.swap(array, k, j);
                j++;
            }

            //checks if elements are greater than or equal
            //to the right pivot
            else if (array[k] >= q) {
                while (array[g] > q && k < g)
                    g--;

                IntSorter.swap(array, k, g);
                g--;

                if (array[k] < p) {
                    IntSorter.swap(array, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        //swap pivots into their final positions
        IntSorter.swap(array, low, j);
        IntSorter.swap(array, high, g);

        //returning the both indices of the pivots
        return new int[] {j, g};
    }

    /**
     * Sorts the specified range of the array using heap sort.
     *
     * @param array the array to be sorted
     * @param low the index of the first element, inclusive, to be sorted
     * @param high the index of the last element, exclusive, to be sorted
     */
    private static void heapSort(int[] array, int low, int high) {
        for (int k = (low + high) >>> 1; k > low; ) {
            pushDown(array, --k, array[k], low, high);
        }
        while (--high > low) {
            int max = array[low];
            pushDown(array, low, array[high], low, high);
            array[high] = max;
        }
    }

    /**
     * Pushes specified element down during heap sort.
     *
     * @param array the given array
     * @param p the start index
     * @param value the given element
     * @param low the index of the first element, inclusive, to be sorted
     * @param high the index of the last element, exclusive, to be sorted
     */
    private static void pushDown(int[] array, int p, int value, int low, int high) {
        for (int k ;; array[p] = array[p = k]) {
            k = (p << 1) - low + 2; // Index of the right child

            if (k > high) {
                break;
            }
            if (k == high || array[k] < array[k - 1]) {
                --k;
            }
            if (array[k] <= value) {
                break;
            }
        }
        array[p] = value;
    }
}
