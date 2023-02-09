package dev.denux.Lotto;

public class Sort {

    private Sort() {}

    public static void bubbleSort(int[] array) {
        //boolean variable called swapped
        boolean swapped;
        do {
            //set swapped to false
            swapped = false;
            //for loop that loops through the array
            for (int i = 0; i < array.length - 1; i++) {
                //if statement that checks if the current element is greater than the next element
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }
            //while loop that loops while swapped is true
        } while (swapped);
    }

    public static void dualPivotQuicksort(int[] array) {
        dualPivotQuicksort(array, 0, array.length - 1);
    }
    public static void dualPivotQuicksort(int[] array, int low, int high) {
        //implement Dual-Pivot Quicksort algorithm
        if (low < high) {
            int[] pivots = partition(array, low, high);
            dualPivotQuicksort(array, low, pivots[0] - 1);
            dualPivotQuicksort(array, pivots[0] + 1, pivots[1] - 1);
            dualPivotQuicksort(array, pivots[1] + 1, high);
        }

    }

    private static int[] partition(int[] array, int low, int high) {
        if (array[low] > array[high]) swap(array, low, high);

        // p is the left pivot, and q
        // is the right pivot.
        int j = low + 1;
        int g = high - 1, k = low + 1,
        p = array[low], q = array[high];

        while (k <= g) {

            // If elements are less than the left pivot
            if (array[k] < p) {
                swap(array, k, j);
                j++;
            }

            // If elements are greater than or equal
            // to the right pivot
            else if (array[k] >= q) {
                while (array[g] > q && k < g)
                    g--;

                swap(array, k, g);
                g--;

                if (array[k] < p)
                {
                    swap(array, k, j);
                    j++;
                }
            }
            k++;
        }
        j--;
        g++;

        // Bring pivots to their appropriate positions.
        swap(array, low, j);
        swap(array, high, g);

        // Returning the indices of the pivots
        // because we cannot return two elements
        // from a function, we do that using an array.
        return new int[] { j, g };
    }


    private static void swap(int[] array, int i, int j) {
        //temporary variable to hold the value of the current element
        int temp = array[i];
        //set the current element to the next element
        array[i] = array[j];
        //set the next element to the temporary variable
        array[j] = temp;
    }
}
