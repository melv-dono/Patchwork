package View;

import java.util.Scanner;

public class Interaction {
	public static int advanceOrTake()
	{
		try ( Scanner scanner = new Scanner( System.in ) ) {
            
            while( true ) {
                System.out.print( "" );
                String login = scanner.nextLine();
                
                System.out.print( "Enter your password: " );
                String password = scanner.nextLine();
                
                if ( login.equals( "Bond" ) && password.equals( "007" ) ) {
                    break;
                }
            }
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

}
