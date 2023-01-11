package View;

import java.util.List;
import java.util.Objects;

import Model.Patch;

public record ViewCirclePatch() {
	
	public static void displayNextPaches(List<Patch> patchs) {
		int i = 1;
		StringBuilder b = new StringBuilder();
		
		Objects.requireNonNull(patchs);
		if (patchs.size() != 3) { 
			throw new IllegalArgumentException(); 
		}
		
		for (Patch p : patchs) {
			b.append("Patch n°");
			b.append(Integer.toString(i));
			b.append(Separator.line());
			b.append(p.toString());
			b.append(Separator.line());
			b.append(Separator.line());
			i++;
		}
		System.out.println(b.toString());
	}
}
