package dev.denux.Lotto;

public class ValidationUtil {

    private ValidationUtil() {}

    public static boolean isValidArray(int[] array) {
        for (int number : array) {
            if (number < 1 || number > 45) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidNumber(int number) {
        return number >= 1 && number <= 45;
    }
}
