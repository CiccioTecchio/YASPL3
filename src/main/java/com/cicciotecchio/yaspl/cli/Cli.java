package com.cicciotecchio.yaspl.cli;

import com.cicciotecchio.yaspl.lexer.LexerLex;
import com.cicciotecchio.yaspl.parser.ParserCup;
import com.cicciotecchio.yaspl.syntaxTree.Programma;
import com.cicciotecchio.yaspl.visitor.*;
import java_cup.runtime.ComplexSymbolFactory;

import java.io.*;

public class Cli {
	
	private static final String HELPEMSDK = "before generate js code you need to install emscriten and follow this tutorial:\n"
			  + "https://emscripten.org/docs/getting_started/downloads.html";
	
	private static final String HELP = "[options] [path to emsdk] <source.yaspl>"
			+ "options are:\n"
			+ "-ast\tgenerate ast.xml\n"
			+ "-scope\tgenerate scope.log\n"
			+ "-enrich\tgenerate enrich.xml\n"
			+ "-js\tgenerate the JavaScript equivalent of souce.yaspl\n"+HELPEMSDK
			+ "path to emsdk need to be passed with the path based of the home directory for OSX is ~ example\n"
			+ "~/path to/emsdk";
	
	
	
	private static boolean ast = false;
	private static boolean scope = false;
	private static boolean enrich = false;
	private static boolean js = false;
	
	public static void main(String[] args) {
		try {
			FileInputStream fs = new FileInputStream(new File(args[args.length-1]));
			ComplexSymbolFactory csf = new ComplexSymbolFactory();
			LexerLex lexer = new LexerLex(csf, fs);
			ParserCup parser = new ParserCup(lexer, csf);
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
				ScopeCheckerVisitor sym = new ScopeCheckerVisitor("yasplSource/scopes.log");
				sym.visit(p); 
			}else {
				ScopeCheckerVisitor sym = new ScopeCheckerVisitor();
				sym.visit(p);
			}
			TypeCheckerVisitor tcv = new TypeCheckerVisitor();
			tcv.visit(p);
			
			if(enrich) {
				EnrichASTVisitor eAST = new EnrichASTVisitor();
				fw = new FileWriter("yasplSource/enrichAst.xml");
				fw.write(eAST.visit(p));
			    fw.close();
			}
			//GenerateCVisitor genC = new GenerateCVisitor();
			CodeVisitor genC = new CodeVisitor();
		    fw = new FileWriter("yasplSource/target.c");
		    fw.write(genC.visit(p));
		    fw.close();
		    pb.redirectErrorStream(true);
		    String pathEmsdk = args[args.length-2];

		    if(js){
		    pb.command("bash", "-c", "source "+pathEmsdk+"/emsdk_env.sh;"
								   + "cd yasplSource;"
		    					   + "clang target.c -o ../a.out; gindent target.c;"
								   + "emcc target.c -o ../yaspl.out.js");
		    }else{
		    pb.command("bash", "-c", "cd yasplSource; clang target.c -o ../a.out; gindent target.c");
		    }

			process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

            reader.close();
		    process.destroy();
		} catch (FileNotFoundException e) {
			System.err.println(HELP);
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			System.err.println(HELP);
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void checkFlag(String[]args) {
		for(String s: args) {
			switch(s) {
			case "-ast": ast = true; break;
			case "-scope": scope = true; break;
			case "-enrich": enrich = true; break;
			case "-js": js = true; break;
			case "help": System.out.println(HELP); break;
			case "-help": System.out.println(HELP); break;
			}
		}
	}
	
}