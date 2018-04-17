package com.regularlLanguage;

import java.util.Objects;
import java.util.function.Predicate;

public class State {
	
	private boolean acceptState = false;
	private String stateName="default";
	//private State initialState =false;
	private boolean finalState = false;


	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public boolean isFinalState() {
		return finalState;
	}

	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}

	public boolean isAcceptState() {
		return acceptState;
	}

	public void setAcceptState(boolean acceptState) {
		this.acceptState = acceptState;
	}

}