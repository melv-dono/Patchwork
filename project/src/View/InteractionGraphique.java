package View;

import java.awt.Color;
import java.awt.geom.Point2D;

import Model.Player;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.KeyboardKey;
import fr.umlv.zen5.Event.Action;

public class InteractionGraphique {
	public static int actionPatch(ApplicationContext context, Player currentPlayer, Graphique graph, Event event, int sizeCase) {
		if (event == null) {return 0;}
        Action action = event.getAction();
        Point2D.Float location = event.getLocation();
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
        	else if(event.getKey() == KeyboardKey.E) {
        		System.out.println("E");
			   	  context.exit(0);
			   	  return -1;
			    }
        }
        return 1;
	}
}
