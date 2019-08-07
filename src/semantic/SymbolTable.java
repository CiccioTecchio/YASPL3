package semantic;

import java.util.Map;
import java.util.TreeMap;

public class SymbolTable extends TreeMap<String, Tuple> {
	
	private static final long serialVersionUID = 1L;
	private String scopeName;
	public SymbolTable() {
		super();
	}
	
	public SymbolTable(String name) {
		super();
		this.scopeName = name;
	}
	
	public enum Kind{
		VARDECL,
		DEFDECL;
	}

	public enum Type{
		INT,
		STRING,
		DOUBLE,
		CHAR,
		BOOL,
		VOID;
	}	
	public enum ParType{
		IN,
		OUT,
		INOUT;
	}
	
	/*@Override
	  public String toString() {
	    String toReturn = "SymbolTable "+hashCode()+"\n";
	    for(Map.Entry<String, Tuple> e : this.entrySet()) {
	      toReturn += "<Name:\t"+e.getKey()+"\t\t"+e.getValue()+">\n";
	    }
	    return toReturn;
	  }*/
	
	@Override
	  public String toString() {
	    String toReturn = "SymbolTable "+this.scopeName+"\n";
	    for(Map.Entry<String, Tuple> e : this.entrySet()) {
	      toReturn += "<Name:\t"+e.getKey()+"\t\t"+e.getValue()+">\n";
	    }
	    return toReturn;
	  }
	
	public String getScopeName() {
		return scopeName;
	}
	
}
