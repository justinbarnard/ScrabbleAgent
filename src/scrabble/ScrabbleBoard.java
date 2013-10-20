package scrabble;
import scrabble.ScrabbleWord;
public class ScrabbleBoard {

	private  static StringBuilder[] board = new StringBuilder[] {new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),
		new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder(),
		new StringBuilder(),new StringBuilder(),new StringBuilder(),new StringBuilder()};


	public ScrabbleBoard(){
		//Formatted with Unicode
		board[0].append("#░░2░░░#░░░2░░#");
		board[1].append("░@░░░3░░░3░░░@░");
		board[2].append("░░@░░░2░2░░░@░░");
		board[3].append("2░░@░░░2░░░@░░2");
		board[4].append("░░░░@░░░░░@░░░░");
		board[5].append("░3░░░3░░░3░░░3░");
		board[6].append("░░2░░░2░2░░░2░░");
		board[7].append("#░░2░░░★░░░2░░#");
		board[8].append("░░2░░░2░2░░░2░░");
		board[9].append("░3░░░3░░░3░░░3░");
		board[10].append("░░░░@░░░░░@░░░░");
		board[11].append("2░░@░░░2░░░@░░2");
		board[12].append("░░@░░░2░2░░░@░░");
		board[13].append("░@░░░3░░░3░░░@░");
		board[14].append("#░░2░░░#░░░2░░#");
		this.showBoard();
		
	}
	public void clear(){
		board[0].replace(0,15,"#░░2░░░#░░░2░░#");
		board[1].replace(0,15,"░@░░░3░░░3░░░@░");
		board[2].replace(0,15,"░░@░░░2░2░░░@░░");
		board[3].replace(0,15,"2░░@░░░2░░░@░░2");
		board[4].replace(0,15,"░░░░@░░░░░@░░░░");
		board[5].replace(0,15,"░3░░░3░░░3░░░3░");
		board[6].replace(0,15,"░░2░░░2░2░░░2░░");
		board[7].replace(0,15,"#░░2░░░★░░░2░░#");
		board[8].replace(0,15,"░░2░░░2░2░░░2░░");
		board[9].replace(0,15,"░3░░░3░░░3░░░3░");
		board[10].replace(0,15,"░░░░@░░░░░@░░░░");
		board[11].replace(0,15,"2░░@░░░2░░░@░░2");
		board[12].replace(0,15,"░░@░░░2░2░░░@░░");
		board[13].replace(0,15,"░@░░░3░░░3░░░@░");
		board[14].replace(0,15,"#░░2░░░#░░░2░░#");
	}

	public void showBoard(){
		for (StringBuilder s:board) {
			System.out.println("\t"+s);		
		}
		System.out.println("=========================================");
		System.out.println("*   Blank Square: ░  Triple Letter: 3\t*\n*    Triple Word: #  Double Letter: 2\t*\n*    Double Word: @  Center Square: ★\t*\n*     Uppercase Letters:Normal Tiles\t*\n*     Lowercase Letters: Blank Tiles\t*");
		System.out.println("=========================================");
	}
	public void changeBoard(ScrabbleWord _word) {
		board[_word.getBoardYPos()].replace(_word.getBoardXPos(), _word.getBoardXPos()+_word.toString().length(), _word.toString());
		this.showBoard();	
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScrabbleBoard board = new ScrabbleBoard();
		//board.showBoard();
		ScrabbleWord test = new ScrabbleWord("TEST");
		test.setBoardPostion(4, 7);
		board.changeBoard(test);
		board.clear();
		System.out.println("Board Cleared");
		board.showBoard();

	}

}
