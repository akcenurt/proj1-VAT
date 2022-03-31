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

    // standardní metody listu:

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



    // načtení z výchozího souboru:

    public void loadFromFile (String filename) throws VATException{
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            int lineNumber = 0;
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                lineNumber++;
                try {
                    this.addState(State.parse(inputLine, DELIMITER));
                } catch (VATException e) {
                    throw new VATException("Neplatný vstupní soubor "+filename+" na řádku "+lineNumber+":\n\t"+e.getLocalizedMessage());
                }
            }
    } catch (FileNotFoundException e) {
            throw new VATException("Vstupní soubor "+filename+" nebyl nalezen: "+e.getLocalizedMessage());
        }
    }

    // tisk států s VAT nad 20%:

    public void printStatesOver20() {
        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně seřazeny sestupně dle DPH:\n");
        for (State state : states) {
            System.out.println(state.getStateSpecialFormatInfo2());
        }
    }

    // tisk zkratek států s VAT <= 20%:

    public void printLineOfSignsUnder20() {
        List <String> statesUnder20 = new ArrayList<>();
        for (State state : states) { //pro státy z stateLIstUnder20
            statesUnder20.add(state.getSign());
        }
        System.out.print("=================\nStáty s DPH 20 % a nižší NEBO používající speciální sazbu: ");
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

    // tisk zkratek států s VAT <= userInput%:

    public void printLineOfSignsUnderFilter() {
        List <String> statesUnderFilter = new ArrayList<>();
        for (State state : states) {
            statesUnderFilter.add(state.getSign());
        }

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


}
