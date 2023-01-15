package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Automa {
	private final String name;
	
	private Pawn pawn;
	
	private int buttons;
	
	private int virtualButtons;
	
	private boolean turn;
	
	private boolean specialPatch;
	
	private final List<Patch> stackButton;
	
	private final List<Patch> stack;
	
	private Deck deck;
	
	private Optional<Card> card;
	
	public Automa(int version) {
		name = "Automa";
		pawn = new Pawn(1);
		stack = new ArrayList<>();
		stackButton = new ArrayList<>();
		checkVersion(version);
		deck = new Deck(version);
		deck.initCards();
		card = Optional.empty();
		turn = false;
		virtualButtons = 0;
	}
	
	private void checkVersion(int version) {
		if (version != 1 && version != 2) {
			throw new IllegalArgumentException();
		}
	}
	
	private void draw() {
		card = Optional.of(deck.draw());
		virtualButtons = card.get().button();
	} 
	
	private boolean affordability(Patch p) {
		return (virtualButtons >= p.buttons()) ? true : false ;
	}
	
	/*return empty mean can not afford a patch*/
	public Optional<Patch> performTurn(List<Patch> patches, int pos) {
		Objects.requireNonNull(patches);
		Patch currentPatch;
		draw();
		
		currentPatch = card.get().determinPatch(patches, pos);
		if (affordability(currentPatch)) {
			return Optional.of(currentPatch);
		}
		else {
			return Optional.empty();
		}
	}
	
	/*return the card the automa is holding*/
	public Optional<Card> currentCard() {
		return card;
	}
	
	public String name() {
		return name;
	}
	
	public int currentPosition() {
		return pawn.currentPosition();
	}
	
	public boolean turn() {
		return turn();
	}
	
	public void changeTurn() {
		turn = !turn;
	}
	
	public boolean realPlayer() {
		return false;
	}
	
	public void earnButton(int income) {
		if (income < 0) {
			throw new IllegalArgumentException();
		}
		buttons += card.get().income();
	}
	
	public void movePawn(int distance) {
		if (distance < 0) {
			throw new IllegalArgumentException();	
		}
		pawn.movePawn(distance);
	}
	
	public Quiltboard quiltboard() {
		return null;
	}	

	public Patch patch() {
		return null;
	}
	
	public int button() {
		return buttons;
	}	
	
	public void buyPatches(Patch patch) {}
	
	public void patchChose(Patch thisPatch) {}
	
	@Override
	public String toString() { /*TODO : replace buttons with score*/
		var string = new StringBuilder();
		string.append("NAME CURRENT PLAYER : " + name + "\n");
		string.append("NUMBER OF BUTTON : " + buttons + "\n");
		return string.toString();
	}
}
