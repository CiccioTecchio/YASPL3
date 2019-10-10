package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import lexer.LexerLex;
import parser.ParserCup;
import syntaxTree.Programma;
import visitor.ASTVisitor;
import visitor.CodeVisitor;
import visitor.EnrichASTVisitor;
import visitor.GenerateCVisitor;
import visitor.SymTableVisitor;
import visitor.TypeCheckerVisitor;

public class Cli {
	
	private static final String HELP = "[options] <source.yaspl>"
			+ "options are:\n"
			+ "-ast\tgenerate ast.xml\n"
			+ "-scope\tgenerate scope.log\n"
			+ "-enrich\tgenerate enrich.xml\n";
	
	private static boolean ast = false;
	private static boolean scope = false;
	private static boolean enrich = false;
	
	public static void main(String[] args) {
		try {
			FileInputStream fs = new FileInputStream(new File(args[args.length-1]));
			LexerLex lexer = new LexerLex(fs);
			ParserCup parser = new ParserCup(lexer);
			Programma p = (Programma) parser.parse().value;
			FileWriter fw;
			ProcessBuilder pb = new ProcessBuilder();
			pb.command("bash", "-c", "mkdir yasplSource");
			checkFlag(args);
			Process process = pb.start();
			if(ast) {
				ASTVisitor tpv = new ASTVisitor();
				fw = new FileWriter("yasplSource/ast.xml");
				fw.write(tpv.visit(p));
				fw.close();
			}
			if(scope) {
				SymTableVisitor sym = new SymTableVisitor("yasplSource/scopes.log");
				sym.visit(p); 
			}else {
				SymTableVisitor sym = new SymTableVisitor();
				sym.visit(p);
			}
			/*TypeCheckerVisitor tcv = new TypeCheckerVisitor();
			tcv.visit(p);
			if(enrich) {
				EnrichASTVisitor eAST = new EnrichASTVisitor();
				fw = new FileWriter("yasplSource/enrichAst.xml");
				fw.write(eAST.visit(p));
			    fw.close();
			}
			GenerateCVisitor genC = new GenerateCVisitor();
			//CodeVisitor genC = new CodeVisitor();
		    fw = new FileWriter("yasplSource/target.c");
		    fw.write(genC.visit(p));
		    fw.close();
		    pb.redirectErrorStream(true);
		    pb.command("bash", "-c", "cd yasplSource; clang target.c -o ../yaspl.out");
		    process = pb.start();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
                
            }
            reader.close();
		    process.destroy();*/
		} catch (FileNotFoundException e) {
			System.out.println(HELP);
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.out.println(HELP);
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void checkFlag(String[]args) {
		for(String s: args) {
			switch(s) {
			case "-ast": ast = true; break;
			case "-scope": scope = true; break;
			case "-enrich": enrich = true; break;
			case "help": System.out.println(HELP); break;
			case "-help": System.out.println(HELP); break;
			}
		}
	}
	
	private static void readInput(BufferedReader bf) throws IOException {
		String msg;
		while((msg = bf.readLine())!=null) {
			System.out.println(msg);
		}
	}
}