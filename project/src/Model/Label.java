package Model;

/**
 * <b>Patches represents a patch in the game</b>
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

public record Label(int button, int price, int movement) {
	
	/**
	 * number of button in the patch, this number must be positive.
	 * 
	 * @see Patches#Patches(String)
	 */
	
	/**
	 * price of the button, this number must be positive, the program check
	 * if the player have enough of button.
	 * 
	 * @see Patches#Patches(String)
	 */
	
	/**
	 * number of case that the player must make when buying patches.
	 * 
	 * @see Patches#Patches(String)
	 */
	
	public Label {
		if (button < 0 || price < 0 || movement < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append('(');
		b.append(Integer.toString(button));
		b.append(" buttons, ");
		b.append(Integer.toString(price));
		b.append(" price, ");
		b.append(Integer.toString(movement));
		b.append(" movement)");
		return b.toString();
	}

}
