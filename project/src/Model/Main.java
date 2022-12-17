package Model;

import Controller.Game;

public class Main {

	public static void main(String[] args) {
		var game = new Game();
		System.out.println("Work in progress...");
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
	}

}
