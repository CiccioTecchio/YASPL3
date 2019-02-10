package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java_cup.runtime.Symbol;
import lexer.*;
import parser.ParserCup;
import syntaxTree.comp.Node;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fs = new FileInputStream(new File("src/main/source.yaspl"));
			LexerLex lexer = new LexerLex(fs);
		   	/*for(int tokenId= lexer.next_token().sym; tokenId != LexerSym.EOF; tokenId  = lexer.next_token().sym) {
		   		System.out.println("token returned is "+ LexerSym.terminalNames[tokenId] + "\n");
		   	}*/
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
