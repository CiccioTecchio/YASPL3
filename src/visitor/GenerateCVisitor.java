package visitor;

import syntaxTree.Args;
import syntaxTree.Body;
import syntaxTree.CompStat;
import syntaxTree.Decls;
import syntaxTree.Expr;
import syntaxTree.ParDecls;
import syntaxTree.Programma;
import syntaxTree.Stat;
import syntaxTree.Statements;
import syntaxTree.VarDecls;
import syntaxTree.VarDeclsInit;
import syntaxTree.VarInitValue;
import syntaxTree.Vars;
import syntaxTree.arithOp.AddOp;
import syntaxTree.arithOp.DivOp;
import syntaxTree.arithOp.MultOp;
import syntaxTree.arithOp.SubOp;
import syntaxTree.arithOp.UminusOp;
import syntaxTree.comp.Leaf;
import syntaxTree.declsOp.DefDeclNoPar;
import syntaxTree.declsOp.DefDeclPar;
import syntaxTree.declsOp.VarDecl;
import syntaxTree.leaf.BoolConst;
import syntaxTree.leaf.CharConst;
import syntaxTree.leaf.DoubleConst;
import syntaxTree.leaf.IdConst;
import syntaxTree.leaf.IntConst;
import syntaxTree.leaf.ParTypeLeaf;
import syntaxTree.leaf.StringConst;
import syntaxTree.leaf.TypeLeaf;
import syntaxTree.logicOp.AndOp;
import syntaxTree.logicOp.NotOp;
import syntaxTree.logicOp.OrOp;
import syntaxTree.relOp.EqOp;
import syntaxTree.relOp.GeOp;
import syntaxTree.relOp.GtOp;
import syntaxTree.relOp.LeOp;
import syntaxTree.relOp.LtOp;
import syntaxTree.statOp.AssignOp;
import syntaxTree.statOp.CallOp;
import syntaxTree.statOp.IfThenElseOp;
import syntaxTree.statOp.IfThenOp;
import syntaxTree.statOp.ReadOp;
import syntaxTree.statOp.WhileOp;
import syntaxTree.statOp.WriteOp;
import syntaxTree.utils.ParDeclSon;
import syntaxTree.varDeclInitOp.VarInit;
import syntaxTree.varDeclInitOp.VarNotInit;
import syntaxTree.wrapper.DeclsWrapper;
import syntaxTree.wrapper.VarDeclsInitWrapper;

public class GenerateCVisitor implements Visitor<String> {

	public GenerateCVisitor() {
	}
	
	@Override
	public String visit(Args n) throws RuntimeException {
		String tr="";
		for(Expr e: n.getChildList()) {
			tr+=e.accept(this)+",";
		}
		return tr = tr.substring(0, tr.length()-1);
		
	}

	@Override
	public String visit(Body n) throws RuntimeException {
		String tr = n.getVd().accept(this)+"";
		tr += n.getS().accept(this);
		return tr;
	}

	@Override
	public String visit(CompStat n) throws RuntimeException {
		return n.getS().accept(this)+"";
	}

	@Override
	public String visit(Decls n) throws RuntimeException {
		String tr = "";
		for(DeclsWrapper dw: n.getChildList()) {
			tr+=dw.accept(this);
		}
		return tr+="\n";
	}

	@Override
	public String visit(DefDeclNoPar n) throws RuntimeException {
		String tr = "void";
		tr+= "void "+n.getId().accept(this)+"(){\n";
		tr+= n.getB().accept(this);
		return tr+="}\n";
	}

