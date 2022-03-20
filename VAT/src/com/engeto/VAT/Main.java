package com.engeto.VAT;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {


    public static void main(String[] args) {
	StateList stateList = new StateList();

    stateList.loadFromFile("vat-eu.txt", "\t");

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
        System.out.print("=================\nSazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");
        for (State state : stateList.getAllStates()) {
            if (state.isOver20PercentVATAndWithoutSpecialVAT() == false){
                System.out.print(state.getSign()+", "); // je třeba naformátovat bez poslední čárky
            }
        }



    }
}
