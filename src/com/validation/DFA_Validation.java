package com.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.regularlLanguage.DFA;
import com.regularlLanguage.DFA_Transition;
import com.regularlLanguage.State;
import com.util.DFA_Inputs;
import com.util.SetupDFA;

public class DFA_Validation {

	public static DFA setupDFA() {

		DFA<String> dfa = new DFA<String>();
		Map<String, State> stateMap = new HashMap<>();
		// Alphabet
		SetupDFA.setAlphabetAndName(dfa, DFA_Inputs.aphabet, DFA_Inputs.title);
		// States
		SetupDFA.setStates(stateMap, dfa, DFA_Inputs.states);
		// transitions
		SetupDFA.setTransitions(stateMap, dfa, DFA_Inputs.transitions);
		return dfa;

	}

	public static void validateString(DFA dfa, String[] inputs) {

		LinkedList<LinkedList<String>> inputList = new LinkedList<LinkedList<String>>();

		// Inputs
		SetupDFA.setInput(inputList, inputs);

		// compute
		System.out.println();
		System.out.println(dfa.getTitle());
		DFA_Validation.displayDFA(dfa);
		for (LinkedList<String> input : inputList) {
			System.out.println("-----------------------");
			System.out.println("Input:" + input);
			if (dfa.validate(input)) {
				System.out.println("\nValid String");
			} else {
				System.out.println("\nInvalid String");
			}
			System.out.println("-----------------------");
		}

	}

	public static void displayDFA(DFA dfa) {
		State s = new State();
		System.out.println("Alphabet-->" + dfa.getAlphabet());
		System.out.println("States");
		Iterator itr = dfa.getStates().iterator();
		while (itr.hasNext()) {
			s = (State) itr.next();
			System.out.print(s.getStateName() + ",");
		}
		System.out.println();
		System.out.println("Input State-->" + dfa.getInputState().getStateName());
		System.out.println("Final States");
		for (int i = 0; i < dfa.getFinalState().size(); i++) {
			s = (State) dfa.getFinalState().get(i);
			System.out.print(s.getStateName() + ",");
		}
		Set<DFA_Transition> list=dfa.getTransitionFunction();
		System.out.println("Transition");
		for(DFA_Transition tr:list){
			System.out.print(tr.getInputState().getStateName()+" + "+tr.getSymbol()+"->"+tr.getOutputState().getStateName()+"||");
		}
		System.out.println();
	}
}
