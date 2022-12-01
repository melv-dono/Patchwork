package Model;

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
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */
public class Patch {
	/**
	 * number of button in the patch, this number must be positive.
	 * 
	 * @see Patch#Patches(String)
	 */
//	private final int nbrButtons;
	
	/**
	 * price of the button, this number must be positive, the program check
	 * if the player have enough of button.
	 * 
	 * @see Patch#Patches(String)
	 */
//	private final int price;
	
	/**
	 * number of case that the player must make when buying patches.
	 * 
	 * @see Patch#Patches(String)
	 */
//	private final int spaces;
	
	
	
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
<<<<<<< Updated upstream:project/src/Model/Patch.java
	public Patch(int nbrButtons, int price, int spaces, int[][] dimension)
	{
		this.nbrButtons = nbrButtons;
		this.price = price;
		this.spaces = spaces;
		this.dimension = dimension;
=======
	public Patches(Label label, int[][] dimension) {
		this.dimension = Objects.requireNonNull(dimension);
		this.label = label;
>>>>>>> Stashed changes:project/src/Model/Patches.java
	}
	
	/**
	 * change the {@link Patch#dimension} array to another array
	 * in which the rotation is performed.
	 * 
	 * @param direction
	 * 				The direction of the rotation, 1 for left rotation otherwise -1 for right rotation. 
	 */
	public void rotate(int direction) {
		int k = switch (direction) {
		case -1: {
			yield dimension.length - 1;
		}
		case 1: {
			yield 0;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		};
        int copy[][] = new int[dimension[0].length][dimension.length];
        for(int i = 0; i < dimension.length; i++,k+=direction)
        {
            for(int j = 0; j < dimension[0].length; j++)
            {
                copy[j][k] = dimension[i][j];
            }
        }
        dimension = copy;
	}
	
	/**
	 * change the {@link Patch#dimension} array to another array
	 * in which the flip is performed.
	 *  
	 */
	public void flip()
	{
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
	 * Accessor for price.
	 */
	public int price()
	{
		return label.price();
	}
	
	/**
	 * Accessor for Number of button.
	 */
	public int buttons()
	{
		return label.button();
	}
	
	/**
	 * Accessor for Number of case.
	 */
	public int movement()
	{
		return label.movement();
	}
	
	/**
	 * Accessor for patch's label.
	 */
	public Label label() {
		return label;
	}
	
	/**
	 * Accessor for dimension.
	 */
	public int[][] dimension()
	{
		return dimension;
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
	
<<<<<<< Updated upstream:project/src/Model/Patch.java
=======
	public static void main(String[] args) {
		int[][] t = {{1,1,1,1},{0,0,0,1}};
		Label l = new Label(1, 3, 4);
		Patches p = new Patches(l, t);
		System.out.println(p);
		p.rotate(1);
		System.out.println(p);
		p.flip();
		System.out.println(p);
	}
>>>>>>> Stashed changes:project/src/Model/Patches.java
}