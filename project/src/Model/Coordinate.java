package Model;

/**
 * <b>Coordinate is a record storing the position of a point</b>
 * <p>
 * There is two integers each one represent a direction :
 * <ul>
 * <li>x : represent this abscissa</li>
 * <li>y : represents this ordinate</li>
 * <li>SPECIAL_PATCH : refers to a case where you can earn a patch with a dimension 1 by 1</li>
 * </ul>
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */

public record Coordinate(int x, int y) {
	
	public Coordinate {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
	}
	
}
