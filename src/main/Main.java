package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import lexer.*;
import parser.ParserCup;
import syntaxTree.Programma;
import visitor.ASTVisitor;
import visitor.SymTableVisitor;
import visitor.TypeCheckerVisitor;

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
			SymTableVisitor sym = new SymTableVisitor("src/main/scopes.log");
			sym.visit(p);
			TypeCheckerVisitor tcv = new TypeCheckerVisitor();
			tcv.visit(p);
			fw = new FileWriter(args[2]);
			fw.write(tcv.getAST());
		    fw.close();
		    
		} catch (FileNotFoundException e) {
			System.out.println("You need to add arguments:\n"
					+ "1. specify the yaspl source file\n"
					+ "2. specify the file where you want to print the ast in XML"
					+ "3. specify the file where you want to print the enriched ast in XML\"");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
