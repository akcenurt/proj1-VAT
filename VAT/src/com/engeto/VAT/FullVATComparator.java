package com.engeto.VAT;

import java.util.Comparator;

public class FullVATComparator implements Comparator<State> {


        @Override
        public int compare(State first, State second) {

            return Double.compare(first.convertFullVATtoDouble(), second.convertFullVATtoDouble());
    }
}
