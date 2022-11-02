package Model;

import java.util.Objects;

public record Patchs(int nbrButtons, int spaces, int[][] dimension) {
	public Pacths {
		Objects.requireNonNull(dimension);
		/* Warning this part may need to be erase later*/
		if (nbrButtons < 0 || spaces < 0) {
			throw new IllegalArgumentException();
		}	
	}
	
	/*TODO : Cette méthode permet de faire tourner un Patchs (Solution orientation : Enum)*/
	public void rotate() {
		
	}

}
