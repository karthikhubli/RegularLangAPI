package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.regularlLanguage.DFA;
import com.regularlLanguage.NFA;
import com.regularlLanguage.State;
import com.validation.DFA_Validation;
import com.validation.NFA_Validation;

public class TestAutomata {

	public static void main(String[] args) {
		//DFA Validation
		//System.out.println("==================DFA VALIDATION==============");
		DFA dfa =DFA_Validation.setupDFA();
		
	DFA_Validation.validateString(dfa, DFA_Inputs.inputs);
		
		
		//NFA Validation
		System.out.println("==================NFA VALIDATION==============");
		NFA nfa =NFA_Validation.setupNFA();
		NFA_Validation.validateString(nfa, NFA_Inputs.inputs);

//System.out.println("--test-me-".replaceAll("-+$", ""));
	}

	

}
