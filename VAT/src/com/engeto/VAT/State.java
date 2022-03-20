package com.engeto.VAT;

public class State /* implements Comparable<State> */ {
    String sign;
    String name;
    String fullVAT;
    String reducedVAT;
    boolean isUsingSpecialVAT;

    public State(String sign, String name, String fullVAT, String reducedVAT, boolean isUsingSpecialVAT) {
        this.sign = sign;
        this.name = name;
        this.setFullVAT(fullVAT);
        this.setReducedVAT(reducedVAT);
        this.isUsingSpecialVAT = isUsingSpecialVAT;
    }

//    @Override
//    public int compareTo(State second) {
//        return this.fullVAT.compareTo(second.fullVAT);
//    }


    public double convertFullVATtoDouble () {

        return Double.parseDouble(fullVAT);
    }

    public double convertReducedVATtoDouble () {

        return Double.parseDouble(reducedVAT);
    }

    public boolean isOver20PercentVATAndWithoutSpecialVAT () {
        if (convertFullVATtoDouble() > 20 && !isUsingSpecialVAT){

            return true;}
        else {return false;}
    }


    public String getStateOver20PercentVATOrWithoutSpecialVAT () {

        if (isOver20PercentVATAndWithoutSpecialVAT() == true){

        return "States with VAT over 20% OR without special VAT: "+name+" ("+sign+"): "+fullVAT+"%";}
        else {return "...";}
    }

    public String getStateSpecialFormatInfo () {
        return name+" ("+sign+"): "+fullVAT+"%";
    }

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

    public void setFullVAT(String fullVAT) {

        fullVAT = fullVAT.replace(",", ".");
        this.fullVAT = fullVAT;
    }

    public String getReducedVAT() {
        return reducedVAT;
    }

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
