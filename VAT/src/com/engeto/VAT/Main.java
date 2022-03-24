package com.engeto.VAT;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static int readOneIntFromInput() {
        int filter = Reading.safeReadInt();
        return filter;
    }



    public static final String INPUT_FILENAME = "vat-eu.txt";
    public static final String DELIMITER = "\t";




    public static void main(String[] args) {

	    StateList stateList = new StateList();

    stateList.loadFromFile(INPUT_FILENAME, "\t");

    for (State state : stateList.getAllStates()) {
        System.out.println(state.getStateSpecialFormatInfo());
    }
//    for (State state : stateList.getAllStates()) { // metoda pro zkoušení přepisu desetinné čárky
//            System.out.println(state.getReducedVAT());
//        }

        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateOver20PercentVATOrWithoutSpecialVAT());
        }


//        ArrayList<State> states = new ArrayList<>(stateList);

//        List<State> states = new ArrayList<>();
//        states.add()
        Collections.sort(stateList.states, new FullVATComparator());
        Collections.reverse(stateList.states);
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateSpecialFormatInfo());
        }
        System.out.print("=================\nSazba VAT 20 % nebo nižší nebo používají speciální sazbu: "); // na toto napsat metodu, aby se dalo přidat do prepareOutputString
        for (State state : stateList.getAllStates()) {
            if (state.isOver20PercentVATAndWithoutSpecialVAT() == false){
                System.out.print(state.getSign()+ ", "); // je třeba naformátovat bez poslední čárky
            }
        }

        System.out.println("\n");

        int filter = readOneIntFromInput();
        if (filter == 0) {
            filter = 20;
        }
        String OUTPUT_FILENAME = "vat-over-"+filter+".txt"; // použít filter i v duplikátu metody isOver20PercentVATAndWithoutSpecialVAT

        try {
            stateList.exportToFile(OUTPUT_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }



        /* SLIBNÁ ČÁST TISKU BEZ ČÁRKY:

        String courseMessage = "Courses: ";
        Iterator<State> c = stateList.getAllStates().iterator(); // kdyby místo getAllStates bylo definováno, že chci o státu vědět zkratku a že se
        //má vztahovat pouze na státy, co splňují podmínku, fungovalo by

        while (c.hasNext()) {
            courseMessage += c.next();
            if (c.hasNext()) {
                courseMessage += ",";
            }
        }

        System.out.println(courseMessage);

        KONEC SLIBNÉ ČÁSTI */




    }
}
