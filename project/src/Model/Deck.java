package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <b>Deck represents the set of cards use during the game</b>
 * <p>
 * A Deck is characterized by :
 * <ul>
 * <li>version : the type of set you want to use. Either normal or tactic.</li>
 * <li>cards : represents the list of the pickable cards</li>
 * <li>graveyard : refers to the list of card already picked.</li>
 * </ul>
 * </p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */
public class Deck {
	
	private final int version;
	
	private final List<Card> cards;
	
	private final List<Card> graveyard;
	
	private final static int NBR_CARD = 12;
	
	private final static String PATH = "src/data/card.txt";
	
	public Deck(int version) {
		if (version != 1 && version != 2) {
			throw new IllegalArgumentException();
		}
		this.version = version;
		cards = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
	}
	
	private void calibrateReader(BufferedReader reader) throws IOException {
		for (int i = 0; i < NBR_CARD * 6; i++) {
			reader.readLine();
		}
	}
	
	public void initCards() {
		Path path = Paths.get(PATH);
		try {
			try (BufferedReader reader = Files.newBufferedReader(path);) {
				if (version == 2) {
					calibrateReader(reader);
				}
				
				for (int i = 0; i < NBR_CARD; i++) {
					cards.add(createCard(reader));
				}
			}			
		}
		catch (IOException ioe) {
			System.err.println("Error in the reading of card.txt");
		}
		Collections.shuffle(cards);
		this.draw();
		this.draw();
	}
	
	private static Card createCard(BufferedReader reader) throws IOException {
		int[] tab = new int[5];
		String line;
		
		for (int i = 0; i < 5; i++) {
			line = reader.readLine();
			tab[i] = Integer.parseInt(Character.toString(line.charAt(line.length() - 1)));
			if (i == 0 && tab[i] == 9) { /*short correction for number with 2 digits*/
				tab[i] = 10;
			}
		}
		reader.readLine();
		return new Card(tab[0], tab[1], List.of(tab[2], tab[3], tab[4]));
	}
	
	private void reset() {
		Collections.shuffle(graveyard);
		
		for (int i = NBR_CARD - 1; i >= 0; i--) {
			cards.add(graveyard.get(i));
			graveyard.remove(i);
		}
	}
	
	public Card draw() {
		Card current;
		if (cards.isEmpty()) {
			this.reset();
			return draw();
		}
		else {
			current = cards.get(cards.size() - 1);
			cards.remove(current);
			graveyard.add(current);
			return current;			
		}
	}
	
	public static void main(String args[]) {
		Deck d = new Deck(2);
		d.initCards();
		d.cards.forEach(c -> System.out.println(c));
		CirclePatch c = new CirclePatch();
		try {
			c.initCirclePatch("src/data/phase2.txt", 33, 1);
			System.out.println(c.nextPatches());
			Card card = d.draw();
			System.out.println(card.determinPatch(c.nextPatches(), 4));
		} 
		catch (IOException ioe) {
			
		}
	}
}
