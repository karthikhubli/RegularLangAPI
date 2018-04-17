package com.regularlLanguage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import com.validation.DFA_Validation;

public interface RegularLanguage<T> {

	// Constants
	static final String Epsilon = "epi";
	static final String NullValue = "phi";

	// Transformation placeholders
	static Map<String, Map<String, List<State>>> nfaStateMap = new TreeMap<>();
	static Map<String, Map<String, State>> dfaStateMap = new TreeMap<>();
	static Map<String, List<State>> epsilonTranMap = new TreeMap<>();
	static Set<State> transformedStates = new HashSet<State>();
	static Set<State> nfaStates = new HashSet<>();

	static void setstateMap(NFA nfa) {
		List<State> allState = null;
		State initialSate = nfa.getInputState();
		Set<NFA_Transition> transitions = nfa.getTransitionFunction();
		//System.out.println("nfaStateMap");
		for (NFA_Transition tr : transitions) {
			Map<String, List<State>> map = new TreeMap<>();
			allState = tr.getOutputState();
			//System.out.println("Addging " + tr.getSymbol());
			map.put(tr.getSymbol().toString().replaceAll("\\s+", ""), allState);
			nfaStateMap.put(tr.getInputState().getStateName(), map);
			nfaStates.add(tr.getInputState());
			//System.out.println(tr.getInputState().getStateName() + " + " + tr.getSymbol().toString() + "-->" + allState.size());
		}

		Set<NFA_Transition> epiTransitions = nfa.getEpTransitionFunction();
		//System.out.println("epsilonTranMap");
		for (NFA_Transition epiTra : epiTransitions) {
			epsilonTranMap.put(epiTra.getInputState().getStateName(), epiTra.getOutputState());
			//System.out.print("Epsilon state-" + epiTra.getInputState().getStateName() + "::");
			for (State s : (List<State>) epiTra.getOutputState()) {
				//System.out.print("--" + s.getStateName());
			}
			//System.out.println();
		}
	}

	static DFA getDFA(NFA nfa) {
		DFA dfa = new DFA();
		setstateMap(nfa);
		setupEpsilon(nfa);

		// Get corresponding transitions
		// Check for any new states
		// Get the transitions for new state
		// Check again and repeat
		//System.out.println("vvvvv" + dfaStateMap.keySet());
		dfa.setTitle("DFA::" + nfa.getTitle());
		dfa.setAlphabet(nfa.getAlphabet());
		dfa.setInputState(nfa.getInputState());
		dfa.setStates(transformedStates);

		Set keys = dfaStateMap.keySet();
		Iterator it = keys.iterator();
		//printSet(transformedStates);
		while (it.hasNext()) {
			String stateName = (String) it.next();
			//System.out.println("stateName--" + stateName+"||");
			State inputState = getStateByName(stateName, transformedStates);
			if (null != inputState) {

				System.out.println("stateName--" + inputState.getStateName()+"||");
				dfa.addToStates(inputState);
				Map<String, State> map = dfaStateMap.get(stateName);
				if (null != map && null != map.keySet()) {
					Iterator mpIt = map.keySet().iterator();
					while (mpIt.hasNext()) {
						String key = (String) mpIt.next();
						State outputState = map.get(key);
						if (null == outputState) {
							outputState = new State();
							outputState.setStateName(stateName);
							outputState.setAcceptState(true);
						}
						DFA_Transition<String> dfaTransition = new DFA_Transition<String>(inputState, key, outputState);
						dfa.addTransition(dfaTransition);
					}
				}
			}

		}
		setFinalState(dfa,nfa);
		//To display the transformed DFA
		//DFA_Validation.displayDFA(dfa);
		return dfa;

	}

	static void setFinalState(DFA dfa, NFA nfa) {
		List<String> nfaFinal=new LinkedList<>();
		List<State> nfaList=new LinkedList<>();
		nfaList=nfa.getFinalState();
		Set<State> dfaStateList =  dfa.getStates();
		List<State> dfaFinalList = new LinkedList<State>();
		for(State state:nfaList){
			nfaFinal.add(state.getStateName());
		}
		for(State dfaState:dfaStateList){
			String[] subStates=dfaState.getStateName().split("-");
			for(String st:subStates){
				if(null!=st &&nfaFinal.contains(st)){
					dfaFinalList.add(dfaState);
				}
			}
		}
		dfa.setFinalState(dfaFinalList);
		
	}

