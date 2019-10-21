package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import lexer.*;
import parser.ParserCup;
import syntaxTree.Programma;
import visitor.ASTVisitor;
import visitor.CodeVisitor;
import visitor.EnrichASTVisitor;
import visitor.ScopeCheckerVisitor;
import visitor.TypeCheckerVisitor;

public class Main {

	public static void main(String[] args) {
		try {
			FileInputStream fs = new FileInputStream(new File(args[0]));
			LexerLex lexer = new LexerLex(fs);
			ParserCup parser = new ParserCup(lexer);
			Programma p = (Programma) parser.parse().value;
			ASTVisitor tpv = new ASTVisitor();
			FileWriter fw = new FileWriter(args[1]);
			fw.write(tpv.visit(p));
			fw.close();
			ScopeCheckerVisitor sym = new ScopeCheckerVisitor("src/tree/scopes.log");
			sym.visit(p);
			TypeCheckerVisitor tcv = new TypeCheckerVisitor();
			tcv.visit(p);
			EnrichASTVisitor eAST = new EnrichASTVisitor();
			fw = new FileWriter(args[2]);
			fw.write(eAST.visit(p));
		    fw.close();
		    CodeVisitor genC = new CodeVisitor();
		    fw = new FileWriter(args[3]);
		    fw.write(genC.visit(p));
		    fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("You need to add arguments:\n"
					+ "1. specify the yaspl source file\n"
					+ "2. specify the file where you want to print the ast in XML\n"
					+ "3. specify the file where you want to print the enriched ast in XML\n"
					+ "4. specify the C output file\"\n"
					);
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}