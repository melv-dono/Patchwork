package View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Interaction {
	private static final String commandBuy = "BUY PATCH : press 'b' and ENTER";
	private static final String commandAdvences = "ADVANCES : press 'a' and ENTER";
	private static final String commandQuiltBoard = "SHOW QUILTBOARD : press 'q' and ENTER";
	private static final String commandCirclePatch = "SHOW CIRCLE PACTH : press 'c' and ENTER";
	private static final String commandTimeboard = "SHOW TIME BOARD : press 't' and ENTER";
	private static final String error = "Error : to make a choice you must enter one of the commands bellow";
	
	private static final List<String> commandList = Arrays.asList(commandBuy, commandAdvences,
			commandQuiltBoard, commandCirclePatch, commandTimeboard);
	public static int advanceOrTake()
	{
		try ( Scanner scanner = new Scanner( System.in ) ) {
            do {
            	
            	commandList.stream().forEach(commandLine -> System.out.println(commandLine));
            	String command = scanner.nextLine();
            	if(command.length() == 1)
            	{
            		switch (command.charAt(0)) {
					case 'b':
						
						break;
					case 'a':
						break;
					case 'q':
						break;
					case 'c':
						break;
					case 't':
						break;
					default:
						Interaction.cleanConsole();
						System.out.println(error);
						break;
					}
            	}
            }
            while(true);
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
