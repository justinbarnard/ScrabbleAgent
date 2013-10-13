package scrabble;

import java.util.*;


public class Permute {
	

	
	public static void main(String args[]) {
		//Set Declarations
	    	Set<String> rackSet = new HashSet<String>();
	    	Set<String> permutedSet = new HashSet<String>();
	    	permuteString("", "ABCD", rackSet);	

	    /**
	     * Add Each String from rackSet to permutedSet 
	     * and substrings from 6 down to 2 characters
	     */
	 	for(int i=0; i < 6;i++) {  	
	 		for (String s : rackSet) {
	 			permutedSet.add(s.substring(i));
	 		}
	 	}
	  //Error Checking Permutation SubStrings
	 	for(String s :permutedSet) {
	 		//if(s.length() == 4)
	 		System.out.println(s);
	 	}
	 
	 	//Arrays.asList(permutedSet.toArray());
	  }

	  public static Set<String> permuteString(String beginningString, String endingString, Set<String> set) {
	    if (endingString.length() <= 1) {
	    	String pString = beginningString + endingString;
	    	set.add(pString);	    	
	    }
	    else
	      for (int i = 0; i < endingString.length(); i++) {
	        try {
	          String newString = endingString.substring(0, i) + endingString.substring(i + 1);

	          permuteString(beginningString + endingString.charAt(i), newString, set);
	        } catch (StringIndexOutOfBoundsException exception) {
	          exception.printStackTrace();
	        }
	      }
	    return set;
	  }
	}

