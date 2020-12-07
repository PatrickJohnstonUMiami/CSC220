package lab05;

public class SortedBinarySet {
	/** represent the simple array that holds the list values */
	public double[] theData;
	/** holds the length of the storage array */
	private int capacity;
	/** holds the number of elements in the list */
	private int size;

	/** constant for initial storage array capacity */
	private static final int INITIAL_STORAGE_CAPACITY = 11;

	/** keep this FALSE for lab; we will play with this in the assignment **/
	public boolean usesBinarySearch = false;

	/** default constructor */
	public SortedBinarySet() {
		theData = new double[INITIAL_STORAGE_CAPACITY];
		capacity = INITIAL_STORAGE_CAPACITY;
		size = 0;
	}

	public SortedBinarySet(double[] input) {
		theData = new double[INITIAL_STORAGE_CAPACITY];
		capacity = INITIAL_STORAGE_CAPACITY;
		size = 0;

		for (int i = 0; i < input.length; i++) {
			this.insert(input[i]);
		}

		// TODO (for assignment, not lab)
	}

	public boolean empty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public int size() {

		return size; // placeholder
	}

	public void grow() {
		double tempArray[] = new double[capacity * 2];
		for (int i = 0; i < theData.length; i++) {
			tempArray[i] = theData[i];
		}

		capacity = capacity * 2;
		theData = tempArray;
	}

	public String toString() {
		String holder = "";
		for (int i = 0; i < size; i++) {
			holder = holder + ("[" + theData[i] + "]");
		}
		holder = holder + "(size = " + size + ")(capacity = " + capacity + ")";

		return holder; // placeholder
	}

	public void clear() {
		for (int i = (capacity - 1); i >= 0; i--) {
			theData[i] = 0;
		}
		capacity = INITIAL_STORAGE_CAPACITY;
		size = 0;

	}

	public boolean insert(double newVal) {
		if (size == capacity) {
			this.grow();
		}

		int indices = (this.findIndex(newVal));
		if (indices < 0) {
			indices = -indices - 1;

			for (int i = size - 1; indices <= i; i--) {
				theData[i + 1] = theData[i];
			}
			theData[indices] = newVal;
			size++;
			return true;

		}

		return false;
	}

	public boolean remove(double element) {
		int indices;
		indices = (this.findIndex(element));

		if (indices < 0) {
			return false;
		}
		if (indices >= 0) {
			theData[indices] = 0;
			for (int i = indices; i <= size - 1; i++) {
				theData[i] = theData[i + 1];
			}
			size--;
			return true;

		}

		return false; // placeholder
	}

	private int sequentialFind(double target) {
		if (this.empty() == true) {

			return -1;
		}

		int placeholder = 0;
		for (int i = 0; i <= size - 1; i++) {
			if (theData[i] == target) {
				return i;
			}
		}

		for (int x = 0; x <= size - 1; x++) {
			if (target < theData[x]) {
				placeholder = (int) (-(x) - 1);

				return placeholder;
			}
			if (x == size - 1) {
				return (int) (-(size) - 1);
			}

		}

		return placeholder; // placeholder
	}

	private int binaryFind(double target) {

		int max = size-1;
		int min = 0;
		if (this.empty() == true) {
			return -1;
		}

		// Searching for an existing number
		while( min<= max) {
			int mid = (max + min)/2;
			if (theData[mid] == target) {
				return mid; 
			}else if (theData[mid] < target) {
				min = mid +1;
			}else {
				max = mid -1;
			}
		}
		return -min -1;
	}

	public int findIndex(double target) {
		if (usesBinarySearch) {
			return binaryFind(target);
		} else {
			return sequentialFind(target);

		}
	}

	public boolean containsAll(double[] elements) {
		for (int i = 0; i <= elements.length - 1; i++) {
			if (this.contains(elements[i]) == false) {
				return false;
			}
		}
		// TODO
		return true; // placeholder
	}

	public boolean contains(double element) {
		int max = size - 1;
		int min = 0;
		while (min <= max) {
			int mid = (max + min) / 2;
			if (theData[mid] == element) {

				return true;

			} else if (theData[mid] < element) {

				min = mid + 1;
			} else if (theData[mid] > element) {
				max = mid - 1;
			}

		}
		// TODO
		return false; // placeholder
	}

}
