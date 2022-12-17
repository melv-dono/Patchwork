package Model;

/**
 * <b>Quiltboard represents the array that own a player</b>
 * <p>
 * Quiltboard is characterized by :
 * <ul>
 * <li>dimension : refers to the representation of it</li>
 * <li>cols : the number of cols it owns</li>
 * <li>rows : the number of rows it owns</li>
 * <li>patchKey : refers to the ènieme patch which have been put</li>
 * <li>IdOfPatch : list all the patches owns by the quiltboard linked by their index</li>
 * </ul>
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
	 * Check if every cell of the current patch can be put in the quiltboard
	 * At a specific location
	 * Then store every coordinate a each cell
	 * 
	 * @param patch
	 * 
	 * @param i
	 * 
	 * @param j
	 * 
	 * @return
	 * 
	 * 
	 */
	public Optional<List<Coordinate>> checkPatchLocation(Patch patch, int i, int j) {
		Objects.requireNonNull(patch);
		if (i < 0 || j < 0) { throw new IllegalArgumentException(); }
		
		List<Coordinate> pos = patch.realLocation();
		for (int k = 0; k < pos.size(); k++ ) {
			// Case when there is already a patch on the given location
			if (dimension[i + pos.get(k).x()][j + pos.get(k).y()] == 1) { return Optional.ofNullable(null); }
		}
		return Optional.of(pos);
	}
	
	/**
	 * Place the Patchs on the quilboard with the coordinates i and j
	 * 
	 * @param pos
	 * 			The patch that we put on the quiltboard.
	 * @param i,j
	 * 			The uppermost left square is placed on i,j.
	 * @return 
	 * 			The value is 1 if the pose is not valid othewise 0; 
	 */
	public void putPatch(Patch patch, int i, int j) {
		Objects.requireNonNull(patch);
		if (i < 0 || j < 0) { throw new IllegalArgumentException(); }
		
		List<Coordinate> pos = patch.realLocation();
		
		patchKey++;
		for (int k = 0; k < pos.size(); k++) {
			dimension[i + pos.get(k).x()][j + pos.get(k).y()] = patchKey;
		}
		
		IdOfPatch.putIfAbsent(patchKey, patch);
	}
	
	/**
	 *  count all the button on the quiltboard and return the current score
	 *  
	 *  @return
	 *  		current nbr of buttons on the quiltboard
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
	
	/* Test de fonction*/
	public static void main(String[] args) {
		Quiltboard q = new Quiltboard();
		System.out.println(q);
		
		int i = 3, j = 3;
		int tab[][] = {{0, 1}, {1, 1}};
		Label l = new Label(0, 3, 2);
		Patch p = new Patch(l, tab);
		Optional op = q.checkPatchLocation(p, i, j);
		if (op.isPresent()) {
			q.putPatch(p, i, j);
		}
		System.out.println(q);
			
		int tab2[][] = {{0, 1}, {1, 1}};
		Label l2 = new Label(0, 3, 2);
		Patch p2 = new Patch(l2, tab2);
		Optional op2 = q.checkPatchLocation(p2, 2, 3);
		if (op2.isPresent()) {
			
			q.putPatch(p2, 2, 3);
		}
		System.out.println(q);
	}
	
	
}
