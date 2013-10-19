package scrabble;
import java.util.*;
import scrabble.ScrabbleWord;


public class wordScore {
	

	
	
	public static void main(String[] args) {
		ScrabbleWord FinalWord;
		List<String> list = Arrays.asList("TEST","PIMP","FACE","ZEBRA","QuDoBa");
		List<ScrabbleWord> scoredWords = new ArrayList<ScrabbleWord>();
		int i = 0;
		for(String s :list) {	
			scoredWords.add(new ScrabbleWord(s));
			scoredWords.get(i).computeScore();
			i++;
		}
		System.out.println( scoredWords.toString());
		Collections.sort(scoredWords);
		scoredWords = scoredWords.subList(0, 3);
		System.out.println( scoredWords.toString());
		for(i= 0; i <=2; i++){
			scoredWords.get(i).bestBoardPos();
		}
		Collections.sort(scoredWords);
		System.out.println( scoredWords.toString());
		FinalWord = scoredWords.get(0);
		System.out.printf("%s was placed at row %d postion %d for a score of %d",FinalWord,FinalWord.getBoardYPos(),FinalWord.getBoardXPos(),FinalWord.getScore());
		
	}

}