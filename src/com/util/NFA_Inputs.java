package com.util;

public class NFA_Inputs {
	/*public static final String title = "NFA Sample";
	public static final String[] aphabet = { "0", "1" };

	// name--acceptstate--finalstate--initialstate
	public static final String[] states = { "s1--yes--no--yes", "s2--yes--no", "s3--yes--no", "s4--yes--no",
			"s5--yes--no", "s6--yes--yes" };

	// inputState--symbol--output
	// epi->Epsilon Transition
	// phi->Empty or null or trap state
	public static final String[] transitions = { "s1--0--phi", "s1--1--phi", "s1--epi--s2~s6", "s2--0--phi",
			"s2--1--s3", "s2--epi--phi", "s3--0--phi", "s3--1--phi", "s3--epi--s4", "s4--0--s5", "s4--1--phi",
			"s4--epi--phi", "s5--0--phi", "s5--1--phi", "s5--epi--s6~s2", "s6--0--s1", "s6--1--phi", "s6--epi--phi" };

	public static final String[] inputs = { "110011001", "00111011100", "1010101010" };*/
	
	
	
	/*public static final String title = "NFA Sample 1";
	public static final String[] aphabet = { "a", "b" };

	// name--acceptstate--finalstate--initialstate
	public static final String[] states = { "s1--yes--yes--yes", "s2--yes--no", "s3--yes--no" };

	// inputState--symbol--output
	// epi->Epsilon Transition
	// phi->Empty or null or trap state
	public static final String[] transitions = { "s1--a--phi", "s1--b--s2", "s1--epi--s1~s3",
	 "s2--a--s2~s3","s2--b--s3", "s2--epi--s2", 
	 "s3--a--s1", "s3--b--phi", "s3--epi--s3",};

	public static final String[] inputs = { "abbabab", "aaabbb", "a" ,"b","ab"};*/
	
	public static final String title="NFA Sample 2";
	public static final String[] aphabet={"a","b","c"};

	//name--acceptstate--finalstate--initialstate
	public static final String[] states={"s1--yes--no--yes","s2--yes--no","s3--yes--yes","s4--yes--no"};
	
	//inputState--symbol--output
	//epi->Epsilon Transition
	//phi->Empty or null or trap state
	public static final String[] transitions={"s1--a--s2","s1--b--phi","s1--c--s4","s1--epi--s1",
			"s2--a--phi","s2--b--s3","s2--c--phi","s2--epi--s2~s1",
			"s3--a--s2","s3--b--phi","s3--c--phi","s3--epi--s3",
			"s4--a--phi","s4--b--phi","s4--c--s3","s4--epi--s3~s4"
			};
	
	
	public static final String[] inputs={"abab","abacab","caa","ab"};
}
