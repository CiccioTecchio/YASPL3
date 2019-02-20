package semantic;

import java.util.ArrayList;

import semantic.SymbolTable.Kind;

public class DefTuple extends Tuple {
	private ArrayList<ParTuple> parArray; 
	
	public DefTuple(Kind kind) {
		super(kind);
		this.parArray = new ArrayList<>();
	}

	public ArrayList<ParTuple> getParArray() {
		return parArray;
	}

	public void setParArray(ArrayList<ParTuple> parArray) {
		this.parArray = parArray;
	}
	
	

}
