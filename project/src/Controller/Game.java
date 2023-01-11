package Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Model.CirclePatch;
import Model.Coordinate;
import Model.Label;
import Model.Patch;
import Model.Player;
import Model.Timeboard;
import View.Interaction;
import View.ViewCirclePatch;
import View.ViewQuiltboard;
import View.ViewTimeboard;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	private Scanner sc;
	
	public Game() {
		// Initialisaton de tous les objets du model
		Player first = new Player("first");
		Player second = new Player("second");
		second.changeTurn();
		this.timeboard = new Timeboard(first, second);
		this.circlePatch = new CirclePatch();
	}
	
	/**
	 * Initialize every object of the model and the view
	 * 
	 * @param specialPatch
	 */
	public void init(boolean specialPatch) {
		Objects.requireNonNull(specialPatch);
		timeboard.initMagicCases(specialPatch);
		
		// Configuration du jeu à savoir quelle version lancée
		String file = specialPatch ? "src/data/phase2.txt" : "src/data/phase1.txt";
		try {
			circlePatch.initCirclePatch(file, specialPatch ? 33 : 2, specialPatch ? 1 : 20); 				
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}		
		circlePatch.initNeutralToken(); // mettre cette fonctigon dans l'initialisation générale de circlePatch
	}
	
	/**
	 * TODO
	 */
	public void runGame() {
		while(timeboard.NotEndGame()) {
			if(performTurn() == 1) {
				return;
			}
		}
	}
	
	
	
	/**
	 * TODO
	 */
	public int performTurn() {
		System.out.println("#######################################");
		Player currentPlayer = timeboard.currentPlayer();
		Player opponentPlayer = timeboard.oppenentPlayer(currentPlayer);
		ViewCirclePatch viewCirclePatch = new ViewCirclePatch();
		int advanceOrTakeRespond;
		Interaction.cleanConsole();
		ViewTimeboard.display(timeboard);
		ViewCirclePatch.displayNextPaches(circlePatch.nextPatches());
		System.out.println(currentPlayer);
		// à remplacer par les fonctions de display.
		advanceOrTakeRespond = Interaction.advanceOrTake();
		// do a switch
		if (advanceOrTakeRespond == 3){
			return 1;
		}
		else if(advanceOrTakeRespond == 1) {	// If the player want to buy pacth.
			if(buy(currentPlayer) == 1) {return 1;};
		}
		else if(advanceOrTakeRespond == 2) { // If the player want to advance.
			timeboard.advancePlayer(currentPlayer, timeboard.oppenentPlayer(currentPlayer));
		}
		return 0;
	}
	
	
	/**
	 * Perform every operation to buy an item
	 */
	public int buy(Player currentPlayer) {
		Patch currPatch;
		int[] coordinate = new int[2];
		int respondChosePatch;	// The chosen patch.
		char respondAction;		// The chosen action (Flip, rotate, coordinate).
		ViewCirclePatch.displayNextPaches(circlePatch.nextPatches());
		
		respondChosePatch = Interaction.chosePatch(); 
		currPatch = circlePatch.nextPatches().get(respondChosePatch);
		if (currentPlayer.checkMoney(currPatch.price())) {	//checking if the player have money
			currentPlayer.patchChose(currPatch);
			do {
//				System.out.println(currentPlayer.quiltboard());
				ViewQuiltboard.display(currentPlayer.quiltboard());
				System.out.println(currentPlayer.patch());
				respondAction = Interaction.choseCoordinates(coordinate);
				if(respondAction == 'L' || respondAction == 'R') {
					System.out.println("ROTATE !");
					currentPlayer.patch().rotate((respondAction == 'R'));
				}
				else if(respondAction == 'F') {
					currentPlayer.patch().flip();
				}
				else if(respondAction == 'Q') {
					return 1;
				}
//				System.out.println(currentPlayer.quiltboard());
				ViewQuiltboard.display(currentPlayer.quiltboard());
				if(respondAction == 'C'){
					if(currentPlayer.checkPutPatch(coordinate[0], coordinate[1])) {
						break;
					}
				}
			}while(true);
			currentPlayer.placePatchs(coordinate[0], coordinate[1]);
			currentPlayer.buyPatches(currPatch);
			timeboard.checkCurrentPostionPlayer(currentPlayer,currentPlayer.currentPosition(),
					currentPlayer.currentPosition() + currPatch.movement());
			currentPlayer.movePawn(currPatch.movement());
			timeboard.checkWhoIsTurn();	// check who is next and change the value of player turn.
			circlePatch.swapPatch(respondChosePatch);
		}
		else {
			System.out.println("Vous n'avez pas assez d'argent");
		}
		return 0;
	}
	
	/**
	 * Acsessor for CirclePatch;
	 */
	public CirclePatch circlePatch(){
		return circlePatch;
	}
	
	public Timeboard timeboard() {
		return timeboard;
	}
	
	public static void main(String[] args) {
		
		Game game = new Game();
		game.init(true);
		game.runGame();
		/*
		int i = 3, j = 3;
		int tab[][] = {{0, 1}, {1, 1}};
		Label l = new Label(0, 3, 2);
		Patch p = new Patch(l, tab);
		Player first = new Player("first");
		first.patchChose(p);
		first.placePatchs(i, j);
		System.out.println(first.quiltboard());
		int tab2[][] = {{0, 1}, {1, 1}};
		Label l2 = new Label(0, 3, 2);
		Patch p2 = new Patch(l2, tab2);
		first.patchChose(p2);
		if(first.checkPutPatch(i, j)) {
			first.placePatchs(2, 3);
		}
		System.out.println(first.quiltboard());
		*/
	}
}
