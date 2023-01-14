package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <b>Patch represents a patch in the game</b>
 * <p>
 * A patch is characterized by :
 * <ul>
 * <li>This number of button.</li>
 * <li>This price, the player need "price" button to buy the patch.</li>
 * <li>This number of cases, The player must move this pawn to suit this variable when they buy patches.</li>
 * <li>This layout and dimension thanks to Int[][], 1 represent case in the tab.</li>
 * </ul>
 * </p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */
public class Patch {
	
	/**
	 * Represent all the informations you need to  know for a patch
	 * price, number of buttons its owns, number of movement
	 * 
	 */
	private final Label label;
	
	/**
	 * The layout and dimension of the patch.
	 * 
	 * @see Patch#Patches(String)
	 */
	private int[][] dimension;
	
	/**
     * Constructor patches.
     * @param nbrButtons
     *            	Number of button in the patch.
     * @param price
     * 				Price of the patch.
     * @param spaces
     * 				number of case that the player must make when buying patches.
     * @param dimension
     * 				 The layout and dimension of the patch.
     * 
     * @see Patch#nbrButtons
     * @see Patch#price
     * @see Patch#spaces
     * @see Patch#dimension
     */
	public Patch(Label label, int[][] dimension) {
		this.dimension = Objects.requireNonNull(dimension);
		this.label = Objects.requireNonNull(label);
	}
	
	/**
	 * Change the {@link Patch#dimension} array to another array
	 * in which the rotation is performed.
	 * 
	 * @param direction
	 * 				The direction of the rotation, 1 for left rotation otherwise -1 for right rotation. 
	 */
	public void rotate(boolean direction) {
        int copy[][] = new int[dimension[0].length][dimension.length];
        for(int i = 0, k = (direction ? dimension.length - 1 : 0 ); i < dimension.length; i++, k += (direction ? -1 : 1))
        {
            for(int j = 0, l = (direction ? 0 : dimension[0].length - 1); j < dimension[0].length; j++, l += (direction ? 1 : -1))
            {
                copy[l][k] = dimension[i][j];
            }
        }
        dimension = copy;
	}
	
	/**
     * Flips the dimension array of the Patch object.
     * The flip is performed by creating a copy of the original array
     * and swapping the elements to reflect the flip.
     * The original dimension array is then replaced with the flipped copy.
     */
	public void flip()	{
		int copy[][] = new int[dimension.length][dimension[0].length];
        for(int i = 0; i < dimension.length; i++)
        {
            for(int j = 0, k = dimension[0].length - 1; j < dimension[0].length; j++, k--)
            {
                copy[i][k] = dimension[i][j];
            }
        }
        dimension = copy;
	}
	
	/**
	 * Getter for price.
	 */
	public int price() {
		return label.price();
	}
	
	/**
	 * Getter for Number of button.
	 */
	public int buttons() {
		return label.button();
	}
	
	/**
	 * Getter for Number of case.
	 */
	public int movement() {
		return label.movement();
	}
	
	/**
	 * Getter for patch's label.
	 */
	public Label label() {
		return label;
	}
	
	/**
	 * Getter for the patch's area
	 * 
	 * @see CirclePatch#initNeutralToken()
	 * 
	 * @return 
	 * 		value representing the area of the current patch
	 */
	public int area() {
		return dimension.length * dimension[0].length;
	}
	
	/**
	 * Getter for dimension.
	 */
	public int[][] dimension() {
		return dimension;
	}
	
	/**
	 * Locate on the representation of a patch every cell equals to 1
	 * 
	 * @return
	 * 		return a list of coordinates for every existing cell of a patch
	 */
	
	public List<Coordinate> realLocation() {
		ArrayList<Coordinate> c = new ArrayList<>();
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0; j < dimension[0].length; j++) {
				if (dimension[i][j] == 1) { // Adding coordinate when the cell exists
					c.add(new Coordinate(i, j));
				}
			}
		}
		return List.copyOf(c);
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0; j < dimension[0].length; j++) {
				b.append(Integer.toString(dimension[i][j]));
			}
			b.append("\n");
		}
		b.append(label.toString());
		return b.toString();
	}
	
}