package com.util;

import java.util.ArrayList;
import java.util.List;

import com.regularlLanguage.DFA_Transition;
import com.regularlLanguage.State;

public class DFA_Inputs {
	
	/*START:: DFA to check if there are even number of 0s in the string*/
	
	/*public static final String title="DFA to verify even number of 0";
	public static final String[] aphabet={"0","1"};
	//name--acceptstate--finalstate
	public static final String[] states={"s1--yes--yes--yes","s2--yes--no"};
	//inputState--symbol--output
	public static final String[] transitions={"s1--0--s2","s2--0--s1","s1--1--s1","s2--1--s2"};
	
	public static final String[] inputs={"110011001","00111011100",""};*/
	
	
	/*END:: DFA to check if there are even number of 0s in the string*/
	
	
	
	
/*	START:: DFA to accepts all strings with consecutive 1s*/
 
	public static final String title="DFA accepts all strings with consecutive 1s";
	public static final String[] aphabet={"0","1"};

	//name--acceptstate--finalstate--initialstate
	public static final String[] states={"a--yes--no--yes","b--yes--no","c--yes--yes"};
	
	//inputState--symbol--output
	public static final String[] transitions={"a--0--a","a--1--b","b--0--a","b--1--c","c--0--c","c--1--c"};
	
	
	public static final String[] inputs={"110011001","00111011100","1010101010","000100001001100001"};
	
	
	/*END:: DFA to accepts all strings with consecutive 1s*/
	
	
	
	/*public static final String title="DFA accepts all strings with consecutive 1s";
	public static final String[] aphabet={"a","b"};

	//name--acceptstate--finalstate--initialstate
	public static final String[] states={"s1--yes--no--yes","s2--yes--no","s2--yes--yes"};
	
	//inputState--symbol--output
	public static final String[] transitions={"s1--a--s2", "s1--b--s1",
			"s2--a--s3","s2--b--s2",
			"s3--a--s3","s3--b--s3"};
	
	
	public static final String[] inputs={"a","ab","abaa","bb"};*/
	

}
