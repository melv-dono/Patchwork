package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import Controller.Game;
import Model.Coordinate;

public class Interaction {
	private static final String commandBuy = "BUY PATCH : press 'B' and ENTER";
	private static final String commandQuit = "QUIT GAME : press 'Q' and ENTER";
	private static final String commandAdvences = "ADVANCES : press 'A' and ENTER";
	private static final String commandQuiltBoard = "SHOW QUILTBOARD : press 'Q' and ENTER";
	private static final String commandCirclePatch = "SHOW CIRCLE PACTH : press 'C' and ENTER";
	private static final String commandTimeboard = "SHOW TIME BOARD : press 'T' and ENTER";
	private static final String commandRotateR = "RIGHT ROTATE PATH : press 'R' and ENTER";
	private static final String commandRotateL = "LEFT ROTATE PATH : press 'L' and ENTER";
	private static final String commandFlip = "FLIP THE PATCH : press 'F' and ENTER";
	private static final String commandCoor = "CHOSE COORDINATE : i j and ENTER (ex : 0 1)";
	private static final String error = "Error : to make a choice you must enter one of the commands bellow";
	private static final List<String> commandPatch = Arrays.asList(commandRotateL,commandRotateR, commandFlip, commandCoor, commandQuit);
	private static final List<String> commandListAction = Arrays.asList(commandBuy, commandAdvences, commandQuit);

	private static final Scanner scanner = new Scanner(System.in);
	
	private static boolean checkVersion(int version) {
		if (version == 1 || version == 2 || version == 3) {
			return true;
		}
		else {
			return false;
		}
	}
	

	public static int initGame() {
		System.out.println("Welcome in Patchwork ! \nWhich version of the game do want to play: \n1 : simple (terminal) \n2 : complet (terminal) \n3 : graphic (non on terminal)");
		String command;
		int choice = 0;
		do {
			try {
				command = scanner.nextLine();
				choice = Integer.parseInt(Character.toString(command.charAt(0)));
			}
			catch(NumberFormatException e) {
				System.err.println("Please enter a valid entry");
				choice = 0;
			}
		}while (!checkVersion(choice));
		
		return choice;		
	}
	
	private static boolean checkChoice(int answer) {
		if (answer == 1 || answer == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static int checkModeGlouton() {
		System.out.println("For this version you can choose to active the gluton mode. \n It will automaticly place your patch for both of you. \nDo you want to use it:\n1 : yes\n2 : no");
		int choice = 0;
		String command;
		do {
			try {
				command = scanner.nextLine();
				choice = Integer.parseInt(Character.toString(command.charAt(0)));
			}
			catch(NumberFormatException e) {
				System.err.println("Please enter a valid entry");
				choice = 0;
			}
		}while(!checkChoice(choice));
		return choice;
	}
	
	public static int advanceOrTake(){
		String command;
		int respond = 4;
		commandListAction.stream().forEach(commandLine -> System.out.println(commandLine));
		do {
        	command = scanner.nextLine();
        	command.toUpperCase(Locale.ROOT);
        	if(command.length() == 1){
        		respond = switch ((command.charAt(0))) {
				case 'B' -> 1; // B for buy
				case 'A' -> 2; // A for progress 
				case 'Q' -> 3; // E to Exit.
				default -> 4;
        		};
        	}
    		if(respond == 4) { System.out.println(error);};
    	} while(respond == 4);
		
		return respond;
    }
	
	/**
	 * Ask the user which version of the game he wants to play
	 */
	//TODO
	public static int choosePhase() {
		return 0;
	}
	
	/**
	 * Asks the user to choose a patch
	 * @return
	 */
	//TODO
	public static int chosePatch()	{
		int respond;
		System.out.println("Enter the number of the patch that you want to buy :");
		do {//selection par le joueur
			respond = Integer.parseInt(scanner.nextLine());
		}while (respond < 1 || respond > 3);		
		return (respond - 1);
	}
	//AJOUT SUREMENT À REFACTOR DANS UNE AUTRE CLASSE
	private static int charToInt(char c) {
		int i;
		try {
			i = Integer.parseInt(Character.toString(c));
			return i;
		}
		catch (NumberFormatException nfe) {
			System.err.println("Please enter a valid number.");
			return -1;
		}
	}
	// REFACTOR surement que la partie de conversion
	
	private static int fillArray(int[] coordinate, char a, char b) {
		int row = charToInt(a);
		int column = charToInt(b);
		
		if (row == -1 || column == -1) {
			return -1;
		}
		else {
			coordinate[0] = row;
			coordinate[1] = column;
			return 1;	
		}
	}	
	
	private static char patchCommand(char c) {
		return switch (c) {
			case 'L' -> 'L'; 
			case 'R' -> 'R'; 
			case 'F' -> 'F';
			case 'Q' -> 'Q';
			default -> 'E';
		};
	}
	//FIN AJOUT
	
	/**
	 * 
	 * @param coordinate
	 * @return
	 */
	public static char choseCoordinates(int[] coordinate)	{
		char respond = 'E';
		String command;
		
		Objects.requireNonNull(coordinate);

		commandPatch.stream().forEach(commandLine -> System.out.println(commandLine));
		while(respond == 'E') {
			command = scanner.nextLine();
			command = command.toUpperCase(Locale.ROOT);
        	switch(command.length()) {
        		case 1:
        			respond = patchCommand(command.charAt(0));
        			break;
        		case 3:
        			if((command.charAt(0) != ' ') && (command.charAt(2) != ' ')){
        				respond = (fillArray(coordinate, command.charAt(0), command.charAt(2)) == 1) ? 'C': 'E';
        			}
        			break;
        		default:
        			System.out.println(error);
        			break;
        	}
		}
		return respond;    
	}
	
	/**
	 * 
	 * @param coordinate
	 * @return
	 */
	public static boolean checkCoordinates(int[] coordinate) {
		Objects.requireNonNull(coordinate);
		if((coordinate[0] < 0 || coordinate[0] > 7)
				|| ((coordinate[1] < 0) || coordinate[1] > 7)) {
			return false;
		}
		return true;
	}

	/**
	 * Clean the console
	 */
	public static void cleanConsole() {
		// Command to clean console. 
        System.out.print("\033[H\033[2J");
        // empty the last octet.
        System.out.flush();
	}
}
