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


    /**
     * Read one integer. If given input cannot be converted to integer,
     * prints error and return 0;
     * @return Integer read or 0 if invalid input entered
     */

    public static int safeReadInt() {
        int result = 20;
        String inputText = getScanner().nextLine();
        try {
            result = Integer.parseInt(inputText);
            if (result == 0){result = 20;}
        } catch (NumberFormatException ex) {
            System.err.println("You have entered nothing or ZERO. Default value has been set to 20.");
        }
        return result;
    }
}
