package com.cicciotecchio.yaspl.semantic;

import java.util.Map;
import java.util.Objects;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		SymbolTable that = (SymbolTable) o;
		return Objects.equals(scopeName, that.scopeName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), scopeName);
	}
}
