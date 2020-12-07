package lab06;

import java.util.Arrays;

public class SortedString implements Comparable<SortedString> {

	private String unsorted;
	private String sorted;

	public SortedString(String unsorted) {
		this.unsorted = unsorted;
		int lengthArr = unsorted.length();
		unsorted = unsorted.toLowerCase();
		
		char sortedArr[] = unsorted.toCharArray();
		
		Arrays.sort(sortedArr);
		String sorted = "";
		for(int i = 0; i < sortedArr.length;i++){
			sorted = sorted +sortedArr[i];
			
		}
		this.sorted = sorted;
	
		
		
	}

	/** convenience function to convert string array to a SortedString array
	   @param strings input array of strings
	   @return the SortedString representation
	*/
	public static SortedString[] toSortedString(String[] strings) {
		SortedString[] out = new SortedString[strings.length];
		for (int i = 0; i < out.length; i++)
			out[i] = new SortedString(strings[i]);
		return out;
	}

	/* Return sorted version of string */
	public String getSorted() {
		return sorted;
	}

	/* Return original (i.e. unsorted) string */
	public String getUnsorted() {
		return unsorted;
	}

	@Override
	public String toString() {
		return  unsorted;
	}

	public int compareTo(SortedString other){
		
		//if s1 > s2, it returns positive number  
		if( this.sorted.compareTo(other.sorted) > 0) {
			return 1;
		}
		if( this.sorted.compareTo(other.sorted) < 0) {
			return -1;
		}
		if( this.sorted.equals(other.sorted) ) {
			return 0;
		}
	
		return 0;
	}
}
