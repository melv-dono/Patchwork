package Controller;

import java.io.IOException;
import java.util.Objects;

import Model.CirclePatch;
import Model.Player;
import Model.Timeboard;

public class Game {
	private Timeboard timeboard;
	private CirclePatch circlePatch;
	
	
	/**
	 * 
	 * @param specialPatch
	 */
	public Game() {
		// Initialisaton de tous les objets du model
		this.timeboard = new Timeboard(new Player("first"), new Player("second"));
		this.circlePatch = new CirclePatch();	
	}
	
	/**
	 * 
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
	 * 
	 */
	public void runGame() {
		// Execute une boucle while sur perform turn tant qu'il n'y a pas de condition d'arret
		// quitter la partie ou partie terminer
	}
	
	
	
	/**
	 * 
	 */
	public void performTurn() {
		// 3 options : Achat, Avancer, afficher menu
		
		//On recup l'input du joueur et tant palyer.IsTurn == true on continue
		
		// Lance le menu 
		
			// IF 'B' On lance la fonction Achat
		
			// Else if 'A' On lance la fonction Avancer
		
	}
	
	
	/**
	 * 
	 */
	public void buy() {
		// possiblité d'afficher le menu
		// possibilité de quitter la fonciton
		// ne pas oublier de changer le tour du joueur une fois la fonction terminée		
	}
	
	/**
	 * 
	 */
	public void moveForward(Player currentPlayer) {
		timeboard.advancePlayer(currentPlayer, timeboard.oppenentPlayer(currentPlayer));
	}
	
	/**
	 * 
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
