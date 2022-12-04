package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Interaction {
	private static final String commandBuy = "BUY PATCH : press 'B' and ENTER";
	private static final String commandAdvences = "ADVANCES : press 'A' and ENTER";
	private static final String commandQuiltBoard = "SHOW QUILTBOARD : press 'Q' and ENTER";
	private static final String commandCirclePatch = "SHOW CIRCLE PACTH : press 'C' and ENTER";
	private static final String commandTimeboard = "SHOW TIME BOARD : press 'T' and ENTER";
	private static final String error = "Error : to make a choice you must enter one of the commands bellow";
	
	private static final List<String> commandList = Arrays.asList(commandBuy, commandAdvences,
			commandQuiltBoard, commandCirclePatch, commandTimeboard);
	public static int advanceOrTake(){
		try ( Scanner scanner = new Scanner( System.in )) {
        	commandList.stream().forEach(commandLine -> System.out.println(commandLine));
        	String command = scanner.nextLine();
        	command.toUpperCase(Locale.ROOT);
        	if(command.length() == 1){
        		return switch ((command.charAt(0))) {
				case 'B' -> 1; // B for buy
				case 'A' -> 2; // A for progress 
				case 'Q' -> 3; // Q to print the quilt board
				case 'C' -> 4; // C to print circle patch
				case 'T' -> 5; // T to print time Board
				case 'E' -> 6; // E to Exit.
				default -> 7;
        		};
        	}
        	else {return 7;}
        }
	}
	public static int chosePatch()
	{
		return 0;
	}
	public static int[] choseCoordinates()
	{
		int[] coordinate = new int[2];
		return coordinate;
	}

	public static void cleanConsole() {
		// Command to clean console. 
        System.out.print("\033[H\033[2J");
        // empty the last octet.
        System.out.flush();
	}
}
