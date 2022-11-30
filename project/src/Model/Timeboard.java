package Model;

import java.util.ArrayList;
import java.util.HashMap;


public class Timeboard { // Maybe change with a record see what is the problem later
	private final ArrayList<Player> players; // WARNING CHNAGE TYPE
	private final HashMap<Integer, MagicCase> magicCases;
	public static final int NBR_CASE = 52; // We will see later for that
	public static final int NBR_SPECIAL_PATCH = 9;
	public Timeboard(ArrayList<Player> players) {
		this.players = players;
		this.magicCases = new HashMap<Integer, MagicCase>();
	}
	
	/*TODO : initialise the case of the timeboard with magic effect (random)*/
	public void initMagicCases() {
		
	}
	
	/*TODO : check if a player is on a magic case*/
	public void checkCurrentPostionPlayer() {
		
	}
	
	/*TODO : check if all the player currentPosition is at the end
	 * Warning maybe this method is not in the right place*/
	public boolean endGame() {
		return false;
	}
	
	/*TODO : verify who is next player and modify his turn
	 * Warning maybe this method is not in the right place*/
	public void whoIsTurn() {
		
	}

}
