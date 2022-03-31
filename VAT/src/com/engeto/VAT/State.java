package com.engeto.VAT;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class State {

//    public static int FILTER = Main.readOneIntFromInput();

    String sign;
    String name;
    String fullVAT;
    String reducedVAT;
    boolean isUsingSpecialVAT;

    // konstruktor, změnu čárky za tečku built-in:

    public State(String sign, String name, String fullVAT, String reducedVAT, boolean isUsingSpecialVAT) {
        this.sign = sign;
        this.name = name;
        this.setFullVAT(fullVAT);
        this.setReducedVAT(reducedVAT);
        this.isUsingSpecialVAT = isUsingSpecialVAT;
    }


    // metoda filtruje státy (dle zadání) - vybere státy s VAT 20%+ nepoužívající SpecVAT

    public boolean isOver20PercentVATAndWithoutSpecialVAT () {
        if (convertFullVATtoDouble() > 20 && !isUsingSpecialVAT){

            return true;}
        else {return false;}
    }



    // metoda filtruje státy (dle zadání) - vybere státy s VAT X%+, kde X = FILTER zadaný uživatelem

    public boolean isOverXPercentVAT (double filter) {// zde bude proměnná načtená z klávesnice, nebo spíš další metoda, ale podobná

        if (convertFullVATtoDouble() > filter){return true;}
        else {return false;}
    }


    // vrací požadované údaje o státu v požadovaném formátu:


    public String getStateSpecialFormatInfo () {
        return name+" ("+sign+"): "+fullVAT+"%";
    }

    // Sweden (SE):    25 % (12 %)

    public String getStateSpecialFormatInfo2 () {
        return name+" ("+sign+"): "+fullVAT+"% ("+reducedVAT+" %)";
    }

    // konvertory načtou data z výchozího souboru jako String a přeparsují na double, aby se se vstupem dalo pracovat jako s číslem:


    public double convertFullVATtoDouble () {

        return Double.parseDouble(fullVAT);
    }

    public double convertReducedVATtoDouble () {

        return Double.parseDouble(reducedVAT);
    }

    public static State parse(String text, String delimiter) throws VATException {
        String[] items = text.split(delimiter);

        int numberOfItems = items.length;
        if (numberOfItems != 5) throw new VATException("Nesprávný počet položek na řádku! Očekáváme 5 položek, místo "+numberOfItems+" položek na řádku: "+text);
        String sign = items[0];
        String name = items[1];
        String fullVAT = items[2];
        String reducedVAT = items[3];
        try {
            boolean isUsingSpecialVAT = Boolean.parseBoolean(items[4]);

            return new State(sign, name, fullVAT, reducedVAT, isUsingSpecialVAT);
        }
        catch (NumberFormatException ex) { throw new VATException("Špatně zadané číslo na řádku: \""+text+"\"\n\t"+ex.getLocalizedMessage()); }


    }


    // GETTERY A SETTERY:

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullVAT() {
        return fullVAT;
    }

    // setter řeší nahrazení tečky čárkou:

    public void setFullVAT(String fullVAT) {

        fullVAT = fullVAT.replace(",", ".");
        this.fullVAT = fullVAT;
    }

    public String getReducedVAT() {
        return reducedVAT;
    }

    // setter řeší nahrazení tečky čárkou:

    public void setReducedVAT(String reducedVAT) {
        reducedVAT = reducedVAT.replace(",", ".");
        this.reducedVAT = reducedVAT;
    }

    public boolean isUsingSpecialVAT() {
        return isUsingSpecialVAT;
    }

    public void setUsingSpecialVAT(boolean usingSpecialVAT) {
        isUsingSpecialVAT = usingSpecialVAT;
    }
}
