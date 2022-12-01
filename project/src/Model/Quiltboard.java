package Model;

import java.util.LinkedHashMap;

public class Quiltboard {

	private final int[][] dimension;
	/**
	 * Map of patch.
	 */
	private final LinkedHashMap<Integer, Patch> IdOfPatch;
	/**
	 * The dimension of the quiltboard.
	 */
	private final static int cols = 9;
	private final static int rows = 9;
	/**
	 * The last int we use for the key of patch.
	 */
	private static int patchKey;
	
	public Quiltboard() {
		dimension = new int[cols][rows];
		IdOfPatch = new LinkedHashMap<Integer, Patch>();
		patchKey = 0;
	}
	
	/**
	 * Place the Patchs on the quilboard with the coordinates i and j
	 * 
	 * @param patch
	 * 			The patch that we put on the quiltboard.
	 * @param i,j
	 * 			The uppermost left square is placed on i,j.
	 * @return 
	 * 			The value is 1 if the pose is not valid othewise 0; 
	 */
	public int putPatch(Patch patch, int i, int j) {
		int[][] dimensionTmp = patch.dimension();
		patchKey++;
		for(int row = i, iPatch = 0; row < (dimensionTmp.length + i); row++, iPatch++)
		{
			for(int col = j, jPatch = 0; col < (dimensionTmp[0].length + j); col++, jPatch++)
			{
				if(dimension[row][col] != 0) {
					dimension[row][col] = (dimensionTmp[iPatch][jPatch] == 1) ? patchKey : 0;
				}
				else {
					return 1;
				}
			}
		}
		IdOfPatch.putIfAbsent(patchKey, patch);
		return 0;
	}
	
	/**
	 *  count all the button on the quiltboard and return the current score
	 *  
	 *
	 */
	public int countScore() {
		int result = 0;
		for(var entry: IdOfPatch.entrySet())
		{
			result += entry.getValue().price();
		}
		return result;
	}
	
	/**
	 * Print of the quiltboard.
	 */
	@Override
	public String toString()
	{
		var result = new StringBuilder();
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				result.append(dimension[row][col]);
			}
			result.append("\n");
		}
		result.append("\n");
		return result.toString();
	}
	
	
}
