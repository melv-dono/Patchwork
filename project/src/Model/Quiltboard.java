package Model;

import java.util.LinkedHashMap;

public class Quiltboard {

	private final int[][] dimension; //Maybe find a better name for this attribut
	private final LinkedHashMap<Integer, Patchs> patchs;
	private final static int cols = 8;
	private final static int rows = 8;
	
	public Quiltboard() {
		dimension = new int[cols][rows];
		patchs = new LinkedHashMap<Integer, Patchs>();
	}
	
	/*TODO : Place the Patchs on the quilboard with the coordinates i and j*/
	public void putPatch(Patchs p, int i, int j) {
		
	}
	
	/*TODO count all the button on the quiltboard and return the current score*/
	public int countScore() {
		return 0;
	}
}
