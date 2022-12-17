package Model;

/**
 * <b>Pawn enable to store the current position on the timeboard</b>
 * <p>
 * The pawn is only characterized by his position which simply an integer
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */

public class Pawn {
	
	/**
	 * Number of case the pawn already crossed
	 */
	private int currentPosition; 
	
	/**
	 * 
	 * @param position
	 */
	public Pawn(int position) {
		if (position < 0 || position > 48) { throw new IllegalArgumentException(); }
		this.currentPosition = position;
	}
	
	
	/**
	 * Change the currentPostion of the pawn to a new one
	 * 
	 * @param cases
	 * 			number of cases you need to move forward
	 */
	public void movePawn(int cases) {
		if (cases < 0) { // Verify that the number of cases is not negative
			throw new IllegalArgumentException(); 
		}
		currentPosition += cases;
	}
	
	/**
	 * Give an access to the attribute currentPosition
	 * @return
	 * 		the value of the currentPosition
	 */
	public int currentPosition() {
		return currentPosition;
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append(' ');
		string.append(currentPosition);
		return string.toString();
	}
}
