package com.engeto.VAT;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.*;

public class Main {


// načte input:
    public static double readOneIntFromInput() {
        double filter = Reading.safeReadInt();
        if (filter == 0) { filter = 20; }
        return filter;
    }


    public static final String INPUT_FILENAME = "vat-eu.txt";
//    public static int FILTER = readOneIntFromInput();




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
//        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně seřazeny sestupně:\n");
//        for (State state : stateListOver20.getAllStates()) {
//            System.out.println(state.getStateSpecialFormatInfo2());
//        }

        stateListOver20.printStates1();

//        5. Pod výpis z bodu 3. doplň řádek s rovnítky pro oddělení a poté seznam zkratek států, které ve výpisu nefigurují.
//        Opět dodrž formát podle vzoru (místo tří teček budou další státy):
        // Seznam států obsahuje čárku za posledním státem


        //POKUS O VYTVOŘENÍ STATELISTU PRO POTŘEBY EXPORTU S METODOU, KTERÁ VYPÍŠE STÁTY VEDLE SEBE BEZ ČÁRKY
//        StateList stateListUnder20 = new StateList();
//        stateListUnder20.printLineOfSignsUnder20();


        stateListUnder20.printLineOfSignsUnder20();


        //OK1:
//        List <String> statesUnder20 = new ArrayList<>();
//        for (State state : stateList.getAllStates()) {
//            if (state.convertFullVATtoDouble() < 20 && !state.isUsingSpecialVAT){ //tento výpis nefunguje dobře
//                statesUnder20.add(state.getSign());
//            }
//        }

// ŘEŠENÍ TVORBY TŘÍDY STATELIST, NA KTERÉ SE ALE NEDAJÍ POUŽÍT METODY NA LISTY (ASI BYCH MUSEL NAPOSAT METODY V STATELISTU)
//        StateList stateListUnder20 = new StateList();
//        for (State state : stateList.getAllStates()) {
//            if (state.isOver20PercentVATAndWithoutSpecialVAT() == false){
//                stateListUnder20.addState(state);
//            }
//        }


// NEDOKONČENÉ ŘEŠENÍ S IF:
//        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: ");
//        for (State state : stateListUnder20.getAllStates()){
//            System.out.println(state.getSign());
//            if (stateListUnder20.){ //list.size()-1 je poslední položka ze seznamu
//                System.out.println(" ,");
//            }
//        }
        //OK2:
//        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: ");

        // POKUS ITERATOR: iterator nelze použít na state list

        //OK3:
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


        // POKUS: NELZE APLIKOVAT LIST.GET NA STATELIST.GETSTATE - chyba v metodě getState?

//        for (int i = 0; i < (statesUnder20.size();i++)){
//            System.out.print(statesUnder20.get(i));
//            if (i != (statesUnder20.size() - 1)){
//                System.out.print(", ");
//            }
//        }


        // POKUS, který vrací: AT, AT, AT. GB, GB, GB atd.
        //for (State state : stateListUnder20.getAllStates()) {
//            for (int i = 0; i < stateListUnder20.sizeOfList(); i++) {
//                System.out.print(state.getSign() + ", ");
//                if (i == (stateListUnder20.sizeOfList() - 1)) {
//                    System.out.println(state.getSign() + ".");
//                }
//            }
//        }

//            System.out.println(state.getSign());
//            if (stateListUnder20.){ //list.size()-1 je poslední položka ze seznamu
//                System.out.println(" ,");
//            }
//        }
//
//        for (int i = 0; i < list.size(); i++){
//            //Nejaka akce
//            if (i == (list.size() - 1){
//                //Posledni zaznam
//            }
//        }


//         ŘEŠENÍ DUPLIKUJÍCÍ 2X PRVNÍ PRVEK:
//                System.out.print(stateListUnder20.getState(0).getSign());
//        for (State state : stateListUnder20.getAllStates()) {
//            System.out.print(", "+state.getSign());
//
//        }


//         PŮVODNÍ ŘEŠENÍ:
//        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: ");// na toto napsat metodu, aby se dalo přidat do prepareOutputString
// ZKOUŠKA S JOIN:
//        for (State state: stateListUnder20.getAllStates()) {
//            System.out.print(String.join(",", state.getSign()));
//        }
//        System.out.print(String.join(",", list));

//        for (State state : stateListUnder20.getAllStates()) {
//            System.out.print(state.getSign()+", ");
//        }
        System.out.println();
        System.out.println("------------------\n");

//        6. Výsledný výpis zapiš také do souboru s názvem vat-over-20.txt. Výstupní soubor ulož do stejné složky, ve které byl vstupní soubor.
//        (Výpis na obrazovku zůstává.)

//        TOTO JE JIŽ DEFINOVÁNO VÝŠE A NENÍ POTŘEBA:
//        StateList stateListOver20 = new StateList();
//        for (State state : stateList.getAllStates()) {
//            if (state.isOver20PercentVATAndWithoutSpecialVAT() == true) {
//                stateListOver20.addState(state);
//            }
//        }

        //ZAKOMENTOVANÉ ŘEŠENÍ:

        String OUTPUT_FILENAME = "vat-over-20.txt";

// POKUS O TISK JIŽ NAFORMÁTOVANÉHO
//        try {
//            stateListOver20.printStates1().exportToFile(OUTPUT_FILENAME);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            stateListOver20.exportToFile(OUTPUT_FILENAME);
//            stateListUnder20.exportToFile2(OUTPUT_FILENAME); //toto se přepíše
        } catch (IOException e) {
            e.printStackTrace();
        }

//        7. Doplň možnost, aby uživatel z klávesnice zadal výši sazby DPH/VAT, podle které se má filtrovat.
//        Vypíší se tedy státy se základní sazbou vyšší než ta, kterou uživatel zadal.
//        a) Pokud uživatel zmáčkne pouze Enter, jako výchozí hodnota se použije sazba 20 %.
//        b) Uprav název výstupního souboru tak, aby reflektoval zadanou sazbu daně.
//        Například pro zadanou sazbu 17 % se vygeneruje soubor vat-over-17.txt a pro sazbu 25 % se vygeneruje soubor vat-over-25.txt.


        // ZDE BYLO ZAMÝŠLENO ZADÁVAT FILTR:
        System.out.println("Zadej filtr: ");
        double filter = readOneIntFromInput();
        if (filter == 0) {filter = 20; }

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
        } // vytiskne seřazené státy, které splňují podmínku, ve spec. formátu

//        ok:
//        List <String> statesUnderFilter = new ArrayList<>();
//        for (State state : stateList.getAllStates()) {
//            if (state.convertFullVATtoDouble() > filter && !state.isUsingSpecialVAT){
//                statesUnderFilter.add(state.getSign());
//            }
//        }
//        ok:
        System.out.print("=================\nSazba VAT "+filter+" % a nižší nebo používají speciální sazbu: ");
        stateListUnderFilter.printLineOfSignsUnderFilter();
        // POKUS ITERATOR: iterator nelze použít na state list

//        ok:
//        Iterator it;
//        it = statesUnderFilter.iterator();
//        while(it.hasNext()){
//            System.out.print(it.next());
//            if (it.hasNext()){
//                System.out.print(", ");
//            }else{
//                System.out.print(".");
//            }
//        }

        // zde ještě vytvořit kolekci stateListUnderFilter??

        // ZAKOMENTOVANÉ ŘEŠENÍ:

        String NEXTOUTPUT_FILENAME = "vat-over-"+filter+".txt";

        try {
            stateListOverFilter.exportToFile(NEXTOUTPUT_FILENAME);
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