	static void setupEpsilon(NFA nfa) {
		// System.out.println("Title:" + nfa.getTitle());
		//System.out.println("transformed::" + transformedStates.size());
		List<State> nfaFinalState = nfa.getFinalState();
		State current = null;
		Set<State> nfaStaes = nfa.getStates();
		if (dfaStateMap.isEmpty()) {
			//System.out.println("Map is empty");
			current = nfa.getInputState();
			//transformedStates.add(current);
			transformedStates.add(computeStates(current, nfa));
		}

		
		if (dfaStateMap.containsValue(null)) {
			Iterator it = dfaStateMap.keySet().iterator();
			while (it.hasNext()) {
				String stateName = (String) it.next();
				if (null == dfaStateMap.get(stateName)) {
					current = getStateByName(stateName, transformedStates);
					//System.out.println("XXXXX   " + current.getStateName());
					if(null!=current&&null!=nfa){
					computeStates(current, nfa);
					}
				}
			}

		}
		

	}

	/*
	 * 0-s3 1-s4,s5 epi-s4,s6
	 */
	static State computeStates(State current, NFA nfa) {

		//System.out.println("transformedStates size" + transformedStates.size());
		//System.out.println("Computing states  " + current.getStateName());
		State newState = new State();
		Set<String> nfaSymbols = nfa.getAlphabet();
		Map<String, State> currDFAMap = new TreeMap<>();
		// symbol->outputState
		//System.out.println(current.getStateName());
		String[] subState = current.getStateName().split("-");
		for (String subSt : subState) {
			if (null != subSt && !subSt.equalsIgnoreCase("")) {
				
				State currSubState = getStateByName(subSt, nfaStates);
				Map<String, List<State>> currNFAMap = nfaStateMap.get(currSubState.getStateName());
				// Iterate over every symbol
				if (null != currNFAMap && currNFAMap.size() != 0) {
					for (String symbol : nfaSymbols) {
						if (!symbol.equalsIgnoreCase(NullValue) || null != symbol) {
							//System.out.println("-------------------Symbol-" + symbol + "  NFA  Map size-"
							//		+ currNFAMap.size() + "----------------");
							StringBuffer stateName = new StringBuffer();
							//System.out.println("ALL THE Symbols " + currNFAMap.keySet());
							if (currNFAMap.containsKey(symbol)) {
								//System.out.println("No prob here for symbol-" + symbol);
								List<State> statesForSymbol = currNFAMap.get(symbol);

								if (null != statesForSymbol && statesForSymbol.size() != 0) {
									String name = getEpsilonStates(statesForSymbol, symbol);
									stateName.append(name);
									//System.out.println("state Nmae-" + stateName.toString());
								}

							}
							//System.out.println("New-->" + stateName.toString());
							if (null != stateName && stateName.length() != 0
									&& null == getStateByName(stateName.toString(), transformedStates)) {

								newState.setStateName(stateName.toString());
								newState.setFinalState(false);
								newState.setAcceptState(true);;
								transformedStates.add(newState);
								transformedStates.add(current);
								currDFAMap.put(symbol, newState);
								if (!dfaStateMap.containsKey(newState.getStateName())) {
									//System.out.println("Adding new state to map-->" + newState.getStateName());
									dfaStateMap.put(newState.getStateName(), null);
								}
								
								if (!dfaStateMap.containsKey(current.getStateName())) {
									//System.out.println("Adding current state to map--" + current.getStateName()+":Size"+currDFAMap.keySet().size());
									dfaStateMap.put(current.getStateName(), currDFAMap);
								}

							}

						}

					}
				}
			}
		}
		return newState;
	}

	static State getStateByName(String subSt, Set<State> states) {
		State state = null;
		for (State st : states) {
			if (subSt.equalsIgnoreCase(st.getStateName())) {
				state = st;
			}
		}

		return state;
	}

	static String getEpsilonStates(List<State> epiStates, String symbol) {
		// System.out.println("Getting epsilon states");
		StringBuffer sb = new StringBuffer();
		for (State s : epiStates) {
			// Getting epsilon states
			// System.out.println("Getting epsilon states
			// for-"+s.getStateName());
			List<State> epsilonStateList = epsilonTranMap.get(s.getStateName());
			for (State epState : epsilonStateList) {
				// System.out.println("Appending the
				// state-"+epState.getStateName());
				sb.append(epState.getStateName() + "-");
			}

		}
		return sb.toString();
	}

	default boolean validateRegEx(String gegEx, LinkedList<T> input) {
		return false;
	}

	static void getUnion() {

	}
	static void printSet(Set<State> set){
		System.out.println("States::::");
		for(State s:set){
			System.out.print(s.getStateName()+"||");
		}System.out.println();
	}
	public boolean validate(LinkedList<T> input);

}
