package View;

import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Controller.Game;
import Model.Case;
import Model.CirclePatch;
import Model.Label;
import Model.Patch;
import Model.Player;
import Model.Quiltboard;
import Model.Timeboard;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ApplicationContext;
import fr.umlv.zen5.Event;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import java.util.Collections;
import java.util.HashMap;

public record Graphique (int sizeCase, int reSize){
	
	public static List<Color> colorList() {
		var list = new ArrayList<Color>();
		int R, G, B;
		Color c;
		R = 0;
		G = 50;
		B = 100;
		for(int i = 1; i < 33; i++) {
			if(R + 15 <= 255) {
				R += 15;
			}
			else if (G + 15 <= 255) {
				G += 15;
			}
			else if (B + 15 <= 255) {
				B += 15;
			}
	        c = new Color(R, G, B);
	        list.add(c);
		}
        Collections.shuffle(list);
		list.add(0, Color.BLACK);
		return list;
	}
	
	public void displayPatch(ApplicationContext context, Patch patch, int x, int y) {
		context.renderFrame(graphics -> {
		int x_tmp, y_tmp, key;
		y_tmp = y;
		for (int i = 0; i < patch.dimension().length; i++) {
			x_tmp = x;
			for (int j = 0; j < patch.dimension()[0].length; j++) {
				if(patch.dimension()[i][j] == 1) {
					graphics.setColor(Color.LIGHT_GRAY);
					graphics.fill(new  Rectangle2D.Float(x_tmp, y_tmp, sizeCase - reSize, sizeCase - reSize));
				}
				x_tmp += sizeCase - (reSize - 1);
			}
			y_tmp += sizeCase - (reSize - 1);
		}
		});
	}
	
	public void displayPlayer(ApplicationContext context, Player p,int X, int Y) {
		context.renderFrame(graphics -> {
			graphics.setColor((p.name() == "first") ? Color.red : Color.blue);
			graphics.fill(new  Rectangle2D.Float(X, Y, sizeCase * 9, sizeCase *4));
			graphics.setColor(Color.white);
			graphics.drawString("Player : " + p.name() , X, Y + 25 );
			graphics.drawString("Number of button : " + p.buttons() , X, Y + 50);
			graphics.drawString("Get special tile : " + (p.specialTile() ? "yes" : "no") , X, Y + 75);
			graphics.drawString("Patch choose :" , X, Y + 100);
			if(p.patch() != null) { displayPatch(context, p.patch(), X + 20 , Y + 20);}
			});
	}
	
