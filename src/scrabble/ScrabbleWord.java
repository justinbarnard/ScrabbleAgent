package scrabble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Container Class that allows Word and Score to be passed together.
 * 
 */

public class ScrabbleWord implements Comparable<ScrabbleWord> {
	final static Set<String> onePoint = new HashSet<String>(Arrays.asList("A","E","I","O","U","L","N","R","S","T"));
	final static Set<String> twoPoint = new HashSet<String>(Arrays.asList("D","G"));
	final static Set<String> threePoint = new HashSet<String>(Arrays.asList("B","C","M","P"));
	final static Set<String> fourPoint = new HashSet<String>(Arrays.asList("F","H","V","W","Y"));
	final static Set<String> fivePoint = new HashSet<String>(Arrays.asList("K"));
	final static Set<String> eightPoint = new HashSet<String>(Arrays.asList("X","J"));
	final static Set<String> tenPoint = new HashSet<String>(Arrays.asList("Q","Z"));
	final static Set<String> zeroPoint = new HashSet<String>(Arrays.asList("a","b","c","d","e","f","g","h","i","j",
			"k","l","m","n","o","p","q","r","s","t",
			"u","v","w","x","y","z"));

	private String word;
	private int score;
	private int boardX;
	private int boardY;

	public ScrabbleWord() {
		this.word = "default";
		this.score = -1;

	}
	public ScrabbleWord(String _word) {
		this.word =_word;
		this.score = 0;
	}
	public ScrabbleWord(String _word, int _score){
		this.word = _word;
		this.score = _score;
	}


	/*Main Method for testing Execution
	public static void main(String[] args) {
	}
	 */

	public int getScore() {
		return this.score;
	}
	public void setScore(int _newScore) {
		score = _newScore;
	}
	public void setBoardPostion(int _x, int _y) {
		this.boardX = _x;
		this.boardY = _y;

	}
	public int getBoardXPos(){
		return this.boardX;
	}
	public int getBoardYPos(){
		return this.boardY;
	}

	@Override
	public String toString() {
		return this.word;		
	}

	public boolean equals(ScrabbleWord _comparedWord){
		return this.getScore() == _comparedWord.getScore();

	}
	@Override //Use Descending Natural Ordering	
	public int compareTo(ScrabbleWord _comparedWord){
		if(this.equals(_comparedWord)) {
			return 0;
		}
		else if(this.getScore() > _comparedWord.getScore()){
			return -1;
		}
		else
			return 1;
	}

	public void computeScore() {


		for(int c=0; c < this.toString().length(); c++) {
			letterScore(this, c);
		}
	}
	public void bestBoardPos(){
		if(this.word.length() == 7) {
			this.score += 50;
			letterScore(this, 2);
			this.setBoardPostion(1, 7);
		}
		else if(this.word.length() == 6) {
			List<ScrabbleWord> tempWords = new ArrayList<ScrabbleWord>();
			tempWords.add(new ScrabbleWord(this.word, this.score));
			tempWords.add(new ScrabbleWord(this.word, this.score));
			tempWords.add(new ScrabbleWord(this.word, this.score));
			tempWords.add(new ScrabbleWord(this.word, this.score));
			
			letterScore(tempWords.get(0), 0);
			letterScore(tempWords.get(1), 5);
			letterScore(tempWords.get(2), 1);
			letterScore(tempWords.get(3), 4);
			tempWords.get(0).setBoardPostion(3, 7);
			tempWords.get(1).setBoardPostion(6, 7);
			tempWords.get(1).setBoardPostion(2, 7);
			tempWords.get(1).setBoardPostion(7, 7);
			Collections.sort(tempWords);
			this.score = tempWords.get(0).getScore();
			this.boardX = tempWords.get(0).boardX;
			this.boardY = tempWords.get(0).boardY;
		}
		else if(this.word.length() == 5) {
			List<ScrabbleWord> tempWords = new ArrayList<ScrabbleWord>();
			tempWords.add(new ScrabbleWord(this.word, this.score));
			tempWords.add(new ScrabbleWord(this.word, this.score));
			letterScore(tempWords.get(0), 0);
			letterScore(tempWords.get(1), 4);
			tempWords.get(0).setBoardPostion(3, 7);
			tempWords.get(1).setBoardPostion(7, 7);
			Collections.sort(tempWords);
			this.score = tempWords.get(0).getScore();
			this.boardX = tempWords.get(0).boardX;
			this.boardY = tempWords.get(0).boardY;
		}
		else if(this.word.length() == 4) {
			this.setBoardPostion(4, 7);
		}
		else
			System.out.println("Pass");
		
	}
	public static ScrabbleWord letterScore(ScrabbleWord _word, int c) {
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
		}
		return _word;
	}
}