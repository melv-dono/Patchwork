package Model;

import View.Interaction;

// TODO this class is here to do all kind of tests

public class Test {

	public static void main(String[] args) {
		var firstPlayer = new Player("first");
		var secondPlayer = new Player("second");
		var timeboard = new Timeboard(firstPlayer, secondPlayer);
		var circlePatch = new CirclePatch();
		timeboard.initMagicCases(false);
		// In function:
		Player currentPlayer;
		
		int indexPatch = 0;
		int[] coordinates = new int[2];
		int resultPlacement = 1;
		if(timeboard.turn() == firstPlayer.name())
		{
			currentPlayer = firstPlayer;
		}
		else
		{
			currentPlayer = secondPlayer;
		}
		while(currentPlayer.turn())
		{
			if(Interaction.advanceOrTake(null) == 0)
			{
				indexPatch = Interaction.chosePatch();
				currentPlayer.patchChose(circlePatch.selectNextPatch(indexPatch));
				do {
					//coordinates = Interaction.choseCoordinates();
					//resultPlacement = currentPlayer.checkPlacePatch(coordinates[0], coordinates[1]);
					// C'ant place patch. 
					if(resultPlacement == -1)
					{
						break;
					}
				} while (resultPlacement == 1);
				// give new choice placement not valid.
				if(resultPlacement == -1)
				{
					continue;
				}
				// placement valid, change the player  attribute (button, pawn, tour) 
				else if(resultPlacement == 0)
				{
					currentPlayer.placePatchs(coordinates[0], coordinates[1]);
					int start = currentPlayer.currentPosition(); // The start of the movement.
					int end = currentPlayer.patch().movement(); // The end of the movement.
					//currentPlayer.buyPatches();	// Subtract the button of the player suits of patch.
					timeboard.checkCurrentPostionPlayer(currentPlayer, start, end); // Performs the different action of each case traveled.
					currentPlayer.movePawn(currentPlayer.patch().movement());	// move the pawn.
					circlePatch.swapPatch(indexPatch);
				}
				
			}
			else
			{
				timeboard.advancePlayer(currentPlayer, timeboard.oppenentPlayer(currentPlayer));
			}
		}
		System.out.println(timeboard);
		/*
		int[][] t = {{1,1,1,1},{0,0,0,1}};
		Label l = new Label(1, 3, 4);
		Patch p = new Patch(l, t);
		var quiltboard = new Quiltboard();
		quiltboard.putPatch(p, 5, 0);
		p.rotate(1);
		quiltboard.putPatch(p, 1, 3);
		p.flip();
		quiltboard.putPatch(p, 1, 1);
		System.out.println(quiltboard);
		System.out.println(p);
		*/
	}

}
