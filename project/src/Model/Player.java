package Model;

import java.util.Objects;

/**
 * <b>Player represents a player in the game</b>
 * <p>
 * A player is characterized by :
 * <ul>
 * <li>His name.</li>
 * <li>His turn in game, true if it is turn otherwise false.</li>
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
	 * True if the player had the special tile, otherwise false.
	 * 
	 * @see Player#Player(String)
	 */
	private boolean specialTile;
	
	/**
	 * The Patch that the player must put on the quiltboard.
	 */
	private Patch patch;
	
	/**
     * Constructor player.
     * When constructing a "player" object, the turn is 0,
     * the list of buttons is empty, and the cover is also empty.
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
		this.pawn = new Pawn(1);
		this.turn = false;
		this.specialTile = false;
		this.patch = null;
	}
	
	/**
	 * Moves the pawn according to the cases parameter
	 * 
	 * @param cases
	 * 				number of movement case.
	 */
	public void movePawn(int cases) {
		if (cases < 0 || cases > 48) { throw new IllegalArgumentException(); }
		pawn.movePawn(cases);
	}
	
	/**
	 * Change the current turn of the player if he plays or not.
	 * 
	 * @see Player#turn
	 */
	public void changeTurn() {
		turn = (turn == true) ? false : true;
	}
	
	/**
	 * Count the total score of the player
	 * 
	 * @see Quiltboard#countScore()
	 * @see Player#scoreButton()
	 */
	public int totalScore() {		
		return buttons + quiltboard.countScore();
	}
	
	/**
	 * Check is the player have enough money (buttons)
	 * 
	 * @param price
	 * 		number of buttons that cost a patch
	 * 
	 * @see Game#buy()
	 * 
	 * @return
	 * 		true when the player have enough buttons
	 * 		false otherwise
	 */
	public boolean checkMoney(int price) {
		if (price < 0) { throw new IllegalArgumentException(); }
		return (buttons - price >= 0) ? true : false; 
	}
	
	/**
	 * Subtract the number of buttons required 
	 * In order to purchase the patch
	 * 
	 * @param patch
	 * 		current patch we want to buy
	 * 
	 * @see Player#buttons
	 * @see SimplePatches#price()
	 * 
	 */
	public void buyPatches(Patch patch) {
		Objects.requireNonNull(patch);
		buttons -= patch.price();
	}
	
	/**
	 * Place patch on the QuiltBoard.
	 * 
	 * @param 
	 * 			The place on the quiltboard to put the patch.
	 * 
	 * @return 
	 * 			The value is 1 if the pose is not valid othewise 0.
	 */
	public void placePatchs(int i, int j) {
		 quiltboard.putPatch(patch, i, j);
	}
	
	/**
	 * Adds buttons to player button
	 * 
	 * @param buttonWin
	 * 		number of buttons that the player earned
	 * 
	 * @see Player#buttons
	 */
	public void earnButton(int buttonWin) {
		if (buttonWin < 0) { throw new IllegalArgumentException(); }
		buttons += buttonWin;
	}
	
	/**
	 * Add player ears buttons coming from those present on his quiltboard. 
	 */
	public void earnButtonQuiltboard() {
		buttons += quiltboard.countScore();
	}
	
	/**
	 * Change special to true because the player have 7x7 cases on the quiltboard.
	 * 
	 * @see Player#specialTile
	 */
	public void getSpecialTile() {
		specialTile = true;
	}
	
	/**
	 * Getter for name.
	 */
	public String name() {
		return name;
	}
	
	/**
	 * Gives the currentPositioin of the player's pawn
	 * 
	 * @return
	 * 		 position of the pawn on the TimeBoard.
	 */
	public int currentPosition() {
		return pawn.currentPosition();
	}
	
	/**
	 * Getter for turn
	 * 
	 * @return 
	 * 		true if the turn is for this player else false.
	 */
	public boolean turn() {
		return turn;
	}
	
	/**
	 * Change the patch for the patch that the player chose.
	 * 
	 * @param 
	 * 		thisPatch The chosen patch.
	 */
	public void patchChose(Patch thisPatch)	{
		Objects.requireNonNull(thisPatch);
		patch = thisPatch;
	}
	
	/**
	 * Getter for the chosen patch.
	 * 
	 * @return
	 * 		the currentPatch that the player need to place
	 * 		on his quiltboard
	 */
	public Patch patch() {
		return patch;
	}
}
