package com.regularlLanguage;

import java.util.LinkedList;
import java.util.List;

public class NFA_Transition<T> {
	State inputState;
    T symbol;
    List<State> output = new LinkedList<>();
    
    public NFA_Transition(State input,T symbol,List<State> output) {
		this.inputState=input;
		this.symbol=symbol;
		this.output=output;
	}
	public State getInputState() {
		return inputState;
	}
	public void setInputState(State input) {
		this.inputState = input;
	}
	public T getSymbol() {
		return symbol;
	}
	public void setSymbol(T symbol) {
		this.symbol = symbol;
	}
	public List<State> getOutputState() {
		return output;
	}
	public void setOutputState(List<State> output) {
		this.output = output;
	}
}
