package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Controller.Game;
import Model.Coordinate;

public class Interaction {
	private static final String commandBuy = "BUY PATCH : press 'B' and ENTER";
	private static final String commandAdvences = "ADVANCES : press 'A' and ENTER";
	private static final String commandQuiltBoard = "SHOW QUILTBOARD : press 'Q' and ENTER";
	private static final String commandCirclePatch = "SHOW CIRCLE PACTH : press 'C' and ENTER";
	private static final String commandTimeboard = "SHOW TIME BOARD : press 'T' and ENTER";
	private static final String commandRotateR = "RIGHT ROTATE PATH : press 'R' and ENTER";
	private static final String commandRotateL = "LEFT ROTATE PATH : press 'L' and ENTER";
	private static final String commandFlip = "FLIP THE PATCH : press 'F' and ENTER";
	private static final String commandCoor = "CHOSE COORDINATE : i j and ENTER (ex : 0 1)";
	private static final String error = "Error : to make a choice you must enter one of the commands bellow";
	private static final List<String> commandPatch = Arrays.asList(commandRotateL,commandRotateR, commandFlip);
	private static final List<String> commandList = Arrays.asList(commandBuy, commandAdvences,
			commandQuiltBoard, commandCirclePatch, commandTimeboard);


	public static int advanceOrTake(){
		try ( Scanner scanner = new Scanner( System.in )) {
			while(true) {
	        	commandList.stream().forEach(commandLine -> System.out.println(commandLine));
	        	String command = scanner.nextLine();
	        	command.toUpperCase(Locale.ROOT);
	        	if(command.length() == 1){
	        		int respond = switch ((command.charAt(0))) {
					case 'B' -> 1; // B for buy
					case 'A' -> 2; // A for progress 
					case 'Q' -> 3; // Q to print the quilt board
					case 'C' -> 4; // C to print circle patch
					case 'T' -> 5; // T to print time Board
					case 'E' -> 6; // E to Exit.
					default -> 7;
	        		};
	        		if(respond != 7) {return respond;};
	        		System.out.println(error);
	        	}
			}
        }
	}
	
	/**
	 * Afficher les actions possible du style rotate flip poser son patch
	 */
	public void actionPossible() {
		
	}
	
	/**
	 * Ask the user which version of the game he wants to play
	 */
	public static int choosePhase() {
		return 0;
	}
	/**
	 * 
	 * @return
	 */
	public static int chosePatch()
	{
		return 0;
	}
	public static String choseCoordinates(int[] coordinate)
	{
		try ( Scanner scanner = new Scanner( System.in )) {
			while(true) {
				System.out.println(commandPatch);
				String command = scanner.nextLine();
	        	command.toUpperCase(Locale.ROOT);
	        	if(command.length() == 1){
	        		String respond = switch ((command.charAt(0))) {
					case 'L' -> "L"; 
					case 'R' -> "R"; 
					case 'F' -> "F";
					default -> "Q";
	        		};
	        		return respond;
	        	}
	        	else if(command.length() == 3) {
	        		// Warning
	        		if((Character.toString(command.charAt(0)) != " ") &&
	        				(Character.toString(command.charAt(2)) != " ")){
	        			coordinate[0] = Integer.parseInt(Character.toString(command.charAt(0)));
	        			coordinate[1] = Integer.parseInt(Character.toString(command.charAt(2)));
	        		}
	        	}
			}
        }
	}
	
	public static boolean checkCoordinates(int[] coordinate)
	{
		if((coordinate[0] < 0 || coordinate[0] > 7)
				|| ((coordinate[1] < 0) || coordinate[1] > 7)) {
			return false;
		}
		return true;
	}

	public static void cleanConsole() {
		// Command to clean console. 
        System.out.print("\033[H\033[2J");
        // empty the last octet.
        System.out.flush();
	}
}
