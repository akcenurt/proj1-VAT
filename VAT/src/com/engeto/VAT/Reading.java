package com.engeto.VAT;

import java.util.Scanner;

public class Reading {

    private static Scanner scanner = null;
    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static int INVALID_INPUT = 0;

    /**
     * Read one integer. If given input cannot be converted to integer,
     * prints error and return 0;
     * @return Integer read or 0 if invalid input entered
     */

    public static int safeReadInt() {
        int result = INVALID_INPUT;
        String inputText = getScanner().nextLine();
        try {
            result = Integer.parseInt(inputText);
        } catch (NumberFormatException ex) {
            System.err.println("You entered nothing. Default value has been set to 20");
        }
        return result;
    }
}
