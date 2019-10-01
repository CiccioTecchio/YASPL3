package visitor;

import java.util.ArrayList;
import java.util.TreeMap;

import exception.ImpossibleReadException;
import semantic.SymbolTable.Type;
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

	private TreeMap<String, ArrayList<TreeMap<String, String>>> paramMap;
	private String toCall;
	private boolean isFunctionBody;
	
	public GenerateCVisitor() {
		paramMap = new TreeMap<>();
		toCall = "";
		isFunctionBody = false;
	}
	
	@Override
	public String visit(Args n) throws RuntimeException {
		String tr="";
		ArrayList<TreeMap<String, String>> listOfParam = paramMap.get(this.toCall);
		int i=0;
		TreeMap<String, String> entry;
		for(Expr e: n.getChildList()) {
			entry = listOfParam.get(i);
			
			tr+=(entry.get(entry.firstKey()).equalsIgnoreCase("in"))?
				 e.accept(this)+",":
				"&"+e.accept(this)+",";
			i++;
			
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
		String tr = "void ";
		tr+= n.getId().accept(this)+"(){\n";
		tr+= n.getB().accept(this);
		return tr+="}\n";
	}

	@Override
	public String visit(DefDeclPar n) throws RuntimeException {
		String tr="void ";
		String id =n.getId().accept(this)+"";
		tr+=id+"(";
		ArrayList<TreeMap<String, String>> listOfPar = new ArrayList<>();
		this.toCall = id;
		this.paramMap.put(id, listOfPar);
		tr+=n.getPd().accept(this);
		tr += "){\n";
		this.isFunctionBody = true;
		tr+=n.getB().accept(this)+"}\n";
		this.isFunctionBody = false;
		return tr;
	}

	@Override
	public String visit(ParDecls n) throws RuntimeException {
		String tr="";
		for(ParDeclSon pds: n.getChildList()) {
			tr+=pds.accept(this)+", ";
		}
		tr=tr.substring(0, tr.length()-2);
		return tr;
	}

	@Override
	public String visit(Programma n) throws RuntimeException {
		String tr = "#include <stdio.h>\n"
				+ "#include <stdlib.h>\n"
				+ "#include <string.h>\n"
				+ "#include <unistd.h>\n"
				+ "\n"
				+ "typedef int bool;\n"
				+ "#define false 0\n"
				+ "#define true 1\n"
				+ "#define STRING_CONST 256\n"
				+ "\n"
				+ "typedef char string[STRING_CONST];\n"
				+ "string yasplBuffer;\n"
				+ "string toParse;\n"
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
		String tr ="";
		for(IdConst id : n.getChildList()) {
			tr+= id.accept(this)+", ";
		}
		tr = tr.substring(0, tr.length()-2);
		return tr;
	}

	@Override
	public String visit(AddOp n) throws RuntimeException {
		return resultOfAddOp(n);
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
		final boolean E1 = n.getE1().getType().toString().equalsIgnoreCase("STRING");
		final boolean E2 = n.getE2().getType().toString().equalsIgnoreCase("STRING");
		String tr = "";
		if(E1 == true && E2 == true) {
			tr = "strcmp("+n.getE1().accept(this)+", "+n.getE2().accept(this)+")==0";
		}else {
			tr = n.getE1().accept(this)+" == "+n.getE2().accept(this);
		}
		return tr;
	}

	@Override
	public String visit(GeOp n) throws RuntimeException {
		return n.getE1().accept(this)+" >= "+n.getE2().accept(this);
	}

	@Override
	public String visit(GtOp n) throws RuntimeException {
		return n.getE1().accept(this)+" > "+n.getE2().accept(this);
	}

	@Override
	public String visit(LeOp n) throws RuntimeException {
		return n.getE1().accept(this)+" <= "+n.getE2().accept(this);
	}

	@Override
	public String visit(LtOp n) throws RuntimeException {
		return n.getE1().accept(this)+" < "+n.getE2().accept(this);
	}

	@Override
	public String visit(BoolConst n) throws RuntimeException {
		return ""+n.getId().accept(this);
	}

	@Override
	public String visit(IdConst n) throws RuntimeException {
		String tr="";
		String id = n.getId().accept(this)+"";
		if(isFunctionBody) {
			ArrayList<TreeMap<String, String>> listOfParam = this.paramMap.get(toCall);
			int len = listOfParam.size();
			TreeMap<String, String> entry = listOfParam.get(0);
			//TODO da aggiustare
			 for(int i=0; i<len; i++) {
				entry = listOfParam.get(i);
				String key = entry.firstKey();
				String value = entry.get(key);
				boolean isOut = (value.equalsIgnoreCase("OUT") || value.equalsIgnoreCase("INOUT"));
				if(entry.containsKey(id) && isOut) {
					tr="*"+id;
					break;
				}else {
					tr=id;
				}
			}
		}else {
			tr=id;
		}
		return tr;
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
		final boolean isEString = n.getE().getType().toString().equalsIgnoreCase("string");
		final boolean isIdString = n.getId().getType().toString().equalsIgnoreCase("string");
		String tr="";
		if(n.getE() instanceof AddOp && isIdString) {
		tr += n.getE().accept(this);
		tr += "strcpy("+n.getId().accept(this)+", yasplBuffer);\n";
		}else {
			tr +=   (isEString)? "strcpy("+n.getId().accept(this)+", "+n.getE().accept(this)+");\n"
							   : n.getId().accept(this)+" = "+n.getE().accept(this)+";\n";
		}
		return tr;
	}

	@Override
	public String visit(CallOp n) throws RuntimeException {
		this.toCall = n.getId().accept(this)+"";
		return (n.getA() == null)?
				toCall+"();\n":
				toCall+"("+n.getA().accept(this)+");\n";
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
		String tr="";
		String str =n.getV().accept(this)+"";
		String[] arr = str.split(",");
		int i=0;
		for(IdConst id : n.getV().getChildList()){
			if(id.getType()!=Type.BOOL) {
				tr += (id.getType()!=Type.STRING)
						?"scanf(\""+escapeForC(id.getType()+"")+"\",&"+arr[i].trim()+");\n"
						:"scanf(\""+escapeForC(id.getType()+"")+"\","+arr[i].trim()+");\n";
				i++;
			}else throw new ImpossibleReadException("ReadOp invalid for type bool");
		}
		return tr;
	}

	@Override
	public String visit(WhileOp n) throws RuntimeException {
		return "while("+n.getE().accept(this)+"){\n"
				+ n.getCs().accept(this)+"\n}\n";
	}

	@Override
	public String visit(WriteOp n) throws RuntimeException {
		String tr="";
		Expr e = n.getA().getChildList().get(0);
		final boolean isEInt = e.getType().toString().equalsIgnoreCase("int");
		final boolean isEChar = e.getType().toString().equalsIgnoreCase("char");
		final boolean isEDouble = e.getType().toString().equalsIgnoreCase("double");
		final boolean isEBool = e.getType().toString().equalsIgnoreCase("bool");
		final boolean isPrimitive = isEInt || isEChar || isEDouble || isEBool;
		String typeOfE = e.getType()+"";
		if(isEBool) {
			tr+="printf(\"%s"+"\\n\", "+e.accept(this)+"? \"true\": \"false\");\n";
		}
		else {
			if(!(e instanceof AddOp) || isPrimitive) {
				tr+="printf(\""+escapeForC(typeOfE)+"\\n\","+e.accept(this)+");\n";
			}else {
				tr+="\n";
				tr+=e.accept(this)+"\n";
				tr+="printf(\"%s\\n\", yasplBuffer);\n";
				tr+="\n";
		}	
		}
		return tr;
	}
	@Override
	public String visit(Leaf n) throws RuntimeException {		
		return n.getValue();
	}

	@Override
	public String visit(ParDeclSon n) throws RuntimeException {
		// TODO Auto-generated method stub
		String tr="";
		String id = n.getId().accept(this)+"";
		String parType = n.getParType().accept(this)+"";
		if(parType.equalsIgnoreCase("in"))
			tr+=n.getTypeLeaf().accept(this)+" "+id;
		else tr+= n.getTypeLeaf().accept(this)+" *"+n.getId().accept(this);
		ArrayList<TreeMap<String, String>> paramList = this.paramMap.get(toCall);
		TreeMap<String, String> tmp = new TreeMap<>();
		tmp.put(id, parType);
		paramList.add(tmp);
		return tr;
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
		return n.getValue();
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
					tr="%lf";
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
		
		final boolean isE1String = n.getE1().getType().toString().equalsIgnoreCase("string");
		final boolean isE2String = n.getE2().getType().toString().equalsIgnoreCase("string");
		
		final boolean isE1Int = n.getE1().getType().toString().equalsIgnoreCase("int");
		final boolean isE2Int = n.getE2().getType().toString().equalsIgnoreCase("int");
		
		final boolean isE1Char = n.getE1().getType().toString().equalsIgnoreCase("char");
		final boolean isE2Char = n.getE2().getType().toString().equalsIgnoreCase("char");
		
		final boolean isE1Double = n.getE1().getType().toString().equalsIgnoreCase("double");
		final boolean isE2Double = n.getE2().getType().toString().equalsIgnoreCase("double");
		
		final boolean isE1Bool = n.getE1().getType().toString().equalsIgnoreCase("bool");
		final boolean isE2Bool = n.getE2().getType().toString().equalsIgnoreCase("bool");
		if(n.getE1() instanceof AddOp) {
			tr+=n.getE1().accept(this);
			if(isE2String) {
				tr += "strcpy(toParse,"+n.getE2().accept(this)+");\n";
				tr+= "strcat(yasplBuffer, toParse);\n";
			}else {
				if(!isE2Bool) {
					tr += "sprintf(toParse,\""+escapeForC(n.getE2().getType()+"")+"\","+n.getE2().accept(this)+");\n";
					tr += "strcat(yasplBuffer, toParse);\n";
				}else {
					tr += resultOfStringBool(n.getE2().accept(this)+"");
				}
			}
			
		}else {
		if(isE1String && isE2String){
				tr += "strcpy(yasplBuffer,"+n.getE1().accept(this)+");\n";
				tr += "strcat(yasplBuffer, "+n.getE2().accept(this)+");\n";
		}else {
			if(isE1String && !(isE2String)){
				tr +=addWhitOneString(n.getE1(), n.getE2(), isE2Bool, isE1String, isE2Int, isE2Char, isE2Double);
				tr += "strcat(yasplBuffer, toParse);\n";
		}
		 else {
			 if(!(isE1String) && isE2String) {
				 tr += addWhitOneString(n.getE2(), n.getE1(), isE1Bool, isE2String, isE1Int, isE1Char, isE1Double);
				 tr += "strcat(toParse, yasplBuffer);\n";
				 tr += "strcpy(yasplBuffer, toParse);\n";
			 }
			 else {
			 if(!(isE1String && isE2String)) {
					tr+=n.getE1().accept(this)+" + "+n.getE2().accept(this);
				}
			 }
		}
		}
		}
		
		return tr;
	}
	
	private String addWhitOneString(Expr e1, Expr e2, boolean b, 
									boolean isStringNode, boolean isIntNode,
									boolean isCharNode, boolean isDoubleNode) {
		String tr="";
		 if((isStringNode && isIntNode) 
					||(isStringNode && isCharNode)
					||(isStringNode && isDoubleNode)
					||(isStringNode && b)
				) {
			 tr += "strcpy(yasplBuffer,"+e1.accept(this)+");\n";
				if(!b) {
					tr+="sprintf(toParse,\""+escapeForC(e2.getType()+"")+"\", "+e2.accept(this)+");\n";
				}else {
					tr+=resultOfStringBool(e2.accept(this)+"");
				}
		 }
		return tr;
	}
	
	private String resultOfStringBool(String s) {
		String tr="";
		if(s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
			tr+="sprintf(toParse,\"%s\", \""+s+"\");\n";
		}else {
			tr+="sprintf(toParse,\"%s\", "+s+"? \"true\" : \"false\");\n";
		}
		return tr;
	}
	
}