	public void displayTimeAux(ApplicationContext context, Timeboard t,int X, int Y, int key) {
		context.renderFrame(graphics -> {
			Case value = t.cases().get(key);
			if((t.first().currentPosition() == t.second().currentPosition())
					&& (key == t.first().currentPosition())) {
					graphics.setColor(Color.GRAY);
					graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase - 1, sizeCase - 1));
					if(t.first().turn())
						graphics.setColor(Color.RED);
					else
						graphics.setColor(Color.BLUE);
					graphics.fill(new  Ellipse2D.Float(X + 5, Y + 5, sizeCase - 10, sizeCase - 10));

			}
			else if(key == t.first().currentPosition()) 	{
				graphics.setColor(Color.GRAY);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase - 1, sizeCase - 1));
				graphics.setColor(Color.RED);
				graphics.fill(new  Ellipse2D.Float(X + 5, Y + 5, sizeCase - 10, sizeCase - 10));
				
			}
			else if(key == t.second().currentPosition()) {
				graphics.setColor(Color.GRAY);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase, sizeCase));
				graphics.setColor(Color.BLUE);
				graphics.fill(new  Ellipse2D.Float(X + 5, Y + 5, sizeCase - 10, sizeCase - 10));
			}
			else if(value == Case.NEUTRAL) {
				graphics.setColor(Color.GRAY);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase - 1, sizeCase - 1));
			}
			else if(value == Case.BUTTON) {
				graphics.setColor(Color.GRAY);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase - 1, sizeCase - 1));
				graphics.setColor(Color.WHITE);
				graphics.fill(new  Ellipse2D.Float(X + 5, Y + 5, sizeCase - 10, sizeCase - 10));
			}
			else if(value == Case.SPECICAL_PATCH) {
				graphics.setColor(Color.GRAY);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase, sizeCase));
				graphics.setColor(Color.CYAN);
				graphics.fill(new  Rectangle2D.Float(X, Y , sizeCase, sizeCase));
			}
		});
	}
	
	public void displayTimeboard(ApplicationContext context, Timeboard t) {
		var cases_tmp = t.cases();
		int X = Timeboard.COL + 1;
		int Y = Timeboard.ROW + 1;
		int key = Timeboard.NBR_CASE;
		int ref_x = sizeCase * (3 + 9 + 4);
		int ref_y = sizeCase * (3 + 4);
		int x = 0;
	    int y = 0;
	    int dx = 0;
	    int dy = -1;
	    for (int i = 0; i < Math.max(X, Y) * Math.max(X, Y); i++) {
	        if ((-X/2 < x && x <= X/2) && (-Y/2 < y && y <= Y/2)) {
	        	System.out.println("x : " + x + "y :" + y);
	            displayTimeAux(context, t, x * sizeCase + ref_x, y * sizeCase +ref_y, key);
	            key--;
	        }
	        if (x == y || (x < 0 && x == -y) || (x > 0 && x == 1-y)) {
	            int temp = dx;
	            dx = -dy;
	            dy = temp;
	        }
	        x += dx;
	        y += dy;
	    }
	}
	
	public void displayQuiltboard(ApplicationContext context, Quiltboard q, int x, int y, List<Color> listColor) {
		Objects.requireNonNull(q);				
		context.renderFrame(graphics -> {
		int x_tmp, y_tmp, key;
		Color c;
		y_tmp = y;
		for (int i = 0; i < q.rows(); i++) {
			x_tmp = x;
			for (int j = 0; j < q.cols(); j++) {
				key = q.dimension()[i][j];
				c = listColor.get(key);
				graphics.setColor(c);
				graphics.fill(new  Rectangle2D.Float(x_tmp, y_tmp, sizeCase - 1, sizeCase - 1));
				x_tmp += sizeCase;
			}
			y_tmp += sizeCase;
		}
		});
		
	}
	
	public void displayInteraction(ApplicationContext context, Player p,int X, int Y) {
		context.renderFrame(graphics -> {
		});
	}
	
	public void displayCirclepatch(ApplicationContext context, CirclePatch c, int x, int y, float widht_p, float height_p) {
		Objects.requireNonNull(c);				
		context.renderFrame(graphics -> {
		Patch tmp;
		int sizeCasetmp = sizeCase - reSize - 1;
		int dir = 0;
		var list = c.circlePatch();
		int x_tmp, y_tmp, x_max, y_max;
		x_tmp = x;
		y_tmp = y;
		x_max = 5;
		y_max = 5; 
		for(int i = 0; i < list.size(); i++) {
			System.out.println(i);
			tmp = list.get(i);
			
			if(dir == 0) {
				displayPatch(context, tmp, x_tmp, y_tmp);
				y_tmp -= (sizeCasetmp * (y_max + 1));
				if (y_tmp - (sizeCasetmp * y_max)  <= sizeCasetmp ) {dir++ ;} 
				
			}
			else if(dir == 1) {
				displayPatch(context, tmp, x_tmp, y_tmp);
				x_tmp += (sizeCasetmp * (x_max + 1));
				if(x_tmp + (sizeCasetmp * x_max)  >= widht_p -  x_max * sizeCasetmp) {dir++;}
				
			}
			else if(dir == 2) {
				displayPatch(context, tmp, x_tmp, y_tmp);
				y_tmp += (sizeCasetmp * (y_max + 1));
				if(y_tmp + (sizeCasetmp * y_max)  >= height_p - y_max * sizeCasetmp) {dir++;}
				
			}
			else if(dir == 3){
				displayPatch(context, tmp, x_tmp, y_tmp);
				x_tmp -= (sizeCasetmp * (x_max + 1));
				if(x_tmp - (sizeCasetmp * x_max)  <= 0 + x_max * sizeCasetmp) {return;}
			}
			if(c.posPawn() - 1 == i) {
				graphics.fill(new  Ellipse2D.Float(x_tmp - 10  , y_tmp - 10 , sizeCasetmp + 5, sizeCasetmp + 5));
			}
        }
		});
}
  public static void main(String[] args) {
    Game game = new Game();
	game.init(true);
    var colorlist = colorList();

    Application.run(Color.DARK_GRAY, context -> {
    	
      
      // get the size of the screen
      ScreenInfo screenInfo = context.getScreenInfo();
      float width = screenInfo.getWidth();
      float height = screenInfo.getHeight();
      System.out.println("size of the screen (" + width + " x " + height + ")");
      int sizeCase = (int)height / 9 - 50;
      int ref_x = sizeCase * (3 + 9 + 4);
	  int ref_y = sizeCase * (3 + 4);
      Graphique graph = new Graphique(sizeCase, 30);
     
      
      for(;;) {
        Event event = context.pollOrWaitEvent(10);
        if (event == null) {  // no event
          continue;
        }
        Action action = event.getAction();
        if (action == Action.KEY_PRESSED || action == Action.KEY_RELEASED) {
          System.out.println("abort abort !");
          context.exit(0);
          return;
        }
        System.out.println(event);
        Point2D.Float location = event.getLocation();
        graph.displayQuiltboard(context, game.timeboard().firstPlayer().quiltboard(), sizeCase * 3, sizeCase * 3, colorlist);
        graph.displayTimeboard(context, game.timeboard());
        graph.displayQuiltboard(context, game.timeboard().firstPlayer().quiltboard(), sizeCase * (3 + 18), sizeCase * 3, colorlist);
        graph.displayCirclepatch(context, game.circlePatch(), 10, sizeCase * 16, width, height);
        graph.displayPlayer(context, game.timeboard().first(), sizeCase * 3, sizeCase * 12);
        graph.displayPlayer(context, game.timeboard().second(), sizeCase * (3 + 18), sizeCase * 12);
      }
    });
  }
}
