package dev.denux.Lotto;

import dev.denux.Lotto.sorter.DualPivotSort;

public class IntSorter {

    private IntSorter() {}

    public static void sort(int[] array) {
        dualPivotQuicksort(array);
    }

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

    public static void selectionSort(int[] array) {
        //for loop that loops through the array
        for (int i = 0; i < array.length - 1; i++) {
            //set the current index to the minimum index
            int minIndex = i;
            //for loop that loops through the array starting at the current index + 1
            for (int j = i + 1; j < array.length; j++) {
                //if statement that checks if the current element is less than the element at the minimum index
                if (array[j] < array[minIndex]) {
                    //set the minimum index to the current index
                    minIndex = j;
                }
            }
            //swap the current element with the element at the minimum index
            if (minIndex != i) swap(array, i, minIndex);
        }
    }

    public static void insertionSort(int[] array) {
        //for loop that loops through the array
        for (int i = 1; i < array.length; i++) {
            //set the current element to the temporary variable
            int temp = array[i];
            //set the current index to the previous index
            int j = i - 1;
            //while loop that loops while the current index is greater than or equal to 0 and the element at the current index is greater than the temporary variable
            while (j >= 0 && array[j] > temp) {
                //set the element at the current index + 1 to the element at the current index
                array[j + 1] = array[j];
                //decrement the current index
                j--;
            }
            //set the element at the current index + 1 to the temporary variable
            array[j + 1] = temp;
        }
    }

    public static void dualPivotQuicksort(int[] array) {
        dualPivotQuicksort(array, 0, array.length - 1);
    }
    public static void dualPivotQuicksort(int[] array, int low, int high) {
        DualPivotSort.sort(array, low, high);
    }

    public static void swap(int[] array, int i, int j) {
        //temporary variable to hold the value of the current element
        int temp = array[i];
        //set the current element to the next element
        array[i] = array[j];
        //set the next element to the temporary variable
        array[j] = temp;
    }
}
