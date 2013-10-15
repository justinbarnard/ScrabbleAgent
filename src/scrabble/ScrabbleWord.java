package scrabble;

/**
 * Container Class that allows Word and Score to be passed together.
 * 
 */

public class ScrabbleWord implements Comparable<ScrabbleWord> {
	private String word;
	private int score;

	public ScrabbleWord() {
		word = "Unintialized Word";
		score = -99;
	}
	public ScrabbleWord(String _word, int _score) {
		word = _word;
		score = _score;

	}

	public int getScore() {
		return score;
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

}