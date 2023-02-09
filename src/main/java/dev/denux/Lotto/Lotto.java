package dev.denux.Lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A simple Lotto game that was created for a school project.<br>
 * Lotto 6 aus 45
 */
public class Lotto {
    /**
     * The numbers that the user guessed.
     */
    private final int[] guesses;
    /**
     * The winning numbers that were randomly generated.
     */
    private final int[] winNumbers;

    /**
     * The main method that is executed when the program is started.<br>
     * It asks the user to enter 6 numbers and then calls the play method with the array of numbers the user entered.
     * @param args The arguments that were passed to the program.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] guesses = new int[6];
        for (int i = 0; i < guesses.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            int number = scanner.nextInt();
            if (!ValidationUtil.isValidNumber(number)) {
                System.out.println("You must enter a number between 1 and 45");
                i--;
                continue;
            }
            guesses[i] = number;
        }
        //Printing a new line for readability -> mostly if an exception occurs
        System.out.println("\n");
        play(guesses);
    }

    /**
     * The method that is called when the user enters 6 numbers or if BlueJ or similar test-editor/IDE is used.<br>
     * Checks if the given array has a length of 6 and if all numbers are between 1 and 45.<br>
     * Also, it creates a new {@link Lotto} instance and calls the {@link Lotto#printWins()} method.
     * @param guesses The int array that contains 6 numbers between 1 and 45.
     */
    public static void play(int[] guesses) {
        if (guesses.length != 6) {
            throw new IllegalArgumentException("You must enter 6 numbers");
        }
        if (!ValidationUtil.isValidArray(guesses)) {
            throw new IllegalArgumentException("You must enter numbers between 1 and 45");
        }
        Lotto lotto = new Lotto(guesses);
        lotto.printWins();
    }

    private Lotto(int[] guesses) {
        Sort.bubbleSort(guesses);
        this.guesses = guesses;
        this.winNumbers = generateWinNumbers();
        checkForDuplications();
    }

    public void printWins() {
        int[] sameNumbers = this.checkForSameNumbers();
        System.out.println("You guessed " + sameNumbers.length + "/6 numbers correctly");
        if (sameNumbers.length != 0) {
            System.out.println("You guessed the following numbers: " + Arrays.toString(sameNumbers));
        }
        System.out.println("The winning numbers were: " + Arrays.toString(this.getWinNumbers()));
    }

    public int[] checkForSameNumbers() {
        List<Integer> sameNumbers = new ArrayList<>();
        for (int guess : guesses) {
            for (int winNumber : winNumbers) {
                if (guess == winNumber) {
                    sameNumbers.add(guess);
                }
            }
        }
        return sameNumbers.stream().mapToInt(i -> i).toArray();
    }

    public int[] getWinNumbers() {
        return winNumbers;
    }

    private void checkForDuplications() {
        for (int i = 0; i < guesses.length - 1; i++) {
            if (guesses[i] == guesses[i + 1]) {
                throw new IllegalArgumentException("You must enter unique numbers");
            }
        }
    }

    private int[] generateWinNumbers() {
        int[] winNumbers = new int[6];
        //Note: an enhanced for-loop cannot be used because primitives are passed by value not by reference.
        for (int i = 0; i < winNumbers.length; i++) {
            int winNumber = (int) (Math.random() * 45 + 1);
            //Make sure that all numbers are unique.
            if (Arrays.stream(winNumbers).anyMatch(x -> x == winNumber)) {
                i--;
                continue;
            }
            winNumbers[i] =winNumber;
        }
        //Finally, sorting the array for readability.
        Sort.dualPivotQuicksort(winNumbers);
        return winNumbers;
    }
}