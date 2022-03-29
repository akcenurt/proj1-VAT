package com.engeto.VAT;

import java.io.*;
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

    public State getState (int index) {
        return states.get(index);
    }


//    public void sortByFullVAT() {
//
//        Collections.sort(states, new FullVATComparator());
//    }

    // načtení z výchozího souboru:


    public void loadFromFile (String filename, String delimiter) throws IOException{
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
            throw new IOException("Chyba při čtení souboru.");
        }
    }

    // export do souboru:

    public void exportToFile(String output) throws IOException {
        int lineNumber = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))){
                for (State state : states) {
                String stateInLine = state.getStateSpecialFormatInfo();
                writer.println(stateInLine);
                lineNumber++;
            }
        } catch (IOException e) {
            throw new IOException("Chyba při zápisu: "+output+" řádek: "+lineNumber+": "+e.getLocalizedMessage());
        }
    }

//    public void exportToFile2(String output) throws IOException {
//        int lineNumber = 0;
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))){
//            for (State state : states) {
//                String stateInLine = state.getSign()+", ";
//                writer.println(stateInLine);
//                lineNumber++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void printStates1() {
        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně seřazeny sestupně:\n");
        for (State state : states) {
            System.out.println(state.getStateSpecialFormatInfo2());
        }
    }





    public void printLineOfSignsUnder20() {
        List <String> statesUnder20 = new ArrayList<>();
        for (State state : states) { //pro státy z stateLIstUnder20
            statesUnder20.add(state.getSign());
        }
        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: ");
        Iterator it;
        it = statesUnder20.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
            if (it.hasNext()){
                System.out.print(", ");
            }else{
                System.out.print(".");
            }
        }

    }

    public void printLineOfSignsUnderFilter() {
        List <String> statesUnderFilter = new ArrayList<>();
        for (State state : states) { //pro státy z stateLIstUnder20
            statesUnderFilter.add(state.getSign());
        }
//        System.out.print("=================\nSazba VAT pod zadanou hodnotu nebo používají speciální sazbu: ");
        Iterator it;
        it = statesUnderFilter.iterator();
        while(it.hasNext()){
            System.out.print(it.next());
            if (it.hasNext()){
                System.out.print(", ");
            }else{
                System.out.print(".");
            }
        }

    }

// POKUS O METODU, KTERÁ ZAHRNUJE ITERATOR A PŮJDE APLIKOVAT NA STATELIST:
//    public void printLineOfSignsUnder20() {
//        List <String> statesUnder20 = new ArrayList<>();
//        for (State state : states) { //pro státy z stateLIstUnder20
//            if (state.convertFullVATtoDouble() > 20 && !state.isUsingSpecialVAT){
//                statesUnder20.add(state.getSign());
//            }
//        }
//        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: ");
//        Iterator it;
//        it = statesUnder20.iterator();
//        while(it.hasNext()){
//            System.out.print(it.next());
//            if (it.hasNext()){
//                System.out.print(", ");
//            }else{
//                System.out.print(".");
//            }
//        }
//
//    }

}
