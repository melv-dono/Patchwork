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
	public static int advanceOrTake(){
		int respond = 4;
		commandListAction.stream().forEach(commandLine -> System.out.println(commandLine));
		do {
        	String command = scanner.nextLine();
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
		}while (respond < 0 || respond > 2);		
		return respond;
	}
	
	/**
	 * 
	 * @param coordinate
	 * @return
	 */
	public static char choseCoordinates(int[] coordinate)	{
		
		Objects.requireNonNull(coordinate);
		char respond = 'E';
		commandPatch.stream().forEach(commandLine -> System.out.println(commandLine));
		while(respond == 'E') {
			String command = scanner.nextLine();
        	command.toUpperCase(Locale.ROOT);
        	if(command.length() == 1){
        		respond = switch ((command.charAt(0))) {
				case 'L' -> 'L'; 
				case 'R' -> 'R'; 
				case 'F' -> 'F';
				case 'Q' -> 'Q';
				default -> 'E';
        		};
        	}
        	else if(command.length() == 3) {
        		if((Character.toString(command.charAt(0)) != " ") &&
        				(Character.toString(command.charAt(2)) != " ")){
        			coordinate[0] = Integer.parseInt(Character.toString(command.charAt(0)));
        			coordinate[1] = Integer.parseInt(Character.toString(command.charAt(2)));
        			respond ='C';
        		}
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
