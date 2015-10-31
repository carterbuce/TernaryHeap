import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;

/** HeapSort.java
 * @author Carter Buce | cmb9400 
 *
 * Version:
 *		$Id: HeapSort.java,v 1.4 2015/10/27 22:12:35 cmb9400 Exp $
 *
 * Revisions:
 *		$Log: HeapSort.java,v $
 *		Revision 1.4  2015/10/27 22:12:35  cmb9400
 *		fixed bugs
 *
 *		Revision 1.3  2015/10/27 20:57:22  cmb9400
 *		fixed bugs
 *
 *		Revision 1.2  2015/10/27 20:53:49  cmb9400
 *		changed to file input
 *
 *		Revision 1.1  2015/10/27 14:44:38  cmb9400
 *		wrote main method
 *
 */

/**
 * Reads in some integers from a data file whose name is provided on the command line, 
 * inserting them into a heap as it reads them.
 * Removes them from a heap and prints them in (hopefully) sorted order
 */
public class HeapSort {

	/**
	 * @param args array of Integers to sort with a heap
	 */
	public static void main(String[] args){
		
		//read integers from a file into the input list
		LinkedList<Integer> input = new LinkedList<Integer>();
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(args[0]));
		    String line = br.readLine();
		    while (line != null) {
		        input.add(Integer.parseInt(line));
		        line = br.readLine();
		    }
		    br.close();
		}
		catch(IOException e){
			System.err.println("Invalid Filename!");
			System.exit(1);
		}
		
		//create the heap
		Integer[] init = new Integer[input.size()];
		TernaryHeap<Integer> sort = new TernaryHeap<Integer>(init);
		
		//put Integers from file into the heap
		for(Integer i : input){
			sort.insert(i);
		}
		
		//remove integers from the heap, printing them
			try {
				int max = sort.size();
				for(int i = 0; i < max; i++){
					System.out.print(sort.remove().toString() + " ");
				}
			}
			catch (UnderflowException e) {
				System.out.println(e.getMessage());
			}
		

	}
	

}
