package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public class CirclePatch {
	/**
	 * The neutral token.
	 * 
	 */
	private final Pawn pawn;
	
	/**
	 * 
	 */
	private final ArrayList<Patch> circlePatch;
	
	public CirclePatch() {
		this.pawn = new Pawn(0);
		this.circlePatch = new ArrayList<Patch>();
		
	}
	
	/**
	 * Initialize the position of the neutral token
	 * To the index of the patch with the lowest area
	 * This function works only if circlePatch is already initialize
	 */
	public void initNeutralToken() {
		Optional<Patch> tiniest = null;
		OptionalInt op = circlePatch
				.stream()
				.mapToInt(Patch::area)
				.min();
		if (op.isPresent()) {
			tiniest = circlePatch
					.stream()
					.filter(x -> x.area() == op.getAsInt())
					.findFirst();
			pawn.movePawn(circlePatch.indexOf(tiniest.get()));
		} 
	}
	
	/**
	 * Generate all the patches for a given phase
	 * 
	 * @throws 
	 * 			IOException
	 * 
	 * @param folder
	 * 				refers to the package to search for data
	 * 				each folder of data refer to a particular phase PUT ON CONFLUENCE
	 * 
	 * @param sizeFolder
	 * 				refers to the number of patch inside the file 
	 * 
	 */
	
	public void initCirclePatch(String folder, int sizeFolder, int iteration) throws IOException {
		Path path = Paths.get(folder);
		/*Path path = Path.of("src/data").resolve(folder);*/
		int lineRemaining = 3000; 

		try (BufferedReader reader = Files.newBufferedReader(path); ) {
			reader.mark(lineRemaining); // This var need to be chosen carefully regarding the memory allocation
			for (int i = 0; i < iteration; i++) {
				for (int j = 0; j < sizeFolder; j++) {
					circlePatch.add(createPatch(reader, path));
					reader.readLine();
					reader.readLine();
				}
				reader.reset();
			}
		}
		Collections.shuffle(circlePatch);
	}
	
	/**
	 * Generate a patch by reading the file
	 * 
	 * @throws 
	 * 			IOException
	 * 
	 * @param path
	 * 			Path to the file we want to read
	 * 
	 * @return 
	 * 			an object patch create from the data of the file in parameter
	 */
	public Patch createPatch(BufferedReader reader, Path path) throws IOException {
		int data[] = readPatchData(reader, path);
		int tab[][] = readPatchTab(reader, path, data[0], data[1]);
		return new Patch(new Label(data[2], data[3], data[4]), tab);
		
	}
	
	/**
	 * WARNING : maybe we need to move this method to Patch and make it static
	 * Read every informations of the file containing data regarded a patch
	 * 
	 * @throws 
	 * 			IOException
	 * 
	 * @param path
	 * 
	 * @return
	 * 			a tab of in containing every information about the patch's data 
	 * 			in the order of apparition
	 */
	public int[] readPatchData(BufferedReader reader, Path path) throws IOException {
		int patchInfo[] = new int[5];
		String line;
			
		for (int i = 0; i < 5; i++) {
			line = reader.readLine();
			if(line.length() > 0)
			{
				
				patchInfo[i] = Integer.parseInt(Character.toString(line.charAt(line.length() - 1)));
			}
		}
		return patchInfo;			
	}	
	
	/**
	 * WARNING : maybe we need to move this method to Patch and make it static
	 * Read only the tab representing the shape of the patch
	 * 
	 * @throws 
	 * 			IOException
	 * 
	 * @param path
	 * 			
	 * 
	 * @param d1
	 * 			represent the size of the first dimension of the tab
	 * 
	 * @param d2
	 * 			represent the size of the second dimension of the tab 
	 * 
	 * @return
	 * 			a tab representing the shape of the patch
	 */
	public int[][] readPatchTab(BufferedReader reader, Path path, int d1, int d2) throws IOException {
		int tab[][] = new int[d1][d2];
		Integer pixel;
		String line;
		reader.readLine();// Because we put a space between the data and tab from a same patch
		for (int i = 0; i < d1; i++) {
			line = reader.readLine();
			for (int j = 0; j < d2; j++) { // Normaly we dodge the \n at each line
				//pixel = Integer.parseInt(Character.toString(reader.read()));
				pixel = Integer.parseInt(Character.toString(line.charAt(j)));
				tab[i][j] = pixel;
			}
		}
		return tab;		
	}
	
	/*JUSTE POUR QUELQUES TESTS À ÉFFACER*/
	public static void main(String[] args) {
		var c = new CirclePatch();
		
		try {
			c.initCirclePatch("phase2.txt", 33, 1);				
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
		
	/**
	 * copies the patch chosen by the player thanks to choosePatch()
	 * then gives the possibility to the player to place it on the quilboard. 
	 * 
	 * @param int of the next pacth select.
	 * 
	 * @return Patch the copy of the patch of the player choose.
	 */
	public Patch selectNextPatch(int next) {
		return circlePatch.get(next);
	}
	
	/**
	 * List of next 3 purchasable patches
	 * 
	 */
	public List<Patch> nextPatches() {
		int index;
		List<Patch> result = new ArrayList<Patch>();
		for (int i = 0; i < 3; i++) {
			index = pawn.currentPosition() + i % circlePatch.size(); // Neutral token can not exceed the number of remaining patch
			result.add(circlePatch.get(index));
		}
		return result;
	}
	
	/**
	 * Move the pawn on the chosen patch and remove ot.
	 */
	public void swapPatch(int index) {
		pawn.movePawn(index);
		circlePatch.remove(index);
	}
	
	public ArrayList<Patch> circlePatch()
	{
		return circlePatch;
	}
	
	@Override
	public String toString() {
		var string = new StringBuilder();
		// Print the patch of the right side of the table.
		for(int patch = pawn.currentPosition(); patch < circlePatch.size(); patch++)
		{
			string.append(circlePatch.get(patch).toString());
			string.append("\n");
		}
		// Print the patch of the left side of the table.
		for(int patch = 0; patch < pawn.currentPosition(); patch++)
		{
			string.append(circlePatch.get(patch).toString());
			string.append("\n");
		}
		return string.toString();
	}
}
