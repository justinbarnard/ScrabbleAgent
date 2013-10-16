package scrabble;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Container Class that allows Word and Score to be passed together.
 * 
 */

public class ScrabbleWord implements Comparable<ScrabbleWord> {
	
	private String word;
	private int score;
	private int boardPostion;

	public ScrabbleWord() {
		word = "Unintialized Word";
		score = -99;
	}
	public ScrabbleWord(String _word, int _score) {
		word = _word;
		score = _score;

	}
	public ScrabbleWord(String _word) {
		word = _word;
	}
	
	
	//Main Method for testing Execution
	public static void main(String[] args) {
		
		ScrabbleWord test = new ScrabbleWord("TeST");
		computeScore(test);
		System.out.printf("The Word is: %s \nThe Score is %d\n",test.toString(),test.getScore());
		
		
	}

	public int getScore() {
		return this.score;
	}
	public void setScore(int _newScore) {
		score = _newScore;
	}
	@Override
	public String toString() {
		return this.word;		
	}
	@Override	
	public int compareTo(ScrabbleWord _word){
		return(this.getScore() - _word.getScore());
	}

	public static ScrabbleWord computeScore(ScrabbleWord _word) {
		final Set<String> onePoint = new HashSet<String>(Arrays.asList("A","E","I","O","U","L","N","R","S","T"));
		final Set<String> twoPoint = new HashSet<String>(Arrays.asList("D","G"));
		final Set<String> threePoint = new HashSet<String>(Arrays.asList("B","C","M","P"));
		final Set<String> fourPoint = new HashSet<String>(Arrays.asList("F","H","V","W","Y"));
		final Set<String> fivePoint = new HashSet<String>(Arrays.asList("K"));
		final Set<String> eightPoint = new HashSet<String>(Arrays.asList("X","J"));
		final Set<String> tenPoint = new HashSet<String>(Arrays.asList("Q","Z"));
		final Set<String> zeroPoint = new HashSet<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j",
																 "k","l","m","n","o","p","q","r","s","t",
																 "u","v","w","x","y","z"));
		
		for(int c=0; c < _word.toString().length(); c++) {
			
			if(onePoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+1);
			}
			else if(twoPoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+2);
			}
			else if(threePoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+3);
			}
			else if(fourPoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+4);
			}
			else if(fivePoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+5);
			}
			else if(eightPoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+8);	
			}
			else if(tenPoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore()+10);
			}
			else if(zeroPoint.contains(Character.toString(_word.toString().charAt(c)))) {
				_word.setScore(_word.getScore());
			}
			else {
				System.out.println("Invaild Word");
				_word.setScore(0);
				break;
			}
			
		}
		return _word;
	}
}