package Model;

import java.util.LinkedHashMap;

public class CirclePatch {
	/**
	 * The neutral token.
	 * 
	 */
	private final Pawn pawn;
	
	/**
	 * 
	 */
	private final LinkedHashMap<Integer, Pawn> circlePatch;
	
	public CirclePatch(LinkedHashMap<Integer, Pawn> circlePatch)
	{
		this.pawn = new Pawn();
		this.circlePatch = circlePatch;
		
	}
}
