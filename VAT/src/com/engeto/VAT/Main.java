package com.engeto.VAT;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.*;

public class Main {


// načte input:
//    public static double readOneIntFromInput() {
//        double filter = Reading.safeReadInt();
//        if (filter == 0) { filter = 20; }
//        return filter;
//    }

    public static final String INPUT_FILENAME = "vat-eu.txt";

    public static void main(String[] args) {

//        1. Načti ze souboru všechna data do vhodné datové struktury (vytvoř třídu pro uložení dat):

	    StateList stateList = new StateList();

        try {
            stateList.loadFromFile(INPUT_FILENAME, "\t");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        2. Vypiš seznam všech států a u každého uveď základní sazbu daně z přidané hodnoty ve formátu podle vzoru: Název země (zkratka): základní sazba %
        System.out.println("Stát (zkratka): základní sazba DPH: \n");
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateSpecialFormatInfo());
        }
        System.out.println("---------------------\n");

//        3. Vypište ve stejném formátu pouze státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % a přitom nepoužívají speciální sazbu daně.
        // Stát nesplňující uvedenou podmínku nahradí "..."
        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně:\n");

        StateList stateListOver20 = new StateList();
        StateList stateListUnder20 = new StateList();
        for (State state : stateList.getAllStates()) {
            if (state.isOver20PercentVATAndWithoutSpecialVAT() == true) {
                stateListOver20.addState(state);
            } else {
                stateListUnder20.addState(state);
            }
        }

        for (State state : stateListOver20.getAllStates()) {
            System.out.println(state.getStateSpecialFormatInfo2());
        }
        System.out.println("----------------------\n");

//        4. Výpis z bodu 3. seřaď podle výše základní sazby DPH/VAT sestupně (nejprve státy s nejvyšší sazbou).

        Collections.sort(stateListOver20.states, new FullVATComparator());
        Collections.reverse(stateListOver20.states);

        stateListOver20.printStates1();

//        5. Pod výpis z bodu 3. doplň řádek s rovnítky pro oddělení a poté seznam zkratek států, které ve výpisu nefigurují.
//        Opět dodrž formát podle vzoru (místo tří teček budou další státy):


        stateListUnder20.printLineOfSignsUnder20();


