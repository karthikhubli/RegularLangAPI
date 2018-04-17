package com.validation;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.regularlLanguage.DFA;
import com.regularlLanguage.NFA;
import com.regularlLanguage.NFA_Transition;
import com.regularlLanguage.RegularLanguage;
import com.regularlLanguage.State;
import com.util.DFA_Inputs;
import com.util.NFA_Inputs;
import com.util.SetupDFA;
import com.util.SetupNFA;

public class NFA_Validation {
	
	public static NFA setupNFA(){
		
		NFA<String> nfa = new NFA<String>();
		Map<String, State> stateMap = new HashMap<>();
		//Alphabet
		SetupNFA.setAlphabetAndName(nfa,NFA_Inputs.aphabet,NFA_Inputs.title);
		//States
		SetupNFA.setStates(stateMap,nfa,NFA_Inputs.states);
		// transitions
		SetupNFA.setTransitions(stateMap, nfa,NFA_Inputs.transitions);
		return nfa;
		
	}
	
	public static void validateString(NFA nfa, String[] inputs) {
	
		//displayNFA( nfa);
		LinkedList<LinkedList<String>> inputList = new LinkedList<LinkedList<String>>();
		
		// Inputs
		SetupNFA.setInput(inputList,inputs);
		
		// compute
		System.out.println();
		System.out.println(nfa.getTitle());
		DFA dfa=RegularLanguage.getDFA(nfa);
		//Display transformed DFA
		DFA_Validation.displayDFA(dfa);
		for (LinkedList<String> input : inputList) {
			System.out.println("-----------------------");
			System.out.println("Input"+input);
			
			if (dfa.validate(input)){
				System.out.println("\nValid String");
			} else {
				System.out.println("\nInvalid String");
			}
			System.out.println("-----------------------");
		}
		
	}
	
	private static void displayNFA(NFA nfa){
		State s = null;
		NFA_Transition n = null;
		System.out.println("Alphabet-->"+nfa.getAlphabet());
		System.out.println("States");
		Iterator itr    =   nfa.getStates().iterator();
		while(itr.hasNext()){
		        s=(State) itr.next();
		        System.out.print(s.getStateName()+",");
		 }
		System.out.println();
		System.out.println("Input-->"+nfa.getInputState().getStateName());
		System.out.println("Final States");
		for(int i=0; i<nfa.getFinalState().size();i++){
			s= (State) nfa.getFinalState().get(i);
			System.out.print(s.getStateName()+",");
		}
		Iterator itr1    =   nfa.getTransitionFunction().iterator();
		System.out.println("\n transition");
		while(itr1.hasNext()){
		        n=(NFA_Transition) itr1.next();
		        List<State> se=n.getOutputState();
		        System.out.print(n.getInputState().getStateName()+" + "+n.getSymbol()+"=");
		        for(State st:se){
		        	System.out.print(st.getStateName()+",");
		        }
		        System.out.println();
		 }
		System.out.println();
	}

}
