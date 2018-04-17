package com.regularlLanguage;

public class DFA_Transition<T> {
    State input;
    T symbol;
    State output;
     
    public DFA_Transition(State input, T symbol, State output){
        this.input = input;
        this.symbol = symbol;
        this.output = output;
    }
    public State getInputState() {
        return input;
    }
 
    public T getSymbol() {
        return symbol;
    }
 
    public State getOutputState() {
        return output;
    }
}
