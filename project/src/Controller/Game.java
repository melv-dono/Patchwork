package Controller;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import Model.CirclePatch;
import Model.Coordinate;
import Model.Label;
import Model.Patch;
import Model.Player;
import Model.Timeboard;
import Model.Player;
import View.Interaction;
import View.InteractionGraphique;
import View.ViewCirclePatch;
import View.ViewQuiltboard;
import View.ViewTimeboard;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	private Scanner sc;
	private Player[] players;
	
	public Game() {
		// Initialisaton de tous les objets du model
		players = new Player[2];
		players[0] = new Player("first");
		players[1] = new Player("second");
		players[0].changeTurn();
		this.timeboard = new Timeboard(players[0], players[1]);
		this.circlePatch = new CirclePatch();
	}
	
	private void updatePlayers() {
		players[0] = timeboard.currentPlayer();
		players[1] = timeboard.oppenentPlayer(timeboard.currentPlayer());
	}
	
	private void checkModeGluton() {
		int answer = Interaction.checkModeGlouton();
		if (answer == 1) {
			players[0].activeModeGlouton();
			players[1].activeModeGlouton();
		}
	}
	
	/**
	 * Initialize every object of the model and the view
	 * 
	 * @param specialPatch
	 * @return
	 * 		0 if its the graphic mode otherwise 1.
	 */
	public int init() {
		int version = Interaction.initGame();
		timeboard.initMagicCases((version == 1) ? false : true);
		if (version == 1) {
			checkModeGluton();
		}
		
		// Configuration du jeu à savoir quelle version lancée
		String file = (version == 1) ? "phase1.txt" : "phase2.txt";
		try {
			circlePatch.initCirclePatch(file, (version == 1) ? 2 : 33, (version == 1) ? 20 : 1); 				
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return (version == 3) ? 0 : 1;
	}
	
	private Optional<Player> whoWins(Player p1, Player p2) {
		if (p1.totalScore() > p2.totalScore()) {
			return Optional.of(p1);
		}
		else if (p1.totalScore() == p2.totalScore()) {
			return Optional.empty();
		}
		else {
			return Optional.of(p2);
		}
	}
	
	/**
	 * TODO
	 */
	public void runGame() {
		Optional<Player> Winner;
		updatePlayers();
		while(timeboard.NotEndGame()) {
			if(performTurn() == 1) {
				return;
			}
		}
		Winner = whoWins(players[0], players[1]);
		if (Winner.isEmpty()) {
			System.out.println("There is no Winner good game to both of you");
		}
		else {
			System.out.println(Winner.get().name() + " wins the Game congratulation");
		}
	}
	
	/*private void runAutoma(int pos) {
		Optional<Patch> patch;
		 patch = timeboard.currentPlayer()
				 .performTurn(circlePatch().nextPatches(), pos);
		 if (patch.isEmpty()) {
			 timeboard.advancePlayer(timeboard.currentPlayer(),
					 timeboard.oppenentPlayer(timeboard.currentPlayer()));
		 }
		 else {
			 //Mode tactique afficher la carte de l'automa
				timeboard.checkWhoIsTurn();	// check who is next and change the value of player turn.
				circlePatch.swapPatch(0); //faire une fonction qui fait le même job
		 }
	}*/
	
	private int detPLayerChoice(int response) {
			switch (response) {
				case 1:
					if (buy() == 1) {
						return 1;
					}
					break;
				case 2:
					timeboard.advancePlayer(players[0], players[1]);
					return 0;
				case 3:  
					return 1;
				default:
					break;
			};
			return 0;
	}
	
	/**
	 * TODO
	 */
	public int performTurn() {
		updatePlayers();
		System.out.println("#######################################");
		int advanceOrTakeRespond;
		Interaction.cleanConsole();
		ViewTimeboard.display(timeboard);
		ViewCirclePatch.displayNextPaches(circlePatch.nextPatches());
		System.out.println(players[0]);
		if (players[0].realPlayer()) {
			advanceOrTakeRespond = Interaction.advanceOrTake();
			return detPLayerChoice(advanceOrTakeRespond);
		}
		else {
//			runAutoma(players[1].currentPosition() - players[0].currentPosition());
		}
		return 0;
	}
	
	/**
	 * Perform every operation to buy an item
	 */
	public int buy() {
		Patch currPatch;
		int[] coordinate = new int[2];
		int respondChosePatch;	// The chosen patch.
		char respondAction;		// The chosen action (Flip, rotate, coordinate).
		ViewCirclePatch.displayNextPaches(circlePatch.nextPatches());
		
		respondChosePatch = Interaction.chosePatch(); 
		currPatch = circlePatch.nextPatches().get(respondChosePatch);
		if (players[0].checkMoney(currPatch.price()) && !players[0].modeGlouton()) {	//checking if the player have money
			players[0].patchChose(currPatch);
			do {
//				System.out.println(players[0].quiltboard());
				ViewQuiltboard.display(players[0].quiltboard());
				System.out.println(players[0].patch());
				respondAction = Interaction.choseCoordinates(coordinate);
				if(respondAction == 'L' || respondAction == 'R') {
					System.out.println("ROTATE !");
					players[0].patch().rotate((respondAction == 'R'));
				}
				else if(respondAction == 'F') {
					players[0].patch().flip();
				}
				else if(respondAction == 'Q') {
					return 1;
				}
//				System.out.println(players[0].quiltboard());
				ViewQuiltboard.display(players[0].quiltboard());
				if(respondAction == 'C'){
					if(players[0].checkPutPatch(coordinate[0], coordinate[1])) {
						break;
					}
				}
			}while(true);
			players[0].placePatchs(coordinate[0], coordinate[1]);
			players[0].buyPatches(currPatch);
			timeboard.checkCurrentPostionPlayer(players[0],players[0].currentPosition(),
					players[0].currentPosition() + currPatch.movement());
			players[0].movePawn(currPatch.movement());
			timeboard.checkWhoIsTurn();	// check who is next and change the value of player turn.
			circlePatch.swapPatch(respondChosePatch);
		}
		else if (!players[0].modeGlouton()) {
			System.out.println("Vous n'avez pas assez d'argent");
		}
		else {
			players[0].buyPatches(currPatch);
			timeboard.checkCurrentPostionPlayer(players[0],players[0].currentPosition(),
					players[0].currentPosition() + currPatch.movement());
			players[0].movePawn(currPatch.movement());
			timeboard.checkWhoIsTurn();	// check who is next and change the value of player turn.
			circlePatch.swapPatch(respondChosePatch);
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
		if(game.init() == 1) {
			game.runGame();
		}
		else {
			InteractionGraphique.run(game);
		}
	}
}
