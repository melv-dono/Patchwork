package Model;

import java.util.ArrayList;
import java.nio.file.Path;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Files.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.*;

public class CirclePatch {
	/**
	 * The neutral token.
	 * 
	 */
	private final Pawn pawn;
	
	/**
	 * 
	 */
	private final LinkedHashMap<Integer, Patch> circlePatch;
	
	public CirclePatch() { // Melvyn removed this LinkedHashMap<Integer, Patches> circlePatch
		this.pawn = new Pawn(0);
		this.circlePatch = new LinkedHashMap<Integer, Patch>();
		
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
	 * 				refers to the number of files inside the folder 
	 * 
	 */
	
	public void initCirclePatch(String folder, int sizeFolder) throws IOException { // WARINING % 2 because only 2 files
		for (int i = 0; i < sizeFolder; i++) { // PAS OPTI DU TOUT GÉNÉRATION DE SB + PAS DE PARSE DU INT
			circlePatch.put(Integer.valueOf(i), createPatch(Path.of(folder).resolve("patch" + (i%2 + 1) + ".txt")));
		}
		
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
	public Patch createPatch(Path path) throws IOException {
		int data[] = readPatchData(path);
		int tab[][] = readPatchTab(path, data[0], data[1]);
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
	public int[] readPatchData(Path path) throws IOException {
		int patchInfo[] = new int[5];
		String line;
		
		try (BufferedReader reader = Files.newBufferedReader(path);) {				
			for (int i = 0; i < 5; i++) {
				line = reader.readLine();
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
	public int[][] readPatchTab(Path path, int d1, int d2) throws IOException {
		int tab[][] = new int[d1][d2];
		int pixel;
		String line;
		
		try (BufferedReader reader = Files.newBufferedReader(path);) {
			line = reader.readLine();
			while (!line.isEmpty()) {
				line = reader.readLine();
			}
			
			for (int i = 0; i < d1; i++) {
				for (int j = 0; j < d2; j++) { // Normaly we dodge the \n at each line
					pixel = reader.read(); // BIG PROBLEM IT READ 49 NOT 1
					tab[i][j] = pixel;
				}
				reader.read();
			}
		}
		return tab;		
	}
	
	/*JUSTE POUR QUELQUES TESTS À ÉFFACER*/
	public static void main(String[] args) {
		var c = new CirclePatch();
		try {
			System.out.println(c.createPatch(Path.of("src/data/phase1").resolve("patch1.txt")));
			
			c.initCirclePatch("src/data/phase1", 10);
			
			/*Artificial toString*/
			c.circlePatch.values().stream()
			.forEach(x -> System.out.println(x));
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
		
	/**
	 * copies the patch chosen by the player thanks to choosePatch()
	 * then gives the possibility to the player to place it on the quilboard. 
	 * 
	 * @param int of the next pach select.
	 * 
	 * @return Patch the copy of the patch of the player choose.
	 */
	public Patch selectNextPatch(int next)
	{
		return null;
	}
	/**
	 * List of next 3 purchasable patches
	 * 
	 */
	public List<Patch> nextPatches()
	{
		List<Patch> result = new ArrayList<Patch>();
		return result;
	}
	
	/**
	 * Move the pawn on the chosen patch and remove ot.
	 */
	public void swapPatch(int index)
	{
		for(int i = 0; i < circlePatch.size();i++)
		{
			
		}
	}
}
