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


    // příprava Stringu pro export do souboru:

    public String prepareOutputString(String delimiter) {
        return getSign() + delimiter
                + getName() + delimiter
                + getFullVAT() + delimiter
                + getReducedVAT() + delimiter
                + isUsingSpecialVAT + delimiter;
    }

    // metoda filtruje státy (dle zadání) - vybere státy s VAT 20%+ nepoužívající SpecVAT

    public boolean isOver20PercentVATAndWithoutSpecialVAT () {
        if (convertFullVATtoDouble() > 20 && !isUsingSpecialVAT){

            return true;}
        else {return false;}
    }

    // metoda vrátí státy s VAT 20%+ nepoužívající SpecVAT v požadovaném formátu

    public String getStateOver20PercentVATOrWithoutSpecialVAT () {

        if (isOver20PercentVATAndWithoutSpecialVAT() == true){

            return name+" ("+sign+"): "+fullVAT+"%";}
        else {return "...";}
    }

    // metoda filtruje státy (dle zadání) - vybere státy s VAT X%+, kde X = FILTER zadaný uživatelem

    public boolean isOverXPercentVAT (double filter) {// zde bude proměnná načtená z klávesnice, nebo spíš další metoda, ale podobná

        if (convertFullVATtoDouble() > filter){return true;}
        else {return false;}
    }

    // metoda vrátí státy s VAT X%+, kde X = FILTER zadaný uživatelem, v požadovaném formátu



    // VZOR:
//    public String getStateOverXPercentVAT (int filter) {
//        if (convertFullVATtoDouble() > filter){
//
//            return name+" ("+sign+"): "+fullVAT+"%";}
//        else {return "...";}
//    }


    public String getStateOverXPercentVAT (double filter) {

        if (convertFullVATtoDouble() > filter){

            return name+" ("+sign+"): "+fullVAT+"%";}
        else {return "...";}
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


    public void loadFromFile (String filename, String delimiter) {
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
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
