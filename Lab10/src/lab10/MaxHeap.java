package lab10;

public class MaxHeap {
	/** internal representation of the heap */
	private int[] theData;
	/** store the number of current elements inside the heap */
	private int size;

	/**
	 * constructor to initialize the status of the objects of this class based on
	 * the input parameter (i.e., size)
	 * 
	 * @param maxsize the size for the heap
	 */
	public MaxHeap(int maxsize) {
		this.theData = new int[maxsize];
		this.size = -1;

		// TODO for Lab10
	}

	/**
	 * constructor that initializes a heap and put the values of the input theData
	 * in it in such a way that the constructed max heap is valid.
	 * 
	 * @param arr the input theData
	 */
	public MaxHeap(int[] arr) {
		this.theData = new int[arr.length];
		this.size = -1;
		for (int i : arr) {
			offer(i);
		}
		// TODO for Assignment10
	}

	public int size() {
		return size;
	}

	private int parent(int pos) {
		return (pos - 1) / 2;
	}

	private int leftChild(int pos) {
		return 2 * pos + 1;
	}

	private int rightChild(int pos) {
		return 2 * pos + 2;
	}

	private boolean isLeaf(int pos) {
		return (pos >= size / 2) && (pos < size);
	}

	/**
	 * Swap the items with index i and index j in the heap theData.
	 * 
	 * @param i index of first item in heap
	 * @param j index of second item in theData
	 */
	private void swap(int i, int j) {
		int value = theData[i];
		theData[i] = theData[j];
		theData[j] = value;
	}

	/**
	 * Returns a string containing contents of the heap as an theData NOTE this
	 * method should *not* be modified.
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < theData.length; i++)
			s += theData[i] + ", ";
		s += "\n";
		return s;
	}

	/**
	 * Prints the tree contents, one per line, following an inorder traversal and
	 * using indentation to indicate node depth; prints right to left so that it
	 * looks correct when the output is rotated; NOTE this method should not be
	 * modified.
	 */
	public void printSideways() {
		printSideways(0, 0);
	}

	/**
	 * Prints in reversed preorder the tree with given root, indenting each line to
	 * the given level
	 * 
	 * @param root_indx the given root
	 * @param level     indentation level NOTE this method should not be modified.
	 */
	private void printSideways(int root_indx, int level) {
		if (root_indx < theData.length) {
			printSideways(rightChild(root_indx), level + 1);
			for (int i = 0; i < level; i++) {
				System.out.print("       ");
			}
			System.out.println(theData[root_indx]);
			printSideways(leftChild(root_indx), level + 1);
		}
	}

	/**
	 * tests if the contents of the heap are equal to an input theData
	 * 
	 * @param arr the input theData NOTE this method should not be modified.
	 */
	public boolean IsEqual(int[] arr) {
		if (arr.length != theData.length)
			return false;

		if (arr.length == 0)
			return true;

		for (int i = 0; i < arr.length; i++)
			if (arr[i] != theData[i])
				return false;

		return true;
	}

	private void maxHeapify(int pos) {
		int check = size;
		int spot;

		if (rightChild(pos) <= check || leftChild(pos) <= check) {
			if (leftChild(pos) <= check && rightChild(pos) <= check) {
				if (theData[rightChild(pos)] > theData[leftChild(pos)]) {
					spot = rightChild(pos);
				} else {
					spot = leftChild(pos);
				}
			} else if (rightChild(pos) > check) {
				spot = leftChild(pos);
			} else {
				spot = rightChild(pos);
			}
			if (pos <= size && theData[pos] < theData[spot]) {
				int temp = theData[pos];
				theData[pos] = theData[spot];
				theData[spot] = temp;
				maxHeapify(spot);
			}
		}
	}

	/**
	 * Remove an item from the heap. pre: theData is in heap order. post: Removed
	 * maximum item, theData is in heap order.
	 * 
	 * @return The item with the maximum value or -1 if empty.
	 */
	public int poll() {
		if (size < 0) {
			return -1;
		}
		int large = theData[0];
		theData[0] = theData[size];
		theData[size] = 0;
		--size;
		if (size > 0) {
			maxHeapify(0);
		}
		return large;

	}

	/**
	 * Insert an element into the heap. pre: theData is in heap order. post: The
	 * element is in the heap and theData is in heap order.
	 * 
	 * @param element The element to be inserted
	 */
	public void offer(int element) {
		size = size + 1;
		theData[size] = element;
		int current = size;
		while (theData[current] > theData[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);

		}

		// TODO for Lab10
	}

	/**
	 * in-place heap sort algorithm.
	 * 
	 * @param arr the input (unsorted) theData to be sorted. TODO finish writing
	 *            siftDown() for Assignment10 NOTE do *not* modify the signatures of
	 *            sort(), heapify(), or siftDown()
	 */
	public void sort(int[] arr) {
		this.theData = arr;
		this.size = arr.length;

		heapify(size - 1);
		while (size > 1) {
			swap(0, size - 1);
			size--;
			siftDown(0);
		}
	}

	private void heapify(int index) {

		if (index == 0) {
			return;
		}

		for (int i = parent(index); i >= 0; i--) {
			siftDown(i);
		}

	}

	private void siftDown(int index) {
		int lNode = leftChild(index);
		int rNode = rightChild(index);
		if (rNode >= size)
			rNode = index;
		if (lNode >= size)
			lNode = 0;
		if (lNode == 0 && rNode == index)
			return;

		if ((theData[index] < theData[lNode]) || (theData[index] < theData[rNode])) {
			if ((theData[lNode] ) > (theData[rNode] )) {
				swap(index, lNode);
				siftDown(lNode);
			} else if ((theData[lNode] ) <= (theData[rNode] )) {
				swap(index, rNode);
				siftDown(rNode);
			}
		}

	}

}
