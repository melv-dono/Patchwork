package Model;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <b>Card represents a card in the game</b>
 * <p>
 * A Card is characterized by :
 * <ul>
 * <li>button : the number of virtual buttons.</li>
 * <li>income : the number of buttons earns when the token cross a special case (button income)</li>
 * <li>filters : list filters that it can apply.</li>
 * </ul>
 * </p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */

public record Card(int button, int income, List<Integer> filters) {
	
	public Card(int button, int income, List<Integer> filters) {
		if (button < 0 || income < 0) {
			throw new IllegalArgumentException();
		}
		this.button = button;
		this.income = income;
		this.filters = List.copyOf(Objects.requireNonNull(filters));
	}
	
	
	/**
	 * Filter rules n°2 
	 * It selects the patch with the biggest area
	 * If there is several patches after the filter it 
	 * returns a list of them
	 * @param patches
	 * 			a list of patches	
	 * @return 
	 * 		list of patches after filtering
	 */
	private static List<Patch> largest(List<Patch> patches) {
		OptionalInt max;
		List<Patch> largest;
		
		/*Get highest value*/
		max = patches.stream()
				.mapToInt(Patch::area)
				.max();
		
		/*Get the patch corresponding*/
		largest = patches.stream()
				.filter(p -> p.area() == max.getAsInt())
				.toList();
		
		return largest;
	}
	
	/**
	 * Filter rules n°4 
	 * It selects the patch with the highest index
	 * @param patches
	 * 			a list of patches	
	 * @return 
	 * 		list of patches after filtering
	 */
	private static List<Patch> farest(List<Patch> patches) {
		return List.of(patches.get(patches.size() - 1));		
	}
	
	/**
	 * Filter rules n°3 
	 * It selects the patch with the biggest amount
	 * of buttons
	 * If there is several patches after the filter it 
	 * returns a list of them
	 * @param patches
	 * 			a list of patches	
	 * @return 
	 * 		list of patches after filtering
	 */
	private static List<Patch> wealthiest(List<Patch> patches) {
		OptionalInt max;
		List<Patch> wealthiest;
		
		/*Get highest value*/
		max = patches.stream()
				.mapToInt(Patch::buttons)
				.max();
		
		/*Get the patch corresponding*/
		wealthiest = patches.stream()
				.filter(p -> p.buttons() == max.getAsInt())
				.toList();
		
		return wealthiest;
	}
	
	/**
	 * Filter rules n°1 
	 * It selects the patch with the quantity of movement
	 * nearest of its initial position and behind his opponent
	 * If there is several patches after the filter it 
	 * returns a list of them
	 * @param patches
	 * 			a list of patches	
	 * @param pos
	 * 			position of the player
	 * @return 
	 * 		list of patches after filtering
	 */
	private static List<Optional<Patch>> nearest(List<Patch> patches, int pos) {
		OptionalInt min;
		List<Optional<Patch>> lowest;
		
		/*Get lowest value*/
		min = patches.stream()
				.mapToInt(Patch::movement)
				.min();
		
		if (min.getAsInt() < pos) {
			/*Get the patch corresponding*/
			lowest = patches.stream()
					.filter(p -> p.movement() == min.getAsInt())
					.map(p -> Optional.of(p))
					.toList();
			return lowest;
		}
		else {
			return List.of(Optional.empty());
		}
	}
	
	/**
	 * Check the validity of the given number
	 * It's a specific case for the number of filter rules
	 * 
	 * @param n 
	 * 		value representing the number of a filter rule
	 */
	private static void checkFilterNumber(int n) {
		if (n > 4 || n < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Convert a List<Patch> in a List<Optional<Patch>> using stream
	 * 
	 * @param patches
	 * 			a list of patches	
	 * @return 
	 * 		value representing the area of the current patch
	 */
	private List<Optional<Patch>> conv(List<Patch> patches) {
		return patches.stream()
				.map(p -> Optional.of(p))
				.toList();
	}
	
	/**
	 * Use the right filter rule to narrow a given list of patches
	 * @param pos
	 * 			position of th e player
	 * @param patches
	 * 			a list of patches	
	 * @param filtNbr
	 * 			the number rule you want to apply
	 * @return 
	 * 		value representing the area of the current patch
	 */
	private List<Optional<Patch>> applyFilter(int filtNbr, List<Patch> patches, int pos) {
		Objects.requireNonNull(patches);
		checkFilterNumber(filtNbr);
		
		return switch (filters.get(filtNbr)) {
			case 1 -> nearest(patches, pos);
			case 2 -> conv(largest(patches));
			case 3 -> conv(wealthiest(patches));
			case 4 -> conv(farest(patches));
			default -> throw new IllegalArgumentException();
		};
	}
	
	private void checkPos(int pos) {
		if (pos < 0) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Determined which patch need to be selected after using
	 * all the filtering rules in a ascending order
	 * @param pos
	 * 			position of th e player
	 * @param patches
	 * 			a list of patches	
	 * @param filtNbr
	 * 			the number rule you want to apply
	 * @return 
	 * 		patch selected
	 */
	public Patch determinPatch(List<Patch> patches, int pos) {
		Objects.requireNonNull(patches);
		checkPos(pos);
		List<Optional<Patch>> patchSelected;
		int i = 1;
		
		do {
			patchSelected = applyFilter(i, patches, pos);
			i++;
		}		
		while (patchSelected.size() > 1 || patchSelected.get(0).isEmpty());
		
		return patchSelected.get(0).get();
	}
}
