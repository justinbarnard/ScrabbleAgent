package test;
import java.io.*;
import java.util.*;


public class Test {
	//Change path to SOWPODS File here
	private static String pathToSOWPODS = "/Users/Justin/Documents/workspace/HashTest/src/test/SOWPODS_complete.txt";

	private static BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) {
		Set<String> rackSet = new HashSet<String>();
		Set<String> permutedSet = new HashSet<String>();
		Trie trie = new Trie(getDictionary());
		try {
			while (true) {
				System.out.print("Word to lookup: ");
				String rack = consoleIn.readLine().trim();
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
					wordSearch(s, trie);
				}
				permutedSet.clear();
			}
		} catch (IOException e) {
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

	public static void wordSearch(String _Word, Trie _Trie) {
		StringBuilder s = new StringBuilder();
		s.setLength(0);
		s.append(_Word);
		if(s.toString().contains("?")) {
			int firstIndex = s.indexOf("?");
			int lastIndex = s.lastIndexOf("?");
			if(firstIndex != lastIndex) {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					for(char j = 'a'; j <= 'z'; j++) {
						s.setCharAt(lastIndex, j);
						if (_Trie.containsWord(s.toString()))
							System.out.println(s + " found");
					}

				}
			}

			else {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					if (_Trie.containsWord(s.toString()))
						System.out.println(s + " found");
				}


			}
		}
		else {
			if (_Trie.containsWord(_Word))
				System.out.println(_Word + " found");
			else {
				//System.out.println("Word Not Found");
			}
		}

	}
}