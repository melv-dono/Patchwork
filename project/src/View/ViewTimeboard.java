package View;

import Model.Case;
import Model.Player;
import Model.Timeboard;

public record ViewTimeboard() {
	
	private static void baseDeCase(StringBuilder b, int length) {
		for (int i = 0; i < length; i++) {
			b.append(Separator.hoziSep());
			b.append(Separator.space());
		}
		b.append(Separator.line());
	}
	
	public static void display(Timeboard t) {
		var string = new StringBuilder();
		for(var entry:t.cases().entrySet()) {				
			if((t.first().currentPosition() == t.second().currentPosition())
					&& (entry.getKey() == t.first().currentPosition())) {
					string.append(t.first().turn() ? t.first().name().charAt(0) :
						t.first().name().charAt(0));
					string.append(Separator.vertSep());
			}
			else if(entry.getKey() == t.first().currentPosition()) 	{
				string.append(t.first().name().charAt(0));
				string.append(Separator.vertSep());
			}
			else if(entry.getKey() == t.second().currentPosition()) {
				string.append(t.second().name().charAt(0));
				string.append(Separator.vertSep());
			}
			else if(entry.getValue() == Case.NEUTRAL) {
				string.append(".");
				string.append(Separator.vertSep());
			}
			else if(entry.getValue() == Case.BUTTON) {
				string.append("+");
				string.append(Separator.vertSep());
			}
			else if(entry.getValue() == Case.SPECICAL_PATCH) {
				string.append("*");
				string.append(Separator.vertSep());
			}
			
			if(entry.getKey() % 7 == 0) {
				string.append("\n");
				baseDeCase(string, 7);
			}
		}
		string.append("\n \n'+' for Button, '*' for Special Pacth.\n");
		System.out.println(string.toString());
	}
	
	public static void main(String args[]) {
		Player p1 = new Player("f");
		Player p2 = new Player("s");
		Timeboard t = new Timeboard(p1, p2);
		t.initMagicCases(true);
		display(t);
	}

}
