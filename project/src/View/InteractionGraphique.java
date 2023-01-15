package View;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.List;

import Controller.Game;
import Model.Patch;
import Model.Player;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;

public class InteractionGraphique {
	private static boolean actionPatch(ApplicationContext context, Player currentPlayer, Graphique graph, Event event, int sizeCase) {
		if (event == null) {return true;}
        Action action = event.getAction();
        if (action == Action.KEY_PRESSED) {
        	if(event.getKey() == KeyboardKey.LEFT) {
				currentPlayer.patch().rotate(false);
      	  	    graph.displayPlacePatch(context, sizeCase * 13, sizeCase * 12);
      	  	    graph.displayPatch(context, currentPlayer.patch(), sizeCase * 17, sizeCase * 14, Color.cyan);
		     }
        	else if(event.getKey() == KeyboardKey.RIGHT) {
				currentPlayer.patch().rotate(true);
      	  	    graph.displayPlacePatch(context, sizeCase * 13, sizeCase * 12);
      	  	    graph.displayPatch(context, currentPlayer.patch(), sizeCase * 17, sizeCase * 14, Color.cyan);
		     }
        	else if(event.getKey() == KeyboardKey.UP) {
				currentPlayer.patch().flip();
      	  	    graph.displayPlacePatch(context, sizeCase * 13, sizeCase * 12);
      	  	    graph.displayPatch(context, currentPlayer.patch(), sizeCase * 17, sizeCase * 14, Color.cyan);
		     }
        	else if(exitGame(context, event)) {
			   	  context.exit(0);
			   	  return false;
			    }
        }
        return true;
	}
	
	private static IntPair posePatch( Player currentPlayer, Event event, int sizeCase) {
		int i, j;
        Action action = event.getAction();
        Point2D.Float location = event.getLocation();
        if(action == Action.POINTER_DOWN) {              
        	int x_s, y_s, x_e, y_e;
        	if(currentPlayer.name() == "first") {
        		 x_s = sizeCase * 3;
        		 y_s = sizeCase * 3;
        		 x_e = sizeCase * 12;
        		 y_e = sizeCase * 12;
        	}
    		else {
    			 x_s = sizeCase * (3 + 18);
    			 y_s = sizeCase * 3;
        		 x_e = sizeCase * (3 + 18 + 9);
        		 y_e = sizeCase * 12;
    		}
    		if(location.x >= x_s && location.x <= x_e &&
				location.y >= y_s && location.y <= y_e) {
    			 j = (int) ((location.x - x_s) / sizeCase);
    			 i = (int) ((location.y - y_s) / sizeCase);
				if(currentPlayer.checkPutPatch(i, j)) {
					return new IntPair(i, j);
				}		
        	}
        }
        return null;
	}

	private static void buyValid(ApplicationContext context, Player currentPlayer, Patch currPatch, Game game, int i, int j, int sizeCase, int c) {
		currentPlayer.placePatchs(i, j);
		currentPlayer.buyPatches(currPatch);
		game.timeboard().checkCurrentPostionPlayer(currentPlayer,currentPlayer.currentPosition(),
				currentPlayer.currentPosition() + currPatch.movement(), context, sizeCase * 13, sizeCase * 12, sizeCase);
		currentPlayer.movePawn(currPatch.movement());
		game.timeboard().checkWhoIsTurn();	// check who is next and change the value of player turn.
		game.circlePatch().swapPatch(c);
	}
	
	private static boolean exitGame(ApplicationContext context, Event event) {
        if(event.getKey() == KeyboardKey.E) {
    		System.out.println("E");
		   	  context.exit(0);
		   	  return true;
	    }
        return false;
	}
	
