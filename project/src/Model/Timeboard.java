package Model;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import View.Graphique;
import View.Interaction;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.Event.Action;


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
	
	public void checkCurrentPostionPlayer(Player currentPlayer, int start, int end, ApplicationContext context , int X, int Y, int sizeCase) {
        Graphique graph = new Graphique(sizeCase, 30);
		for(int caseKey = start; caseKey <= end; caseKey++) {
			if(cases.get(caseKey) == Case.BUTTON) {
				currentPlayer.earnButtonQuiltboard();	//earn the button on the quiltboard
			}
			else if(cases.get(caseKey) == Case.SPECICAL_PATCH) {
	      	    graph.displaySpecialPatch(context, X, Y);
	      	    int tab[][] = {{1}};
	      	    Label l = new Label(0, 0, 0);
	      	    Patch p = new Patch(l, tab);
	      	    Patch currPatch = p;
    			currentPlayer.patchChose(currPatch);
	      	    int i = 0, j = 0;
	      	    for(;;) {
	      	    	Event event = context.pollEvent();
	    	        if (event == null) {  // no event	
	    	          continue;
	    	        }
	    	        Action action = event.getAction();
	    	        Point2D.Float location = event.getLocation();
	    	        if(action == Action.POINTER_DOWN) {              
	    	        	int x_s, y_s, x_e, y_e;
	    	        	if(currentPlayer.name() == "first") {
	    	        		 x_s = sizeCase * 3;
	    	        		 y_s = sizeCase * 3;
	    	        		 x_e = sizeCase * 12;
	    	        		 y_e = sizeCase * 12;
	    	        	}
    	        		else {
    	        			 x_s = sizeCase * (3 + 18);
    	        			 y_s = sizeCase * 3;
	    	        		 x_e = sizeCase * (3 + 18 + 9);
	    	        		 y_e = sizeCase * 12;
    	        		}
    	        		if(location.x >= x_s && location.x <= x_e &&
	        				location.y >= y_s && location.y <= y_e) {
    	        			j = (int) ((location.x - x_s) / sizeCase);
    	        			i = (int) ((location.y - y_s) / sizeCase);
    						if(currentPlayer.checkPutPatch(i, j)) {
	    							break;
    						}	
	    	        		
	    	        	}
	      	    }
				currentPlayer.placePatchs(i, j);
				cases.replace(caseKey, Case.NEUTRAL); // Remove the special patch.
			}
		}
		}
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
	
	public Player firstPlayer() {
		return firstPlayer;
	}
	
	public Player secondPlayer() {
		return secondPlayer;
	}
	/**
	 * Advances the player pawn and overtakes the oppenent.
	 * 
	 * @param
	 * 		currentPlayer The player who advence.
	 * @param
	 * 		opponentPlayer The opponent player oftThe player who advence.
	 */
	public void advancePlayer(Player currentPlayer, Player opponentPlayer, ApplicationContext context, int sizeCase) 	{
		//checkCurrentPostionPlayer(ApplicationContext context , Player currentPlayer, int start, int end, int X, int Y, int sizeCase);
		Objects.requireNonNull(currentPlayer);
		Objects.requireNonNull(opponentPlayer);
		int start = currentPlayer.currentPosition();
		int end = opponentPlayer.currentPosition() + 1;
		checkCurrentPostionPlayer(currentPlayer, start, end, context, sizeCase * 13, sizeCase * 12, sizeCase);	
		currentPlayer.movePawn(end - start);
		currentPlayer.earnButton(end - start);
		checkWhoIsTurn();	// check who is next and change the value of player turn.
	}
	
	/**(
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
