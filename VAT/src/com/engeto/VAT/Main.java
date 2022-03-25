package com.engeto.VAT;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Main {


// načte input:
    public static int readOneIntFromInput() {
        int filter = Reading.safeReadInt();
        return filter;
    }


    public static final String INPUT_FILENAME = "vat-eu.txt";
    public static final String DELIMITER = "\t";




    public static void main(String[] args) {

//        1. Načti ze souboru všechna data do vhodné datové struktury (vytvoř třídu pro uložení dat):

	    StateList stateList = new StateList();

        stateList.loadFromFile(INPUT_FILENAME, "\t");

//        2. Vypiš seznam všech států a u každého uveď základní sazbu daně z přidané hodnoty ve formátu podle vzoru: Název země (zkratka): základní sazba %
        System.out.println("Stát (zkratka): základní sazba DPH: \n");
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateSpecialFormatInfo());
        }
        System.out.println("---------------------\n");
//        3. Vypište ve stejném formátu pouze státy, které mají základní sazbu daně z přidané hodnoty vyšší než 20 % a přitom nepoužívají speciální sazbu daně.
        // Stát nesplňující uvedenou podmínku nahradí "..."
        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně:\n");
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateOver20PercentVATOrWithoutSpecialVAT());
        }
        System.out.println("----------------------\n");

//        4. Výpis z bodu 3. seřaď podle výše základní sazby DPH/VAT sestupně (nejprve státy s nejvyšší sazbou).

        Collections.sort(stateList.states, new FullVATComparator());
        Collections.reverse(stateList.states);
        System.out.println("Státy se základní sazbou DPH vyšší než 20% a zároveň nepoužívající speciální sazbu daně seřazeny sestupně:\n");
        for (State state : stateList.getAllStates()) {
            System.out.println(state.getStateOver20PercentVATOrWithoutSpecialVAT());
        }

//        5. Pod výpis z bodu 3. doplň řádek s rovnítky pro oddělení a poté seznam zkratek států, které ve výpisu nefigurují.
//        Opět dodrž formát podle vzoru (místo tří teček budou další státy):
        // Seznam států obsahuje čárku za posledním státem

        System.out.print("=================\nSazba VAT 20 % a nižší nebo používají speciální sazbu: "); // na toto napsat metodu, aby se dalo přidat do prepareOutputString
        for (State state : stateList.getAllStates()) {
            if (state.isOver20PercentVATAndWithoutSpecialVAT() == false){
                System.out.print(state.getSign()+ ", ");
            }
        }

//        6. Výsledný výpis zapiš také do souboru s názvem vat-over-20.txt. Výstupní soubor ulož do stejné složky, ve které byl vstupní soubor.
//        (Výpis na obrazovku zůstává.)

        StateList stateListOver20 = new StateList();
        for (State state : stateList.getAllStates()) {
            if (state.isOver20PercentVATAndWithoutSpecialVAT() == true) {
                stateListOver20.addState(state);
            }
        }

        String OUTPUT_FILENAME = "vat-over-20.txt";

        try {
            stateListOver20.exportToFile(OUTPUT_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        7. Doplň možnost, aby uživatel z klávesnice zadal výši sazby DPH/VAT, podle které se má filtrovat.
//        Vypíší se tedy státy se základní sazbou vyšší než ta, kterou uživatel zadal.
//        a) Pokud uživatel zmáčkne pouze Enter, jako výchozí hodnota se použij jako výchozí sazbu 20 %.
//        b) Uprav název výstupního souboru tak, aby reflektoval zadanou sazbu daně.
//        Například pro zadanou sazbu 17 % se vygeneruje soubor vat-over-17.txt a pro sazbu 25 % se vygeneruje soubor vat-over-25.txt.

        

        // TOTO NEFUNGUJE DOBŘe, musím pořád zadávat hodnoty, nestačí zadat jednou:

//        for (State state : stateList.getAllStates()) {
//            int filter = readOneIntFromInput();
//            if (filter == 0) {filter = 20;}
//            System.out.println(state.getStateOverXPercentVAT());
//        }



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
