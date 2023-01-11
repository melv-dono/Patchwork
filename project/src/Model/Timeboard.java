package Model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import View.Interaction;


public class Timeboard { // Maybe change with a record see what is the problem later
	private Player firstPlayer;
	private Player secondPlayer;
	private String turn;
	private final HashMap<Integer, Case> cases ;
	public static final int NBR_CASE = 48;
	public static final int NBR_VASE_BTW_BUTTON = 6;
	public static final int NBR_SPECIAL_PATCH = 5;
	public static final int ROW = 7;
	public static final int COL = 7;
	public Timeboard(Player firstPlayer, Player secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.cases = new HashMap<Integer, Case>();
	}
	
	/**
	 * Initialise the case of the timeboard with magic effect.
	 * 
	 */
	public void initMagicCases(Boolean specialPatch) {
		Objects.requireNonNull(specialPatch);
		Case caseValue;
		for(int idCase = 1; idCase <= NBR_CASE; idCase++)
		{
			if(idCase % 6 == 0)	{ 
				caseValue = Case.BUTTON;
			}
			else if(((idCase >= 21) && ((idCase - 21) % 6 == 0)) && specialPatch) 	{
				caseValue = Case.SPECICAL_PATCH;
			}
			else {
				caseValue = Case.NEUTRAL;
			}
			cases.put(idCase,caseValue);
		}
	}
	
	//AJOUT
	public Player first() {
		return firstPlayer;
	}
	
	public Player second() {
		return secondPlayer;
	}
	
	public Map<Integer, Case> cases() {
		return cases;
	}
	
	//FIN AJOUT
	
	@Override
	public String toString() {
		var string = new StringBuilder();
		for(var entry:cases.entrySet()) {				
			if((firstPlayer.currentPosition() == secondPlayer.currentPosition())
					&& (entry.getKey() == firstPlayer.currentPosition())) {
					string.append(firstPlayer.turn() ? firstPlayer.name().charAt(0) :
						secondPlayer.name().charAt(0));
			}
			else if(entry.getKey() == firstPlayer.currentPosition()) 	{
				string.append(firstPlayer.name().charAt(0));
			}
			else if(entry.getKey() == secondPlayer.currentPosition()) {
				string.append(secondPlayer.name().charAt(0));
			}
			else if(entry.getValue() == Case.NEUTRAL) {
				string.append(".");
			}
			else if(entry.getValue() == Case.BUTTON) {
				string.append("+");
			}
			else if(entry.getValue() == Case.SPECICAL_PATCH) {
				string.append("*");
			}
			
			if(entry.getKey() % 7 == 0) {
				string.append("\n");
			}
		}
		string.append("\n'+' for Button, '*' for Special Pacth.\n");
		return string.toString();
		
	}
	
	
	/*TODO : check if a player is on a magic case*/
	public void checkCurrentPostionPlayer(Player currentPlayer, int start, int end) {
		char respondAction = 0;
		int[] coordinate = new int[2];
		for(int caseKey = start; caseKey <= end; caseKey++) {
			if(cases.get(caseKey) == Case.BUTTON) {
				currentPlayer.earnButtonQuiltboard();	//earn the button on the quiltboard
			}
			else if(cases.get(caseKey) == Case.SPECICAL_PATCH) {
				do {
					Interaction.cleanConsole();
					System.out.println(currentPlayer.quiltboard().toString());
					System.out.println("Give the coordinate (i, j) for the special patch.");
					respondAction = Interaction.choseCoordinates(coordinate);
					if(respondAction == 'C'){
						if(currentPlayer.checkPutPatch(coordinate[0], coordinate[1])) {
							break;
						}
					}
				}while(true);
				currentPlayer.placePatchs(coordinate[0], coordinate[1]);
				cases.replace(caseKey, Case.NEUTRAL); // Remove the special patch.
			}
		}

	}
	/**
	 *  check if all the player currentPosition is at the end
	 * 
	 * @return
	 * 		true if the end game else false
	 */
	public boolean NotEndGame() {
		return (!((firstPlayer.currentPosition() == NBR_CASE) ||
				secondPlayer.currentPosition() == NBR_CASE));
	}
	
	/**
	 *  verify who is next player and modify his turn
	 */
	public void checkWhoIsTurn() {
		
		if(((firstPlayer.turn() == true) && (firstPlayer.currentPosition() > secondPlayer.currentPosition()))
				|| ((secondPlayer.turn() == true) && (secondPlayer.currentPosition() > firstPlayer.currentPosition())))	{
			firstPlayer.changeTurn();
			secondPlayer.changeTurn();
		}
	}
	
	/**
	 *  Assessor for turn. 
	 * 
	 */
	public String turn() {
		return turn;
	}
	
	/**
	 * 
	 * @return
	 */
	public Player currentPlayer() {
		return (firstPlayer.turn()) ? firstPlayer : secondPlayer;
	}
	
	/**
	 * return the oppenent of the cureent player.
	 */

	public Player oppenentPlayer(Player currentPlayer)	{
		Objects.requireNonNull(currentPlayer);
		if(currentPlayer == firstPlayer) {
			return secondPlayer;
		}
		else {
			return firstPlayer;
		}
	}
	/**
	 * Advances the player pawn and overtakes the oppenent.
	 * 
	 * @param
	 * 		currentPlayer The player who advence.
	 * @param
	 * 		opponentPlayer The opponent player oftThe player who advence.
	 */
	public void advancePlayer(Player currentPlayer, Player opponentPlayer) 	{
		Objects.requireNonNull(currentPlayer);
		Objects.requireNonNull(opponentPlayer);
		int start = currentPlayer.currentPosition();
		int end = opponentPlayer.currentPosition() + 1;
		checkCurrentPostionPlayer(currentPlayer, start, end);	
		currentPlayer.movePawn(end - start);
		currentPlayer.earnButton(end - start);
		checkWhoIsTurn();	// check who is next and change the value of player turn.
	}

}
