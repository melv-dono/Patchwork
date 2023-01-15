package Model;

import java.util.List;
import java.util.Optional;

public interface Player2 {
	
	String name();
	
	int button();
	
	Quiltboard quiltboard();
	
	int currentPosition();
	
	Patch patch();
	
	boolean turn();
	
	boolean realPlayer();
	
	void changeTurn();
	
	void earnButton(int income);
	
	void movePawn(int distance);

	Optional<Patch> performTurn(List<Patch> patches, int pos);
	
	void buyPatches(Patch patch);
	
	void patchChose(Patch thisPatch);
	
	int totalScore();
	
	boolean checkSpecialTile();
	
	void activeModeGlouton();
	
	boolean modeGlouton();
	

	
}
