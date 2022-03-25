package com.engeto.VAT;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class StateList {
    public static final String DELIMITER = "\t";
    List<State> states = new ArrayList<>();


// metoda poskytne kopii listu
    public List<State> getAllStates() {
        return new ArrayList<>(states);
    }


    public void addState (State state) {
        states.add(state);
    }

    public void removeState (int index) {
        states.remove(index);
    }

    public int sizeOfList () {return states.size();}


//    public void sortByFullVAT() {
//
//        Collections.sort(states, new FullVATComparator());
//    }

    // načtení z výchozího souboru:

    public void loadFromFile (String filename, String delimiter) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {

            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] parts = inputLine.split(delimiter);
                String sign = parts[0];
                String name = parts[1];
                String fullVAT = parts[2];
                String reducedVAT = parts[3];
                boolean isUsingSpecialVAT = Boolean.parseBoolean(parts[4]);

                State state = new State (sign, name, fullVAT, reducedVAT, isUsingSpecialVAT);
                states.add(state); }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // export do souboru:

    public void exportToFile(String output) throws IOException {
        int lineNumber = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))){
            for (State state : states) {
                String stateInLine = state.prepareOutputString(DELIMITER);
                writer.println(stateInLine);
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
