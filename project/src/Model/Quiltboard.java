package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Quiltboard {

	private final int[][] dimension; //Maybe find a better name for this attribut
	/**
	 * Map of patches.
	 */
	private final LinkedHashMap<Integer, Patchs> patches;
	/**
	 * The dimension of the quiltboard.
	 */
	private final static int cols = 9;
	private final static int rows = 9;
	/**
	 * The last int we use for the key of patches.
	 */
	private static int patchKey;
	
	public Quiltboard() {
		dimension = new int[cols][rows];
		patches = new LinkedHashMap<Integer, Patchs>();
		patchKey = 0;
	}
	
	/*TODO : Place the Patchs on the quilboard with the coordinates i and j*/
	public void putPatch(Patches patch, int i, int j) {
		ArrayList<ArrayList<Integer>> dimensionTmp = patch.dimension();
		/*
		for(int row = i; i < dimensionTmp.; row++)
		{
			for(int col = j; j < dimensionTmp[row].length; col++)
			{
				 dimension[row][col] = dimensionTmp[row][col];
			}
		}
		*/
		patches.putIfAbsent(patchKey, patch);
		patchKey++;
	}
	
	/**
	 *  count all the button on the quiltboard and return the current score
	 *  
	 *
	 */
	public int countScore() {
		int result = 0;
		for(var entry: patches.entrySet())
		{
			result += entry.getValue().nbrButtons();
		}
		return result;
	}
	
	/**
	 * Print of the quiltboard.
	 */
	public String toString()
	{
		var result = new StringBuilder();
		for(int row = 0; row < rows; row++)
		{
			result.append("[");
			for(int col = 0; col < cols; col++)
			{
				result.append(dimension[row][col]);
				result.append(" ");
			}
			result.append("]\n");
		}
		result.append("\n");
		return result.toString();
	}
	
}
