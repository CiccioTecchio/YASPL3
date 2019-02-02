package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import lexer.*;
import parser.ParserCup;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fs = new FileInputStream(new File("src/main/source.yaspl"));
			LexerLex lexer = new LexerLex(fs);
			ParserCup parser = new ParserCup(lexer);
			parser.parse();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Add argoment");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
