package Controller;

import java.io.IOException;
import java.util.Objects;

import Model.CirclePatch;
import Model.Player;
import Model.Timeboard;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	
	public Game(boolean specialPatch)
	{
		Objects.requireNonNull(specialPatch);
		var firstPlayer = new Player("first");
		var secondPlayer = new Player("second");
		this.timeboard = new Timeboard(firstPlayer, secondPlayer);
		this.circlePatch = new CirclePatch();
		timeboard.initMagicCases(specialPatch);
		String file = specialPatch ? "phase2.txt" : "phase1.txt";
		try {
			circlePatch.initCirclePatch(file, 2, 20);				
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
		circlePatch.initNeutralToken();
	}
	
	/**
	 * Acsessor for CirclePatch;
	 */
	public CirclePatch circlePatch()
	{
		return circlePatch;
	}
	
	
}
