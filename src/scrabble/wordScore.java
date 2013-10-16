/**
 * Deprecated, Code Folded into ScrabbleWord
 */

package scrabble;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class wordScore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		String word ="ABCDEF?";
		int wordScore = 0;
		for(int c=0; c < word.length(); c++) {
		
			if(onePoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=1;
			}
			else if(twoPoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=2;
			}
			else if(threePoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=3;
			}
			else if(fourPoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=4;
			}
			else if(fivePoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=5;
			}
			else if(eightPoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=8;	
			}
			else if(tenPoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=10;
			}
			else if(zeroPoint.contains(Character.toString(word.charAt(c)))) {
				wordScore +=0;
			}
			else {
				System.out.println("Invaild Word");
				break;
			}
			
		}
		System.out.printf("%s is worth %d points",word,wordScore);
		
		
	}

}
