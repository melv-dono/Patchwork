package View;

import java.util.Objects;

import Model.Quiltboard;

public record ViewQuiltboard() {
	
	public static void display(Quiltboard q) {
		Objects.requireNonNull(q);
		StringBuilder b = new StringBuilder();
		
		marge(q.cols(), b);
				
		for (int i = 0; i < q.rows(); i++) {
			for (int j = 0; j < q.cols(); j++) {
				if (j == 0) { // place index for coordinate
					b.append(Integer.toString(i+1));
					b.append(Separator.vertSep());
				}
				b.append(Integer.toString(q.dimension()[i][j]));
				b.append(Separator.space());
			}
			b.append(Separator.line());
		}
		System.out.println(b.toString());
	}
	
	//Marge du haut
	private static void marge(int length, StringBuilder build) {
		build.append(Separator.space());
		for (int i = 0; i < length; i++) {			
			build.append(Separator.vertSep());
			build.append(Integer.toString(i+1));
		}
		build.append(Separator.line());
	}
}
