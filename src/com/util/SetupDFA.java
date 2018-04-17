package com.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.regularlLanguage.DFA;
import com.regularlLanguage.DFA_Transition;
import com.regularlLanguage.State;

public class SetupDFA {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static void setAlphabetAndName(DFA<String> dfa, String[] alphabets, String title) {
		System.out.println("Setting up alphabets");
		for (String aplhabet : alphabets) {
			dfa.addToAlphabet(aplhabet);
		}
		dfa.setTitle(title);
	}

	public static void setStates(Map<String, State> stateMap, DFA<String> dfa, String[] states) {
		System.out.println("Setting up states");
		List<State> finalState = new LinkedList<>();
		for (String state : states) {
			String[] state_details = state.split("--");
			State current_state = new State();
			current_state.setStateName(state_details[0]);

			if (state_details[1].equalsIgnoreCase("yes")) {
				current_state.setAcceptState(true);
			} else {
				current_state.setAcceptState(false);
			}
			if (state_details[2].equalsIgnoreCase("yes")) {
				current_state.setFinalState(true);
				finalState.add(current_state);
			} else {
				current_state.setFinalState(false);
			}

			if (state_details.length == 4) {
				dfa.setInputState(current_state);
				;
			}
			dfa.addToStates(current_state);
			stateMap.put(state_details[0], current_state);
			dfa.setFinalState(finalState);
		}

	}

	public static void setTransitions(Map<String, State> stateMap, DFA<String> dfa, String[] transitions) {
		System.out.println("Setting up transitions");
		for (String transition : transitions) {
			String[] transitions_details = transition.split("--");
			DFA_Transition<String> dfa_transistion = new DFA_Transition<String>(stateMap.get(transitions_details[0]),
					transitions_details[1], stateMap.get(transitions_details[2]));
			dfa.addTransition(dfa_transistion);
		}
	}

	public static void setInput(LinkedList<LinkedList<String>> inputList, String[] inputs) {
		for (String input : inputs) {
			LinkedList<String> l = new LinkedList<String>();
			String[] input_chars = input.split("");
			for (String cruuentVal : input_chars) {
				l.add(cruuentVal);
			}
			inputList.add(l);
		}
	}

}
