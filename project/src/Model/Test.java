package Model;

// TODO this class is here to do all kind of tests

public class Test {

	public static void main(String[] args) {
		int[][] t = {{1,1,1,1},{0,0,0,1}};
		Patch p = new Patch(1, 3, 4, t);
		var quiltbard = new Quiltboard();
		quiltbard.putPatch(p, 5, 0);
		p.rotate(1);
		quiltbard.putPatch(p, 1, 3);
		p.flip();
		quiltbard.putPatch(p, 1, 1);
		System.out.println(quiltbard);
	}

}
