package Model;

public record Coordinate(int x, int y) {
	
	public Coordinate {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
	}
	
}
