package Model;

import java.util.Objects;

public record SimplePatches(int buttons, int spaces, int price, int[][] dimension) implements Patches{
	
	/*TODO : Cette méthode permet de faire tourner un Patchs (Solution orientation : tourner que dans un sens)*/
	public void rotate() {
		
	}

}
