package semantic;

import semantic.SymbolTable.Kind;
import semantic.SymbolTable.ParType;
import semantic.SymbolTable.Type;

public class Tuple {
	
	private Kind kind;
	private Type type;
	private ParType parType; //in out inout
	
	public Tuple(Kind kind, Type type, ParType parType) {
		this.kind = kind;
		this.type = type;
		this.parType = parType;
	}
	
	public Tuple(Kind kind, Type type) {
		this.kind = kind;
		this.type = type;
	}
	
	public Tuple(Kind kind) {
		this.kind = kind;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public ParType getParType() {
		return parType;
	}

	public void setParType(ParType parType) {
		this.parType = parType;
	}
	
	
	
	@Override
	  public String toString() {
	    return "Tuple [" + (kind != null ? "kind=" + kind + ", " : "") + (type != null ? "type=" + type + ", " : "")
	        + (parType != null ? "parType=" + parType + ", " : "") + "]";
	  }
	

}
