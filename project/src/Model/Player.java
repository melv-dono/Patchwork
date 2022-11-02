package Model;

import java.util.ArrayList;

public class Player { // Check later record or not
	private final String name;
	private final boolean turn;
	private final ArrayList<Button> buttons; //WARNING change name later
	private final Quiltboard quiltboard; //WARNING change name later
	private final Pawn pawn;
	
	public Player(String name, ArrayList<Button> b, Quiltboard q, Pawn p) {
		this.name = name;
		buttons = b;
		quiltboard = q;		
		pawn = p;
		turn = false; //Warning how to init the first player to go
	}
	
	/*TODO : player want to move his pawn*/
	public void movePawn();
	
	/*TODO : change the current turn of the player if he plays or not*/
	public void changeTurn() {
		
	}
	
	/*TODO : count the total number of button*/
	public int scoreButton() {
		return 0;
	}
	
	/*TODO : count the total score of the player*/
	public int totalScore() {
		return 0;
	}
	
	//WARNING
	/*TODO : buy a patchs*/
	public void buyPatchs() {
		
	}
	
	//wARNING
	/*TODO : place patchs on the quiltboard*/
	public void placePatchs() {
		
	}
	
	//Warning 
	/*TODO : gain buttons*/
	public void earnButton() {
		
	}
	
	
	/*Exemple potentiel java doc*/
	/**
	 * @param a : integer
	 * @return addition
	 */
	public int myfunction(int a, int b) {
		return 0;
	}

}
