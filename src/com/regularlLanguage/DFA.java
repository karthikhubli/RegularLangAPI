package com.regularlLanguage;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
 
public class DFA<T> implements RegularLanguage<T> {
    private Set<T> alphabet = new HashSet<T>();
    private Set<State> states = new HashSet<State>();
    private Set<DFA_Transition<T>> transitionFunction = new HashSet<DFA_Transition<T>>();
    private String title;
    private State inputState;
    private List<State> finalState = new LinkedList<>();
    
     
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
     
    public void removeFromAlphabet(T symbol){
        alphabet.remove(symbol);
    }
     
    public void addToStates(State state){
        states.add(state);
    }
 
    public void removeToState(State state){
        states.remove(state);
    }
     
    public void removeTransition(DFA_Transition<T> transition){
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

	public Set<DFA_Transition<T>> getTransitionFunction() {
		return transitionFunction;
	}

	public void setTransitionFunction(Set<DFA_Transition<T>> transitionFunction) {
		this.transitionFunction = transitionFunction;
	}
     
    public void addTransition(DFA_Transition<T> transition) throws IllegalArgumentException{
    	//System.out.println("inputState->"+transition.input.getStateName()+"||symbol-->"+transition.symbol+"||output-->"+transition.output.getStateName());
        // no 2 outputs for same inputState+symbol
        if(transitionFunction.stream()
                .noneMatch(t -> t.getInputState().equals(transition.getInputState()) &&
                                t.getSymbol().equals(transition.getSymbol()))){
            transitionFunction.add(transition);
        } else {
        	System.out.println("Error:: transition is repeated");
            throw new IllegalArgumentException();
        }
    }
     
    public Set<State> getAcceptStates(){
        return states.stream().filter(s -> s.isAcceptState())
                .collect(Collectors.toSet());
    }
 
    public State calculateFinalState(State state, Queue<T> symbol)
                throws  IllegalArgumentException {
    	//System.out.print("state->"+state.getStateName()+"||symbol-->"+symbol.peek()+"\t");
    	
        if(symbol.isEmpty() && state.isFinalState()){
        	//System.out.println("Return-"+state.getStateName());
            return state;
        }else if(symbol.peek()==null){
        	//System.out.println("Return-"+state.getStateName());
        	return state;
        }
        //System.out.println(symbol.peek());
        if(!alphabet.contains(symbol.peek())){
        	System.out.println("Error: Current alphabet not part of the language");
            throw new IllegalArgumentException();
        }
       // System.out.println("Peek pass");
        Optional<State> nextState = getNextState(state, symbol.poll());
        if(nextState.isPresent()){
        	if(nextState.get().isAcceptState()){
        	//System.out.println("NextState-->"+nextState.get().getStateName());
            return calculateFinalState(nextState.get(), symbol);
        	}else{
        		System.out.println("Entrered Trap State");
        	}
        }
       return state;
    }
    public boolean validate(LinkedList<T> input){
    	Queue<T> inputQueue = (Queue<T>) input;
    	if (this.getFinalState().contains(this.calculateFinalState(this.getInputState(), inputQueue))) {
			return true;

		} else {
			return false;
	
		}
    }
    
     
    public Optional<State> getNextState(State state, T alphabet){
    	//System.out.println(state.getStateName()+" polling for "+alphabet);
        return
                transitionFunction.stream()
                    .filter(t -> t.getInputState().equals(state) &&
                                 t.getSymbol().equals(alphabet))
                                    .map(t -> t.getOutputState()).findFirst();
    }

	

}
