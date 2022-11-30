package Model;

public class Pawn {
	private int currentPosition; // Number of cases that the pawn crossed on the "plateau"
	
	public Pawn() {
		currentPosition = 1;
	}
	
	public void movePawn(int cases) {
		if (cases > 0) { // Verify that the number of cases is not negative
			currentPosition += cases;
		}
	}
	
	public int currentPosition()
	{
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
