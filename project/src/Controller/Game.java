package Controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import Model.CirclePatch;
import Model.Patch;
import Model.Player;
import Model.Timeboard;
import View.ViewCirclePatch;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	private ViewCirclePatch viewCirclePatch;
	private Scanner sc;
	
	public Game() {
		// Initialisaton de tous les objets du model
		this.timeboard = new Timeboard(new Player("first"), new Player("second"));
		this.circlePatch = new CirclePatch();
		viewCirclePatch = new ViewCirclePatch();
		sc = new Scanner(System.in);
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
		String file = specialPatch ? "phase2.txt" : "phase1.txt";
		try {
			
			circlePatch.initCirclePatch(file, 2, specialPatch ? 1 : 20); 				
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
		// Execute une boucle while sur perform turn tant qu'il n'y a pas de condition d'arret
		// quitter la partie ou partie terminer
	}
	
	
	
	/**
	 * TODO
	 */
	public void performTurn() {
		// 3 options : Achat, Avancer, afficher menu
		
		//On recup l'input du joueur et tant palyer.IsTurn == true on continue
		
		// Lance le menu 
		
			// IF 'B' On lance la fonction Achat
		
			// Else if 'A' On lance la fonction Avancer
		
	}
	
	
	/**
	 * Perform every operation to buy an item
	 */
	public void buy() {
		// possiblité d'afficher le menu
		// possibilité de quitter la fonciton
		// ne pas oublier de changer le tour du joueur une fois la fonction terminée	
		int rep;
		Patch currPatch;
		
		//listing des patchs achetables
		viewCirclePatch.displayNextPaches(circlePatch.nextPatches());
	
		do {//selection par le joueur
			rep = Integer.parseInt(sc.nextLine());
		}while (rep < 0 || rep > 3); 
		
		currPatch = circlePatch.nextPatches().get(rep);
		
		//checking if the player have money
		if (timeboard.currentPlayer().checkMoney(currPatch.price())) {
			
			// on renvoie l'indice, on bouge le token et on supp le patch de la liste
			
		
			// ce patch est sauvegardé et on le donne au joueur pour qu'il réalise 2 opérations
		
				// placer le patch ATTENTION BIG TROUBLE
		
				// payer le patch			
			
			// Once token place we move the neutral token
			circlePatch.swapPatch(rep);
			
			// We substract money from player's bank of buttons
			timeboard.currentPlayer().buyPatches(currPatch);
		}
		else {
			System.out.println("Vous n'avez pas assez d'argent");
		}
	}
	
	/**
	 * TODO
	 */
	public void moveForward(Player currentPlayer) {
		timeboard.advancePlayer(currentPlayer, timeboard.oppenentPlayer(currentPlayer));
	}
	
	/**
	 * TODO
	 */
	public void menu() {
		
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
}