	@Override
	public String visit(DefDeclPar n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(ParDecls n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(Programma n) throws RuntimeException {
		String tr = "#include <stdio.h>\n"
				+ "#include <stdlib.h>\n"
				+ "#include<string.h>\n"
				+ "#include <unistd.h>\n"
				+ "\n"
				+ "typedef int bool;\n"
				+ "#define false 0\n"
				+ "#define true 1\n"
				+ "\n"
				+ "typedef char string[256];\n"
				+ "\n";
		//visita Decls
		tr+= n.getD().accept(this);
		tr+="int main(void){\n";
		//visita Statements
		tr+= n.getS().accept(this);
		return tr+="return 0;\n}";
	}

	@Override
	public String visit(Statements n) throws RuntimeException {
		String tr="";
		for(Stat s: n.getChildList()) {
			tr += s.accept(this);
		}
		return tr;
	}

	@Override
	public String visit(VarDecl n) throws RuntimeException {
		String tr = "";
		tr+= n.getT().accept(this)+" ";
		tr+= n.getVdi().accept(this);
		return tr;
	}

	@Override
	public String visit(VarDecls n) throws RuntimeException {
		String tr = "";
		for(VarDecl vd: n.getChildList()) {
			tr += vd.accept(this);
		}
		return tr;
	}

	@Override
	public String visit(VarDeclsInit n) throws RuntimeException {
		String tr="";
		for(VarDeclsInitWrapper vdiw: n.getChildList()) {
			tr += vdiw.accept(this)+",";
		}
		tr = tr.substring(0, tr.length()-1);
		return tr+=";\n";
	}

	@Override
	public String visit(VarInitValue n) throws RuntimeException {
		return ""+n.getE().accept(this);
	}

	@Override
	public String visit(Vars n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	//TODO AGGIUSTARE
	public String visit(AddOp n) throws RuntimeException {
		String typeE1 = n.getE1().getType()+"";
		String typeE2 = n.getE2().getType()+"";
		String tr="";
		if(typeE1.equalsIgnoreCase("string") || typeE2.equalsIgnoreCase("string")) {
			//System.out.println(resultOfAddOp(n));
			tr+=resultOfAddOp(n);
		}else tr+=n.getE1().accept(this)+" + "+n.getE2().accept(this);
		
		return tr;
	}

	@Override
	public String visit(DivOp n) throws RuntimeException {
		return n.getE1().accept(this)+" / "+n.getE2().accept(this);
	}

	@Override
	public String visit(MultOp n) throws RuntimeException {
		return n.getE1().accept(this)+" * "+n.getE2().accept(this);
	}

	@Override
	public String visit(SubOp n) throws RuntimeException {
		return n.getE1().accept(this)+" - "+n.getE2().accept(this);
	}

	@Override
	public String visit(UminusOp n) throws RuntimeException {
		return "-"+n.getE().accept(this);
	}

	@Override
	public String visit(AndOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" && "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(NotOp n) throws RuntimeException {
		return "!("+n.getE().accept(this)+")";
	}

	@Override
	public String visit(OrOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" || "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(EqOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" == "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" >= "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" > "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" <= "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		return "("+n.getE1().accept(this)+" < "+n.getE2().accept(this)+")";
	}

	@Override
	public String visit(BoolConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(IdConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(IntConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(DoubleConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(CharConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(StringConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(AssignOp n) throws RuntimeException {
		return n.getId().accept(this)+" = "+n.getE().accept(this)+";\n";
	}

	@Override
	public String visit(CallOp n) throws RuntimeException {
		return n.getId().accept(this)+"("+n.getA().accept(this)+");\n";
	}

	@Override
	public String visit(IfThenElseOp n) throws RuntimeException {
		String tr="if(";
		tr+= n.getE().accept(this)+"){\n";
		tr+= n.getCs1().accept(this)+"}\nelse{\n";
		tr+= n.getCs2().accept(this)+"}\n";
		return tr;
	}

	@Override
	public String visit(IfThenOp n) throws RuntimeException {
		String tr="if(";
		tr+= n.getE().accept(this)+"){\n";
		tr+= n.getCs().accept(this)+"}\n";
		return tr;
	}

	@Override
	public String visit(ReadOp n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(WhileOp n) throws RuntimeException {
		return "while("+n.getE().accept(this)+"){\n"
				+ n.getCs().accept(this)+"\n}\n";
	}

	@Override
	//TODO AGGIUSTARE
	public String visit(WriteOp n) throws RuntimeException {
		String tr = "printf(";
		Expr e = n.getA().getChildList().get(0);
		if(e instanceof StringConst) {
			tr+=n.getA().accept(this);
		}else {
			if(e instanceof IntConst) {
				tr+="\"%d\", "+n.getA().accept(this);
			}
			else{
			if (e instanceof DoubleConst){
				tr+="\"%f\", "+n.getA().accept(this);
			}else {
				if(e instanceof CharConst) {
				tr+="\"%c\", "+n.getA().accept(this);
				}
			else {
				if(e instanceof BoolConst) {
					tr+="\""+n.getA().accept(this)+"\"";
					}
				else {
					if(e instanceof IdConst) {
						tr+="\""+escapeForC(""+e.getType())+"\", "+n.getA().accept(this);
						}
					else {
						if(e instanceof AddOp) {
							
							tr+="\""+escapeForC(""+e.getType())+"\", "+e.accept(this);
						}
					}
				}
			}
			}
				
			}
		}
		
		//tr+=n.getA().accept(this);
		
		return tr+=");\n";
		
		
	}
	@Override
	public String visit(Leaf n) throws RuntimeException {		
		return n.getValue();
	}

	@Override
	public String visit(ParDeclSon n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visit(VarInit n) throws RuntimeException {
		String tr = "";
		tr += n.getId().accept(this)+" = ";
		tr += n.getViv().accept(this);
		return tr;
	}

	@Override
	public String visit(VarNotInit n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(TypeLeaf n) throws RuntimeException {
		return n.getValue().toLowerCase();
	}

	@Override
	public String visit(ParTypeLeaf n) throws RuntimeException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String escapeForC(String type) {
		String tr="";
		if(type.equalsIgnoreCase("string") || type.equalsIgnoreCase("bool")) {
			tr="%s";
		}else {
			if(type.equalsIgnoreCase("int")) {
				tr="%d";
			}else {
				if(type.equalsIgnoreCase("double")) {
					tr="%f";
				}
				else {
					if(type.equalsIgnoreCase("char")) {
						tr="%c";
					}
				}
			}
		}
		return tr;
	}
	
	private String resultOfAddOp(AddOp n) {
		String tr="";
		
		final String typeOfE1 = n.getE1().getType()+"";
		final String typeOfE2 = n.getE2().getType()+"";
		
		final boolean e1EqualsInt = typeOfE1.equalsIgnoreCase("int");
		final boolean e2EqualsInt = typeOfE2.equalsIgnoreCase("int");
		
		final boolean e1EqualsStr = typeOfE1.equalsIgnoreCase("string");
		final boolean e2EqualsStr = typeOfE2.equalsIgnoreCase("string");
		
		final boolean e1EqualsDbl = typeOfE1.equalsIgnoreCase("double");
		final boolean e2EqualsDbl = typeOfE2.equalsIgnoreCase("double");
		
		final boolean e1EqualsChr = typeOfE1.equalsIgnoreCase("char");
		final boolean e2EqualsChr = typeOfE2.equalsIgnoreCase("char");
		
		final boolean e1EqualsBool = typeOfE1.equalsIgnoreCase("bool");
		final boolean e2EqualsBool = typeOfE2.equalsIgnoreCase("bool");
		
		
		if((e1EqualsStr && e2EqualsInt)) {
			tr += "strcat("+n.getE1().accept(this)+",itoa("+n.getE2().accept(this)+"))";
		}else {
		if(e2EqualsStr && e1EqualsInt) {
			tr += "strcat("+n.getE2().accept(this)+",itoa("+n.getE1().accept(this)+"))";
			
		}else {
		if((e1EqualsStr && e2EqualsStr) || (e1EqualsStr && e2EqualsChr)) {
			tr += "strcat("+n.getE1().accept(this)+", "+n.getE2().accept(this)+")";
			
		}else {
		if(e1EqualsChr && e2EqualsStr) {
				tr += "strcat("+n.getE2().accept(this)+", "+n.getE1().accept(this)+")";
				
		}else {
		if(e1EqualsStr && e2EqualsBool ) {
				tr += "strcat("+n.getE1().accept(this)+", \""+n.getE2().accept(this)+"\")";
				
		}else {
			if(e1EqualsBool && e2EqualsStr ) {
				tr += "strcat("+n.getE2().accept(this)+", \""+n.getE1().accept(this)+"\")";
				
		}
		}
		}
		}
		}
		}
		System.out.println(tr);
		return tr;
	}
	

}
