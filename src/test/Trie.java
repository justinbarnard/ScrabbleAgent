package test;
import java.util.*;

public class Trie {

	private Node root = new Node("");

	public Trie() {}

	public Trie(List<String> _Dictionary) {
		int i = 0;
		System.out.print("Loading ");
		for (Iterator<String> it = _Dictionary.iterator(); it.hasNext(); i++) {
			String s = it.next();
			addWord(s);
			it.remove();
			if(i == 5000){
				System.out.print(".");
				System.gc();
				i = 0;
			}

		}
		System.out.println("\nSOWPODS Loaded");
	}

	public void addWord(String _Word) {
		char _Chars[] = _Word.toCharArray();
		Node currentNode = root;

		for (int i = 0; i < _Chars.length; i++) {
			if (!currentNode.containsChildValue(_Chars[i])) {
				currentNode.addChild(_Chars[i], new Node(currentNode.getValue() + _Chars[i]));
			}

			currentNode = currentNode.getChild(_Chars[i]);
		}

		currentNode.setIsWord(true);
	}

	public boolean containsWord(String _Word) {
		return contains(_Word.toUpperCase());
	}

	public Node getWord(String _String) {
		Node node = getNode(_String);
		return node != null && node.isWord() ? node : null;
	}

	private boolean contains(String _String) {
		Node node = getNode(_String);
		return (node != null && node.isWord());
	}

	private Node getNode(String _String) {
		Node currentNode = root;
		char _Chars[] = _String.toCharArray();
		for (int i = 0; i < _Chars.length && currentNode != null; i++) {
			currentNode = currentNode.getChild(_Chars[i]);

			if (currentNode == null) {
				return null;
			}
		}

		return currentNode;
	}
}


class Node {

	private final String value;
	private Map<Character, Node> children = new HashMap<Character, Node>();
	private boolean isValidWord;

	public Node(String _Value) {
		value = _Value;
	}

	public boolean addChild(char c, Node _Child) {
		children.put(c, _Child);
		return true;
	}

	public boolean containsChildValue(char c) {
		return children.containsKey(c);
	}

	public String getValue() {
		return value.toString();
	}

	public Node getChild(char c) {
		return children.get(c);
	}

	public boolean isWord() {
		return isValidWord;
	}

	public void setIsWord(boolean _isWord) {
		isValidWord = _isWord;

	}

	public String toString() {
		return value;
	}

}

