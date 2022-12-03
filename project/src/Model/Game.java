package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	
	public Game(boolean specialPatch)
	{
		Objects.requireNonNull(specialPatch);
		var firstPlayer = new Player("first");
		var secondPlayer = new Player("second");
		var timeboard = new Timeboard(firstPlayer, secondPlayer);
		var circlePatch = new CirclePatch();
		timeboard.initMagicCases(specialPatch);
		String file = specialPatch ? "phase2.txt" : "phase1.txt";
		try {
			circlePatch.initCirclePatch(file, 2, 20);				
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
		
		// circlePatch.initNeutralToken();
	}
	public CirclePatch circlePatch()
	{
		circlePatch.circlePatch().forEach(x -> System.out.println(x));
		return circlePatch;
	}
	
	
}
