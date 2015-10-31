/** TernaryHeap.java
 * @author Carter Buce | cmb9400 
 *
 * Version:
 *		$Id: TernaryHeap.java,v 1.6 2015/10/27 22:12:29 cmb9400 Exp $
 *
 * Revisions:
 *		$Log: TernaryHeap.java,v $
 *		Revision 1.6  2015/10/27 22:12:29  cmb9400
 *		fixed bugs
 *
 *		Revision 1.5  2015/10/27 21:03:24  cmb9400
 *		works, commented out main method
 *
 *		Revision 1.4  2015/10/27 15:01:24  cmb9400
 *		fixed remove method
 *
 *		Revision 1.3  2015/10/26 22:59:30  cmb9400
 *		fixed problem?
 *
 *		Revision 1.2  2015/10/26 22:55:18  cmb9400
 *		fixed some bugs, still doesn't work properly
 *
 *		Revision 1.1  2015/10/26 20:54:35  cmb9400
 *		wrote all methods
 *
 */

/** Generic implementation of a ternary heap. Items are arranged such that
 *  each item in the heap is less than its children in the heap (if any). 
 *  Note that behavior with identical items is not defined.
 */
public class TernaryHeap<E extends Comparable<E>> {
	private E[] heap;
	private int size = 0;
	
	
	/**
	 * constructor of an empty heap
	 * @param init an empty array of the appropriate
	 *   type, large enough to hold the heap at all times
	 */
	TernaryHeap(E[] init){
		heap = init;
	}
	
	
	/**
	 * Insert an item into the heap
	 * @param item the item to insert
	 */
	public void insert(E item){
		heap[size] = item;
		size++;
		
		heapifyUp();
	}
	
	
	/**
	 * Remove and return the item at the root of the heap
	 * @return the item at the root of the heap
	 */
	public E remove() throws UnderflowException{
		if(size == 0){
			throw new UnderflowException("Heap is empty!");
		}
		
		E oldRoot = heap[0];
		heap[0] = heap[size - 1]; //move the last node to the root
		heap[size - 1] = null; // and remove it
		size--;
		
		heapifyDown();
		return oldRoot;
	}
	
	
	/**
	 * Returns the number of items in the heap
	 * @return the number of items
	 */
	public int size(){
		return size;
	}
	
	
	/**
	 * sort the heap so that all parents are less than their descendants
	 * Starts at the root node and works down
	 */
	private void heapifyDown(){
		int current = 0; //start at root
		
		while(hasLeftChild(current)){
			int smallestChild = getLeftChild(current);
			
			if(hasMiddleChild(current) && 
					heap[getMiddleChild(current)].compareTo(heap[smallestChild]) < 0) {
				smallestChild = getMiddleChild(current);
			}
			
			if(hasRightChild(current) && 
					heap[getRightChild(current)].compareTo(heap[smallestChild]) < 0){
				smallestChild = getRightChild(current);
			}
			
			if(heap[current].compareTo(heap[smallestChild]) > 0){
				swap(current, smallestChild);
			}
			else{
				break; // the current node is larger than its children
			}
			
			current = smallestChild;
		}
	}
	
	
	/**
	 * sort the heap so that all parents are less than their descendants
	 * starts at the newly inserted node and works up
	 */
	private void heapifyUp(){
		int current = size - 1; //index of last node in array (most recently added)
		
		while(current >= 1 && //while it's not the root
				heap[getParent(current)].compareTo(heap[current]) > 0){ //and the parent is greater than the child
			swap(current, getParent(current));
			current = getParent(current);
		}
	}	
	
	
	/**
	 * swaps two objects in the heap
	 * @param i first object's location in the array
	 * @param j second object's location in the array
	 */
	private void swap(int i, int j){
		E swap = heap[i];
		heap[i] = heap[j];
		heap[j] = swap;
	}
	
	
	/**
	 * get the index of any node's parent in the array
	 * @param index index of the node
	 * @return index of the node's parent
	 */
	private int getParent(int index){
		if(index%3 == 0){
			return (index / 3 - 1); // this is because the right child will 
		}							//result in a one-off error due to being divisible by 3
		return (int) (index/3); //same as floor (index/3)
	}
	
	
	/**
	 * check if a parent has a left child
	 * @param index the index of the parent node
	 * @return if there exists a left child
	 */
	private boolean hasLeftChild(int index){
		return getLeftChild(index) < size;
	}
	
	
	/**
	 * get the index of a node's left child
	 * @param index the index of the parent node
	 * @return the index of the left child
	 */
	private int getLeftChild(int index){
		return index * 3 + 1;
	}
	
	
	/**
	 * check if a parent has a middle child
	 * @param index the index of the parent node
	 * @return if there exists a middle child
	 */
	private boolean hasMiddleChild(int index){
		return getMiddleChild(index) < size;
	}
	
	
	/**
	 * get the index of a node's middle child
	 * @param index the index of the parent node
	 * @return the index of the middle child
	 */
	private int getMiddleChild(int index){
		return index * 3 + 2;
	}
	
	
	/**
	 * check if a parent has a right child
	 * @param index the index of the parent node
	 * @return if there exists a right child
	 */
	private boolean hasRightChild(int index){
		return getRightChild(index) < size;
	}
	

	/**
	 * get the index of a node's right child
	 * @param index the index of the parent node
	 * @return the index of the right child
	 */
	private int getRightChild(int index){
		return index * 3 + 3;
	}
	
	
	/**
	 * Returns the String representation of the heap. This
	 *   consists of each element's string representation
	 *   on a line by itself, in order of breadth first
	 * @return the heap in string representation
	 */
	public String toString(){
		String result = "";
		for(int i = 0; i < size; i++){
			result += heap[i].toString() + "\n";
		}
		return result;
	}
	
	/*
	public static void main(String[] args) throws UnderflowException{
		Integer[] test = new Integer[10];
		TernaryHeap<Integer> heap = new TernaryHeap<Integer>(test);
		
		heap.insert(3);
		heap.insert(58);
		heap.insert(1);
		heap.insert(4);
		heap.insert(65);
		heap.insert(5);
		heap.insert(1);
		heap.insert(5);
		heap.insert(2);
		heap.insert(100);
		System.out.println(heap.toString());
		System.out.println();
		int max = heap.size();
		for(int i = 0; i < max; i++){
			System.out.print(heap.remove().toString() + " ");
		}
		System.out.println();
		System.out.println(heap.toString());
	//	heap.remove();
	//	System.out.println(heap.toString());
	//	heap.remove();
	//	System.out.println(heap.toString());
	}
	*/
	
}
