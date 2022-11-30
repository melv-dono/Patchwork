package Model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
	/**
	 * copies the patch chosen by the player thanks to choosePatch()
	 * then gives the possibility to the player to place it on the quilboard. 
	 * 
	 * @param int of the next pach select.
	 * 
	 * @return Patch the copy of the patch of the player choose.
	 */
	public Patch selectNextPatch(int next)
	{
		return null;
	}
	/**
	 * List of next 3 purchasable patches
	 * 
	 */
	public List<Patch> nextPatches()
	{
		List<Patch> result = new ArrayList<Patch>();
		return result;
	}
}
