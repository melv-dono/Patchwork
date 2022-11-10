package Model;

import java.util.ArrayList;


/**
 * <b>Player represents a player in the game</b>
 * <p>
 * A player is characterized by :
 * <ul>
 * <li>This name.</li>
 * <li>This turn in game, true if it is turn otherwise false.</li>
 * <li>A list of buttons that contains the buttons it has won.</li>
 * <li>A Quiltboard on which the player places these fabrics.</li>
 * <li>A pawn that the player moves.</li>
 * </ul>
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */
public class Player {
	/**
	 * name of the player, two players cannot have the same name
	 * and the name must be initialized, the name cannot be changed during the game.
	 * 
	 * @see Player#Player(String)
	 */
	private final String name;
	
	/**
	 * player turn false if the current turn is not for
	 * this player otherwise true, two players cannot have the
	 * same turn and the player must be initialized.
	 * 
	 * @see Player#Player(String)
	 */
	private boolean turn;
	
	/**
	 * number of buttons the player has.
	 * 
	 * @see Player#Player(String)
	 */
	private int buttons;
	
	/**
	 * Quiltboard type object where the player places these patch,
	 * we can just put patch not remove or move.
	 * 
	 * @see Player#Player(String)
	 */
	private final Quiltboard quiltboard;
	
	/**
	 * pawn of the player, the position of the pawn can change.
	 * 
	 * @see Player#Player(String)
	 */
	private final Pawn pawn;
	
	/**
	 * List of patch that the player win.
	 * 
	 * @see Player#Player(String)
	 */
	private final ArrayList<Patches> patches;
	
	/**
     * Constructor player.
     * <p>
     * When constructing a "player" object, the turn is 0,
     * the list of buttons is empty, and cover the is also empty.
     * 
     * @param name
     *            Name of the player.
     * 
     * @see Player#name
     * @see Player#buttons
     * @see Player#quiltboard
     * @see Player#pawn
     * @see Player#turn
     */
	public Player(String name) {
		this.name = name;
		this.buttons = 0;
		this.quiltboard = new Quiltboard();
		this.pawn = new Pawn();
		this.turn = false;
		this.patches = new ArrayList<Patches>();
	}
	
	/**
	 * moves the pawn according to the cases parameter
	 * 
	 * @param cases
	 * 				number of movement case.
	 */
	public void movePawn(int cases) {
		pawn.movePawn(cases);
	}
	
	/**
	 * change the current turn of the player if he plays or not.
	 * 
	 * @see Player#turn
	 */
	public void changeTurn() {
		turn = (turn == true) ? false : true;
	}
	
	
	/**
	 * count the total score of the player
	 * 
	 * @see Quiltboard#countScore()
	 * @see Player#scoreButton()
	 */
	public int totalScore() {		
		return buttons + quiltboard.countScore();
	}
	
	/**
	 * 
	 * @param patch The patch bought by the player.
	 * 
	 * @see Player#buttons
	 * @see SimplePatches#price()
	 * 
	 */
	public void buyPatches(SimplePatches patch) {
		if(buttons < patch.price())
		{
			System.err.println("Error : You don't have enough button.\n");
		}
		else
		{
			buttons = buttons - patch.price();
			patches.add(patch);
		}
	}
	
	/**
	 * Place patch on the QuiltBoard.
	 * 
	 * @param patch
	 */
	public void placePatchs(Patches patch, int i, int j) {
		quiltboard.putPatch(patch, i, j);
	}
	
	/**
	 * adds buttons to player button
	 * 
	 * @see Player#buttons
	 */
	public void earnButton(int buttonWin) {
		buttons = buttons + buttonWin;
	}


}
