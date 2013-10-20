package scrabble;
import java.io.*;
import java.util.*;

import scrabble.ScrabbleWord;
import scrabble.ScrabbleBoard;


public class ScrabbleAgent {

	private static long startTime;
	private static long stopTime;
	//Change path to SOWPODS and Rack Files here ░
	private static String pathToSOWPODS = "/Users/Justin/Documents/workspace/ScrabbleAgent/src/scrabble/SOWPODS_complete.txt";
	private static String pathToRACK ="/Users/Justin/Documents/workspace/ScrabbleAgent/src/scrabble/sample_rack_file.txt";

	private static BufferedReader txtReader;
	private static BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		Set<String> rackSet = new HashSet<String>();
		Set<String> permutedSet = new HashSet<String>();
		String input;
		String rack;
		List<String> list = new ArrayList<String>();
		ScrabbleWord FinalWord;
		Trie trie = new Trie(getDictionary());
		ScrabbleBoard board = new ScrabbleBoard();
		boolean done = false;
		try {
			txtReader = new BufferedReader(new FileReader(pathToRACK));
			while ((rack = txtReader.readLine()) != null && done == false) {
				do {
					System.out.print("Process Next Rack?<Y/N> ");
					input = consoleIn.readLine().trim().toUpperCase();
					
				} while(!input.equals("N") && !input.equals("Y"));
				if(input.equals("N")) {
					done = true;
				}
				else if (input.equals("Y")){
					startTime = System.currentTimeMillis();
					permuteString("", rack, rackSet);
					for(int i=0; i < rack.length()-1;i++) {  	
						for (String s : rackSet) {
							permutedSet.add(s.substring(i));
						}
					}
					rackSet.clear();
					for (String s : permutedSet) {
						if (rack.isEmpty())
							break;
						list.addAll(wordSearch(s, trie));
					}

					//New addition

					List<ScrabbleWord> scoredWords = new ArrayList<ScrabbleWord>();
					int i = 0;
					for(String s :list) {	
						scoredWords.add(new ScrabbleWord(s));
						scoredWords.get(i).computeScore();
						i++;
					}
					list.clear();

					permutedSet.clear();
					//System.out.println( scoredWords.toString());
					Collections.sort(scoredWords);
					//System.out.println(scoredWords.size());
					if (scoredWords.size() >= 3) {
						scoredWords = scoredWords.subList(0, 3);
						//System.out.println( scoredWords.toString());
						for(i= 0; i <=2; i++){
							scoredWords.get(i).bestBoardPos();
						}
						Collections.sort(scoredWords);
						//System.out.println( scoredWords.toString());
						FinalWord = scoredWords.get(0);
						if(FinalWord.toString().length() >= 4) {
							stopTime = System.currentTimeMillis();
							FinalWord.setScore(FinalWord.getScore()*3);
							board.changeBoard(FinalWord);
							System.out.println("Placed " + FinalWord + " For score of " + FinalWord.getScore());
							System.out.printf("Elapsed Time: %d ms\n",stopTime-startTime);
							scoredWords.clear();
						}
						else
						{
							stopTime = System.currentTimeMillis();
							System.out.printf("Computer Passes\nElapsed Time: %d ms\n",stopTime-startTime);

						}

					}
					else if (scoredWords.isEmpty()) {
						System.out.println("Computer Exchanges:" + exchangeTiles(rack).toString());
						stopTime = System.currentTimeMillis();
						System.out.printf("Elapsed Time: %d ms\n",stopTime-startTime);

					}
					else {
						scoredWords.get(0).bestBoardPos();
						board.changeBoard(scoredWords.get(0));
						scoredWords.clear();


					}
					//
					//permutedSet.clear();
				}
	
				board.clear();
			}
			txtReader.close();
			consoleIn.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}


	}


	public static List<String> getDictionary() {
		List<String> dictionary = new ArrayList<String>();
		BufferedReader input = null;

		try {

			String word;

			input = new BufferedReader(new FileReader(pathToSOWPODS));

			while ((word = input.readLine()) != null) {
				if(word.length() > 1)
					dictionary.add(word);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (input != null)input.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return dictionary;
	}

	public static Set<String> permuteString(String _beginningString, String _endingString, Set<String> _set) {
		if (_endingString.length() <= 1) {
			String pString = _beginningString + _endingString;
			_set.add(pString);	    	
		}
		else
			for (int i = 0; i < _endingString.length(); i++) {
				try {
					String newString = _endingString.substring(0, i) + _endingString.substring(i + 1);

					permuteString(_beginningString + _endingString.charAt(i), newString, _set);
				} catch (StringIndexOutOfBoundsException exception) {
					exception.printStackTrace();
				}
			}
		return _set;
	}

	public static List<String> wordSearch(String _Word, Trie _Trie) {
		List<String> list = new ArrayList<String>();
		StringBuilder s = new StringBuilder();
		s.setLength(0);
		s.append(_Word);
		if(s.toString().contains("_")) {
			int firstIndex = s.indexOf("_");
			int lastIndex = s.lastIndexOf("_");
			if(firstIndex != lastIndex) {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					for(char j = 'a'; j <= 'z'; j++) {
						s.setCharAt(lastIndex, j);
						if (_Trie.containsWord(s.toString()))
							list.add(s.toString());

					}

				}
			}

			else {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					if (_Trie.containsWord(s.toString()))
						list.add(s.toString());
				}


			}
		}
		else {
			if (_Trie.containsWord(_Word))
				list.add(_Word);
		}
		return list;
	}
	public static List<ScrabbleWord> exchangeTiles(String _rack){
		List<ScrabbleWord> letters = new ArrayList<ScrabbleWord>();
		for(char c: _rack.toCharArray()) {
			letters.add(new ScrabbleWord(Character.toString(c)));						
		}
		for(ScrabbleWord c: letters){
			c.computeScore();
		}
		Collections.sort(letters);
		return letters.subList(2, 5);
	}
}