	private static boolean chosePatch(ApplicationContext context, Player currentPlayer, Game game, Event event,
			Graphique graph, int sizeCase, int c) {
		int sizeC = game.circlePatch().circlePatch().size();
		IntPair coor;
		Patch currPatch = game.circlePatch().nextPatches().get((c == sizeC) ? 0 : c);
	  	    graph.displayPlacePatch(context, sizeCase * 13, sizeCase * 12);

		if (currentPlayer.checkMoney(currPatch.price())) {	//checking if the player have money
			currentPlayer.patchChose(currPatch);
  	  	    graph.displayPatch(context, currentPlayer.patch(), sizeCase * 17, sizeCase * 14, Color.cyan);
			// Placement patch
			for(;;) {
				event = context.pollEvent();
    	        if (event == null) {continue;}
    	        InteractionGraphique.actionPatch(context, currentPlayer, graph, event, sizeCase);
    	         coor = InteractionGraphique.posePatch( currentPlayer, event, sizeCase);
    	        if(coor != null) {
    	        	break;
    	        }
			}
			InteractionGraphique.buyValid(context, currentPlayer, currPatch, game, coor.i(), coor.j(), sizeCase, c);
			return true;
		}
		else { graph.displayNoEnoughtMoney(context, sizeCase * 13, sizeCase * 12);}
		return false;
	}
	
	private static boolean buyPatch(ApplicationContext context, Event event, Game game, List<Color> colorlist,
			Graphique graph, Player currentPlayer, float width, float height, int sizeCase) {
		int c = 0;
		Action action;
  	    // Chose Patch function
  	    for(;;) {
  	    	event = context.pollEvent();
	        if (event == null) continue;
	        action = event.getAction();
	        if (action == Action.KEY_PRESSED) {
	        	if(event.getKey() == KeyboardKey.LEFT) {
	        		if(c > 0) c--;
	      	  	    graph.displayGame(context, game, colorlist, graph, width, height, c);
			     }
	        	else if(event.getKey() == KeyboardKey.RIGHT) {
	        		if(c < 2) c++;
	      	  	    graph.displayGame(context, game, colorlist, graph, width, height, c);
			     }
	        	else if(event.getKey() == KeyboardKey.SPACE) {
	        		if(InteractionGraphique.chosePatch(context, currentPlayer, game, event,graph, sizeCase, c)) {break;}
			    }
	        	else if(InteractionGraphique.exitGame(context, event)){
	        		return false;
        	}}}
  	    return true;
	}
	
	private static boolean performTurnAux(ApplicationContext context, Game game, Graphique graph, List<Color> colorlist,
			Player currentPlayer, Player opponentPlayer, float width, float height, int sizeCase) {
		for(;;) {
	    	Event event = context.pollEvent();
	        if (event == null) continue;
	        Action action = event.getAction();
	        if (action == Action.KEY_PRESSED) {
		        if(event.getKey() == KeyboardKey.A) {
					game.timeboard().advancePlayer(currentPlayer, opponentPlayer, context, sizeCase );
			    	break;
			     }
		        else if(event.getKey() == KeyboardKey.E) {
			   	  context.exit(0);
			   	  break;
			    }
		        else if(event.getKey() == KeyboardKey.B) {
		      	    graph.displayChosePatch(context, sizeCase * 13, sizeCase * 12);
		      	    if(InteractionGraphique.buyPatch(context, event, game, colorlist, graph, currentPlayer, width, height, sizeCase) == true)
		      	    	break;
		      	    else { return false;}
	      	    }}}
		return true;
	}
	
	private static boolean performTurn(ApplicationContext context, Game game, Graphique graph, List<Color> colorlist,
			float width, float height, int sizeCase) {
		
		for(;true;) {
	     	Player currentPlayer = game.timeboard().currentPlayer();
	  		Player opponentPlayer = game.timeboard().oppenentPlayer(currentPlayer);
	  		graph.displayClean(context, width, height);
	  	    graph.displayGame(context, game, colorlist, graph, width, height, 0);
	  	    graph.displayAdvanceOrTake(context, currentPlayer, sizeCase * 13, sizeCase * 12);
		    // Advance or take function
	  	    if(!InteractionGraphique.performTurnAux(context, game, graph, colorlist, currentPlayer, opponentPlayer, width, height, sizeCase)) {break;}
	      }
		return true;
	}
	public static void run() {
		Game game = new Game();
		game.init(true);
	    var colorlist = Graphique.colorList();
	    Application.run(Color.DARK_GRAY, context -> {
	      // get the size of the screen
	      ScreenInfo screenInfo = context.getScreenInfo();
	      float width = screenInfo.getWidth();
	      float height = screenInfo.getHeight();
	      int sizeCase = (int)height / 9 - 50;
	      Graphique graph = new Graphique(sizeCase, 30);
	      InteractionGraphique.performTurn(context, game, graph, colorlist, width, height, sizeCase);
	    });
	    return;
		
	}
}
