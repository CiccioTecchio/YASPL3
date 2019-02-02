package parser;

public class Couple {

	private int nOfOnes;
	private int nOfZeros;
	
	public int getnOfOnes() {
		return nOfOnes;
	}
	
	public int getnOfZeros() {
		return nOfZeros;
	}
		

	public Couple(int nOfOnes, int nOfZeros) {
		super();
		this.nOfOnes = nOfOnes;
		this.nOfZeros = nOfZeros;
	}

	@Override
	public String toString() {
		return nOfOnes + " ones and " + nOfZeros + " zeros";
	}	
}