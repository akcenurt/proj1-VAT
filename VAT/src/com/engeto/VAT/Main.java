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
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateSpecialFormatInfo());
        }

//        ArrayList<Customer> customers = new ArrayList<>();
//        customers.add(new Customer(1, "Karel", LocalDate.of(1979, 1,1)));
//        customers.add(new Customer(1, "Karel", LocalDate.of(2009, 1,1)));
//        customers.add(new Customer(2, "Zora", LocalDate.of(1999, 1,1)));
//        customers.add(new Customer(3, "Adam", LocalDate.of(1949, 1,1)));
//        Collections.sort(customers, new CustomerAgeComparator());
//        customers.forEach(c -> System.out.println(c.getName()+": "+c.getBirthDate()));


    }
}
