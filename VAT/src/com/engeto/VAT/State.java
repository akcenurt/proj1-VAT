package com.engeto.VAT;



public class State {

    public static final int FILTER = Main.readOneIntFromInput();

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

    public boolean isOverXPercentVAT () {// zde bude proměnná načtená z klávesnice, nebo spíš další metoda, ale podobná

        if (convertFullVATtoDouble() > FILTER){return true;}
        else {return false;}
    }

    // metoda vrátí státy s VAT X%+, kde X = FILTER zadaný uživatelem, v požadovaném formátu


    public String getStateOverXPercentVAT () {

        if (isOverXPercentVAT() == true){

            return name+" ("+sign+"): "+fullVAT+"%";}
        else {return "...";}
    }

    // vrací požadované údaje o státu v požadovaném formátu:


    public String getStateSpecialFormatInfo () {
        return name+" ("+sign+"): "+fullVAT+"%";
    }

    // konvertory načtou data z výchozího souboru jako String a přeparsují na double, aby se se vstupem dalo pracovat jako s číslem:


    public double convertFullVATtoDouble () {

        return Double.parseDouble(fullVAT);
    }

    public double convertReducedVATtoDouble () {

        return Double.parseDouble(reducedVAT);
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