        System.out.println();
        System.out.println("------------------\n");

//        6. Výsledný výpis zapiš také do souboru s názvem vat-over-20.txt. Výstupní soubor ulož do stejné složky, ve které byl vstupní soubor.
//        (Výpis na obrazovku zůstává.)


// POKUS printwriter ne v metodě statelist, ale v hlavní metodě main:
        String OUTPUT_FILENAME = "vat-over-20.txt";
        int lineNumber = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILENAME)))){
            for (State state : stateListOver20.getAllStates()) {
                String stateInLine = state.getStateSpecialFormatInfo();
                writer.println(stateInLine);
                lineNumber++;
            }
            writer.println("=======================");
            writer.println("Státy nesplňující podmínku: ");

                for (int i = 0; i < stateListUnder20.sizeOfList();i++) {
                    writer.print(stateListUnder20.getState(i).getSign());
                    if (i != (stateListUnder20.sizeOfList() - 1)){
                        writer.print(", ");
                    }
                }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        String OUTPUT_FILENAME = "vat-over-20.txt";


//        try {
//            stateListOver20.exportToFile(OUTPUT_FILENAME);
////            stateListUnder20.exportToFile2(OUTPUT_FILENAME); //toto se přepíše
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        7. Doplň možnost, aby uživatel z klávesnice zadal výši sazby DPH/VAT, podle které se má filtrovat.
//        Vypíší se tedy státy se základní sazbou vyšší než ta, kterou uživatel zadal.
//        a) Pokud uživatel zmáčkne pouze Enter, jako výchozí hodnota se použije sazba 20 %.
//        b) Uprav název výstupního souboru tak, aby reflektoval zadanou sazbu daně.
//        Například pro zadanou sazbu 17 % se vygeneruje soubor vat-over-17.txt a pro sazbu 25 % se vygeneruje soubor vat-over-25.txt.


        System.out.println("Zadej filtr: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Double filter;
        if (input.isEmpty()){
            filter = 20.0;
            StateList stateListOverFilter = new StateList();
            StateList stateListUnderFilter = new StateList();
            for (State state : stateList.getAllStates()) {
                if (state.isOverXPercentVAT(filter) == true) {
                    stateListOverFilter.addState(state);
                } else {
                    stateListUnderFilter.addState(state);
                }
            }

            Collections.sort(stateListOverFilter.states, new FullVATComparator());
            Collections.reverse(stateListOverFilter.states);

            for (State state : stateListOverFilter.getAllStates()) {
                System.out.println(state.getStateSpecialFormatInfo2());
            }

            System.out.print("=================\nSazba VAT "+filter+" % a nižší nebo používají speciální sazbu: ");
            stateListUnderFilter.printLineOfSignsUnderFilter();
            String NEXTOUTPUT_FILENAME = "vat-over-"+filter+".txt";
            lineNumber = 0;
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(NEXTOUTPUT_FILENAME)))){
                for (State state : stateListOverFilter.getAllStates()) {
                    String stateInLine = state.getStateSpecialFormatInfo();
                    writer.println(stateInLine);
                    lineNumber++;
                }
                writer.println("=======================");
                writer.println("Státy nesplňující podmínku: ");

                for (int i = 0; i < stateListUnderFilter.sizeOfList();i++) {
                    writer.print(stateListUnderFilter.getState(i).getSign());
                    if (i != (stateListUnderFilter.sizeOfList() - 1)){
                        writer.print(", ");
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
        filter = Double.parseDouble(input);
            StateList stateListOverFilter = new StateList();
            StateList stateListUnderFilter = new StateList();
            for (State state : stateList.getAllStates()) {
                if (state.isOverXPercentVAT(filter) == true) {
                    stateListOverFilter.addState(state);
                } else {
                    stateListUnderFilter.addState(state);
                }
            }

            Collections.sort(stateListOverFilter.states, new FullVATComparator());
            Collections.reverse(stateListOverFilter.states);

            for (State state : stateListOverFilter.getAllStates()) {
                System.out.println(state.getStateSpecialFormatInfo2());
            }

            System.out.print("=================\nSazba VAT "+filter+" % a nižší nebo používají speciální sazbu: ");
            stateListUnderFilter.printLineOfSignsUnderFilter();
            String NEXTOUTPUT_FILENAME = "vat-over-"+filter+".txt";
            lineNumber = 0;
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(NEXTOUTPUT_FILENAME)))){
                for (State state : stateListOverFilter.getAllStates()) {
                    String stateInLine = state.getStateSpecialFormatInfo();
                    writer.println(stateInLine);
                    lineNumber++;
                }
                writer.println("=======================");
                writer.println("Státy nesplňující podmínku: ");

                for (int i = 0; i < stateListUnderFilter.sizeOfList();i++) {
                    writer.print(stateListUnderFilter.getState(i).getSign());
                    if (i != (stateListUnderFilter.sizeOfList() - 1)){
                        writer.print(", ");
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        if (filter == 0) {filter = 20; }
//        cheese = ""; // empty 'cheese' of prior input
//        cheese = in.nextLine(); //read in a line of input
//
////if user didn't enter anything (or just spacebar and return)
//        if(cheese.isEmpty()) {
//            System.out.println("Nothing was entered. Please try again");
//        }
////user entered something
//        else {
//[enter code here checking validity of input]
//        }

        // ok:

//        StateList stateListOverFilter = new StateList();
//        StateList stateListUnderFilter = new StateList();
//        for (State state : stateList.getAllStates()) {
//            if (state.isOverXPercentVAT(filter) == true) {
//                stateListOverFilter.addState(state);
//            } else {
//                stateListUnderFilter.addState(state);
//            }
//        }
//
//        Collections.sort(stateListOverFilter.states, new FullVATComparator());
//        Collections.reverse(stateListOverFilter.states);
//
//        for (State state : stateListOverFilter.getAllStates()) {
//            System.out.println(state.getStateSpecialFormatInfo2());
//        }
//
//        System.out.print("=================\nSazba VAT "+filter+" % a nižší nebo používají speciální sazbu: ");
//        stateListUnderFilter.printLineOfSignsUnderFilter();

        // POKUS printwriter ne v metodě statelist, ale v hlavní metodě main:

//        String OUTPUT_FILENAME = "vat-over-20.txt";
//        int lineNumber = 0;
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILENAME)))){
//            for (State state : stateListOver20.getAllStates()) {
//                String stateInLine = state.getStateSpecialFormatInfo();
//                writer.println(stateInLine);
//                lineNumber++;
//            }
//            writer.println("=======================");
//            writer.println("Státy nesplňující podmínku: ");
//
//            for (int i = 0; i < stateListUnder20.sizeOfList();i++) {
//                writer.print(stateListUnder20.getState(i).getSign());
//                if (i != (stateListUnder20.sizeOfList() - 1)){
//                    writer.print(", ");
//                }
//            }

        // ok2:

//        String NEXTOUTPUT_FILENAME = "vat-over-"+filter+".txt";
//        lineNumber = 0;
//        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(NEXTOUTPUT_FILENAME)))){
//            for (State state : stateListOverFilter.getAllStates()) {
//                String stateInLine = state.getStateSpecialFormatInfo();
//                writer.println(stateInLine);
//                lineNumber++;
//            }
//            writer.println("=======================");
//            writer.println("Státy nesplňující podmínku: ");
//
//            for (int i = 0; i < stateListUnderFilter.sizeOfList();i++) {
//                writer.print(stateListUnderFilter.getState(i).getSign());
//                if (i != (stateListUnderFilter.sizeOfList() - 1)){
//                    writer.print(", ");
//                }
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        String NEXTOUTPUT_FILENAME = "vat-over-"+filter+".txt";
//
//        try {
//            stateListOverFilter.exportToFile(NEXTOUTPUT_FILENAME);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
