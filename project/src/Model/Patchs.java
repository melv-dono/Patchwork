package Model;

import java.util.Arrays;
import java.util.Objects;

public record Patchs(int nbrButtons,int price, int spaces, int[][] dimension) {
	/*
	public Pacths {
		Objects.requireNonNull(dimension);
		// Warning this part may need to be erase later
		if (nbrButtons < 0 || spaces < 0) {
			throw new IllegalArgumentException();
		}	
	}*/
	
	/*TODO : Cette méthode permet de faire tourner un Patchs (Solution orientation : Enum)*/
	public void rightRotate() {
		int copyTab[][] = dimension;
		
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0, k = dimension.length - 1; j < dimension.length; j++, k--) {
				dimension[i][k] = copyTab[j][i];
				System.out.println("(" + j + "," + i + ")" + " -> " + "(" + i + "," + k + ")");
			}			
		}
	}
	
	public void rotate() {
		int k = dimension.length - 1;
		int copy[][] = new int[dimension[0].length][dimension.length];
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0; j < dimension[0].length; j++) {
				copy[j][k-i] = 	dimension[i][j];
			}
		}
		for (int i = 0; i < dimension[0].length; i++ ) {
			dimension[i] = copy[i].clone();
		}
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < dimension.length; i++) {
			for (int j = 0; j < dimension[0].length; j++) {
				b.append(Integer.toString(dimension[i][j]));
			}
			b.append("\n");
		}
		b.append('(');
		b.append(Integer.toString(nbrButtons));
		b.append(" buttons, ");
		b.append(Integer.toString(price));
		b.append(" price, ");
		b.append(Integer.toString(spaces));
		b.append(" movement)");
		return b.toString();
	}
	
	public static void main(String[] args) {
		int[][] t = {{1,1,1},{0,0,1}};
		Patchs p = new Patchs(1, 3, 4, t);
		System.out.println(p);
		p.rotate();
		System.out.println(p);
	}
}
