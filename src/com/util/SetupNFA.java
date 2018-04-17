package com.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.regularlLanguage.NFA;
import com.regularlLanguage.NFA_Transition;
import com.regularlLanguage.RegularLanguage;
import com.regularlLanguage.State;

public class SetupNFA {

	public static void setAlphabetAndName(NFA<String> nfa, String[] alphabets, String title) {
		System.out.println("Setting up alphabets");
		// nfa.addToAlphabet("epi");
		for (String aplhabet : alphabets) {
			nfa.addToAlphabet(aplhabet.replaceAll("\\s+", ""));
			//System.out.print("-"+aplhabet);
		}//System.out.println();
		nfa.setTitle(title);
	}

	public static void setStates(Map<String, State> stateMap, NFA<String> nfa, String[] states) {
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
				//System.out.println("Final-->"+current_state.getStateName());
				current_state.setFinalState(true);
				finalState.add(current_state);
			} else {
				current_state.setFinalState(false);
			}

			if (state_details.length == 4) {
				nfa.setInputState(current_state);
				;
			}
			nfa.addToStates(current_state);
			//System.out.print("::"+current_state.getStateName());
			stateMap.put(state_details[0], current_state);
			nfa.setFinalState(finalState);
		}//System.out.println();

	}

	public static void setTransitions(Map<String, State> stateMap, NFA<String> nfa, String[] transitions) {
		System.out.println("Setting up transitions");

		for (String transition : transitions) {
			// System.out.println(transition);

			String[] transitions_details = transition.split("--");
			String[] outputStateString = transitions_details[2].split("~");
			List<State> outputStates = new LinkedList<State>();
			for (String state : outputStateString) {
				// System.out.println(state);
				// Skip phi or null transitions
				if (null != state || !state.equalsIgnoreCase(RegularLanguage.NullValue)) {
					outputStates.add(stateMap.get(state));
				}
			}
			if (!RegularLanguage.NullValue.equalsIgnoreCase(transitions_details[2])) {
				
				NFA_Transition<String> nfa_transistion = new NFA_Transition<String>(
						stateMap.get(transitions_details[0]), transitions_details[1], outputStates);
				if (!transitions_details[1].equalsIgnoreCase(RegularLanguage.Epsilon)) {
					//System.out.print(transitions_details[0]+" + "+transitions_details[1]+"-->");
					for(State s:outputStates){
						//System.out.print(s.getStateName()+":");
					}
					nfa.addTransition(nfa_transistion);
					//System.out.println();
				} else {
					// Add epsilon transitions
					//System.out.print("EPS::"+transitions_details[0]+" + "+transitions_details[1]+"-->");
					for(State t:outputStates){
						//System.out.print(t.getStateName()+":");
					}
					//System.out.println();
					nfa.addEpsilonTransition(nfa_transistion);
				}
			}

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
