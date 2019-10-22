package com.cicciotecchio.yaspl.visitor;

public interface Visitable{
		
	public Object accept(Visitor<?> visitor) throws RuntimeException;
	
}
