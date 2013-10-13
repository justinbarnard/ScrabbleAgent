package test;
import java.io.*;

public class QuestionMark {

	public static void main(String[] args) {

		StringBuilder s = new StringBuilder();
		s.append("STAP");

		if(s.toString().contains("?")) {
			int firstIndex = s.indexOf("?");
			int lastIndex = s.lastIndexOf("?");
			if(firstIndex != lastIndex) {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					for(char j = 'a'; j <= 'z'; j++) {
						s.setCharAt(lastIndex, j);
						System.out.println(s);
					}

				}
			}
			
			else {
				for(char i = 'a'; i <= 'z'; i++)
				{
					s.setCharAt(firstIndex, i);
					System.out.println(s.toString().toUpperCase());
				}


			}
		}

		// TODO Auto-generated method stub

	}

}
