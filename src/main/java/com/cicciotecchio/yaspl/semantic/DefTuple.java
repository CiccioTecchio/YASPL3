package com.cicciotecchio.yaspl.semantic;

import com.cicciotecchio.yaspl.semantic.SymbolTable.Kind;
import java.util.ArrayList;

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

	@Override
	public String toString() {
		return "DefTuple [parArray=" + parArray + "]";
	}
	
	

}
