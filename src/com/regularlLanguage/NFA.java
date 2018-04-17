package com.regularlLanguage;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class NFA<T> implements RegularLanguage<T> {

	private Set<T> alphabet = new HashSet<T>();
	private Set<State> states = new HashSet<State>();
	private Set<NFA_Transition<T>> transitionFunction = new HashSet<NFA_Transition<T>>();
	private String title;
	private State inputState;
	private List<State> finalState = new LinkedList<>();

	private Set<NFA_Transition<T>> epTransitionFunction = new HashSet<NFA_Transition<T>>();

	public List<State> getFinalState() {
		return finalState;
	}

	public void setFinalState(List<State> finalState) {
		this.finalState = finalState;
	}

	public State getInputState() {
		return inputState;
	}

	public void setInputState(State inputState) {
		this.inputState = inputState;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addToAlphabet(T aplhabet) {
		alphabet.add(aplhabet);
	}

	public void removeFromAlphabet(T symbol) {
		alphabet.remove(symbol);
	}

	public void addToStates(State state) {
		states.add(state);
	}

	public void removeToState(State state) {
		states.remove(state);
	}

	public void removeTransition(DFA_Transition<T> transition) {
		transitionFunction.remove(transition);
	}

	public Set<T> getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(Set<T> alphabet) {
		this.alphabet = alphabet;
	}

	public Set<State> getStates() {
		return states;
	}

	public void setStates(Set<State> states) {
		this.states = states;
	}

	public Set<NFA_Transition<T>> getTransitionFunction() {
		return transitionFunction;
	}

	public void setTransitionFunction(Set<NFA_Transition<T>> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}

	public Set<NFA_Transition<T>> getEpTransitionFunction() {
		return epTransitionFunction;
	}

	public void setEpTransitionFunction(Set<NFA_Transition<T>> epTransitionFunction) {
		this.epTransitionFunction = epTransitionFunction;
	}

	public void addTransition(NFA_Transition<T> transition) throws IllegalArgumentException {
		// System.out.println("inputState->"+transition.input.getStateName()+"||symbol-->"+transition.symbol+"||output-->"+transition.output.getStateName());
		// no 2 outputs for same inputState+symbol
		if (transitionFunction.stream().noneMatch(t -> t.getInputState().equals(transition.getInputState())
				&& t.getSymbol().equals(transition.getSymbol()))) {
			transitionFunction.add(transition);
		} else {
			System.out.println(transition.getInputState() + "--" + transition.getSymbol());
			System.out.println("Error:: transition is repeated");
			throw new IllegalArgumentException();
		}
	}

	public void addEpsilonTransition(NFA_Transition<T> transition) throws IllegalArgumentException {
		// System.out.println("inputState->"+transition.input.getStateName()+"||symbol-->"+transition.symbol+"||output-->"+transition.output.getStateName());
		// no 2 outputs for same inputState+symbol
		if (epTransitionFunction.stream().noneMatch(t -> t.getInputState().equals(transition.getInputState())
				&& t.getSymbol().equals(transition.getSymbol()))) {
			epTransitionFunction.add(transition);
		} else {
			System.out.println(transition.getInputState() + "--" + transition.getSymbol());
			System.out.println("Error:: transition is repeated");
			throw new IllegalArgumentException();
		}
	}

	public boolean validate(LinkedList<T> input) {
		Queue<T> inputQueue = (Queue<T>) input;
		DFA dfa=RegularLanguage.getDFA(this);
		if (dfa.getFinalState()
				.contains(dfa.calculateFinalState(dfa.getInputState(), inputQueue))) {
			return true;
		} else {
			return false;

		}
	}


}
