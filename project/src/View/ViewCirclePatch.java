package View;

import java.util.List;
import java.util.Objects;

import Model.Patch;

public record ViewCirclePatch() {
	
	public void displayNextPaches(List<Patch> patchs) {
		Objects.requireNonNull(patchs);
		if (patchs.size() != 3) { throw new IllegalArgumentException(); }
		
		int i = 1;
		for (Patch p : patchs) {
			System.out.println("Patch n°" + i);
			System.out.println(p);
			i++;
		}
	}
}
