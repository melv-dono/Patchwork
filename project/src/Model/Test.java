package Model;

// TODO this class is here to do all kind of tests

public class Test {

	public static void main(String[] args) {
		var firstPlayer = new Player("first");
		var secondPlayer = new Player("second");
		var timeboard = new Timeboard(firstPlayer, secondPlayer);
		timeboard.initMagicCases();
		System.out.println(timeboard);
		/*
		int[][] t = {{1,1,1,1},{0,0,0,1}};
		Label l = new Label(1, 3, 4);
		Patch p = new Patch(l, t);
		var quiltboard = new Quiltboard();
		quiltboard.putPatch(p, 5, 0);
		p.rotate(1);
		quiltboard.putPatch(p, 1, 3);
		p.flip();
		quiltboard.putPatch(p, 1, 1);
		System.out.println(quiltboard);
		System.out.println(p);
		*/
	}

}
