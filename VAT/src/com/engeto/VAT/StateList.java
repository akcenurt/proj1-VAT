package com.engeto.VAT;

import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.*;

public class StateList {
    List<State> states = new ArrayList<>();


// copy of a list
    public List<State> getAllStates() {
        // Vytvořím kopii listu a tu poskytnu jako výsledek volání:
        return new ArrayList<>(states);
    }

//    public List<State> getStates(int i) {
//        return states;
//    }
//
//    public void setStates(List<State> states) {
//        this.states = states;
//    }

    public void addState (State state) {
        states.add(state);
    }

    public void removeState (int index) {
        states.remove(index);
    }

    public int sizeOfList () {return states.size();}

    public void sortByFullVAT() {

        Collections.sort(states, new FullVATComparator());
    }

//    public void printStatesSignsWithComma () {
//        for(int i = 0; i < states.size(); i++) {
//            System.out.println(", ");
//        }
//    }
//
//    public static void printArray(int[] states)
//    {
//
//        System.out.print("[");
//        for(int i = 0; i < states.length; i++)
//        {
//            states[i] = i + 1;
//            if (i > 0)
//            {
//                System.out.print(", ");
//            }
//            System.out.print(states[i]);
//        }
//        System.out.println("]");
//    }



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
//
//
//
//    public void saveToFile (String inputFilename, String delimiter) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(inputFilename))) {
//            for (Plant plant : plants) {
//                String outputLine = plant.getName()+delimiter;
//                outputLine += plant.getNotes()+delimiter;
//                outputLine += plant.getFrequencyOfWatering()+delimiter;
//                outputLine += plant.getWatering().toString()+delimiter;
//                outputLine += plant.getPlanted().toString()+delimiter;
//                writer.println(outputLine);
//
//            } }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void exportToFile(String output) throws PlantException {
//        int lineNumber = 0;
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))){
//            for (Plant plant : plants) {
//                String plantInLine = plant.prepareOutputString(DELIMITER);
//                writer.println(plantInLine);
//                lineNumber++;
//            }
//        } catch (IOException e) {
//            throw new PlantException("Chyba při zápisu: "+output+" řádek: "+lineNumber+": "+e.getLocalizedMessage());
//        }
//    }
}
