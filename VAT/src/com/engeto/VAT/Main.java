package com.engeto.VAT;

import java.time.LocalDate;
import java.util.*;

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
                System.out.print(state.getSign()+ ", "); // je třeba naformátovat bez poslední čárky
            }
        }

        System.out.println("\n");

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




//        String courseMessage = "Přehled států: " + stateList.getAllStates();
//        for (int i = 1; i<stateList.sizeOfList();++i) {
//            courseMessage += ", " + stateList.getStates(i);
//        }
//        System.out.println(courseMessage);

//        for (String userName : studentEnrollments.keySet()) {
//            System.out.println("Student: " + userName);
//            ArrayList<String> courses = studentEnrollments.get(userName);
//            String courseMessage = "Courses: ";
//            for (String singleCourse : courses) {
//                courseMessage += singleCourse + ", ";
//                System.out.println(courseMessage);
//            }
//        }
//        String message = "Přehled států: ";
//        String separator = "";
//        for (State state : stateList.getAllStates()) {
//            message += separator + state.getSign();
//            System.out.println(message);
//            separator = ",";
//        }

//        for(int i = 0; i < stateList.sizeOfList(); i++) {
//            for (State state : stateList.getAllStates()) {
//                if (state.isOver20PercentVATAndWithoutSpecialVAT() == false){
//                    System.out.print(state.getSign()+", ");
//                }
//            }
//        }




    }
}
