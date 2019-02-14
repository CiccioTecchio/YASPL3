package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import lexer.*;
import parser.ParserCup;
import syntaxTree.Programma;
import visitor.ASTVisitor;

public class Main {

	public static void main(String[] args) {
		try {
			FileInputStream fs = new FileInputStream(new File(args[0]));
			LexerLex lexer = new LexerLex(fs);
			ParserCup parser = new ParserCup(lexer);
			Programma p = (Programma) parser.parse().value;
			ASTVisitor tpv = new ASTVisitor();
			String r = tpv.visit(p);
			FileWriter fw = new FileWriter(args[1]);
		    fw.write(r);
		    fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("You need to add arguments:\n"
					+ "1. specify the yaspl source file\n"
					+ "2. specify the output xml file");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
