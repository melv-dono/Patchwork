package Model;

/**
 * <b>Label represents all the informations regarding a specific patch</b>
 * <p>
 * THis record is characterized by:
 * <ul>
 * <li>button : the number of buttons that the patch owns</li>
 * <li>price : it refers to the number of button to give in order to purchase a patch</li>
 * <li>movement : the number of case you need to cross with your pawn</li>
 * </ul>
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */

public record Label(int button, int price, int movement) {
	
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
