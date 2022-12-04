package Model;

import Controller.Game;

public class main {

	public static void main(String[] args) {
		var game = new Game(false);
		CirclePatch c = game.circlePatch();
		System.out.println(c);
	}

}
