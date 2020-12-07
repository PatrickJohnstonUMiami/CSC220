package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AnagramUtil {

	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.
	public static SortedString[] readFile(String filename) {
		ArrayList<SortedString> results = new ArrayList<SortedString>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready()) {
				results.add(new SortedString(input.readLine()));
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		SortedString[] retval = new SortedString[1];
		return results.toArray(retval);
	}

	public static String[] getLargestAnagramGroup(String filename) {
		SortedString[] words = readFile(filename);
		String[] toReturn = getLargestAnagramGroup(words);
		return toReturn;
	}

	public static String[] getLargestAnagramGroup(SortedString[] stringList) {

		/*
		 * Initialize a sorting algorithm of type SortedString using either MergeSort or
		 * InsertionSort
		 */
		// InsertionSort<SortedString> g=new InsertionSort<>();
		MergeSort<SortedString> g = new MergeSort<>();

		/* sort the input string list */

		stringList = g.sort(stringList);

		/* The case where stringList is of size 1 or less */
		if (stringList.length <= 1) {
			return new String[stringList.length];

		}

		/* The variables for the logic following */
		int end = 0, length = 1, maxLength = 0;


		for (int i = 0; i < stringList.length - 1; i++) {
			SortedString a = (stringList[i]);
			SortedString b = (stringList[i + 1]);

			if (areAnagrams(a, b) == true) {

				length++;

				if (length > maxLength) {
					maxLength = length;
					end = i + 1;
				}
			} else {
				length = 1;

			}

		}
		System.out.println("made it out");

		SortedString a = (stringList[stringList.length - 1]);
		SortedString b = (stringList[stringList.length - 2]);
		if (true == areAnagrams(a, b)) {
			length++;
			if (length > maxLength) {
				end = stringList.length - 1;
				maxLength = length;
			}

		}
		if (maxLength == 0 && end == 0) {
			return new String[stringList.length];

		}

		/*
		 * Don't forget one last check for the end: if the longest list is the last
		 * group. As before, update variables accordingly.
		 */

		/*
		 * Prepare to return. The following is almost the answer except for one thing...
		 */
		String[] toStringList = new String[stringList.length];
		for (int i = 0; i < toStringList.length; i++) {
			toStringList[i] = stringList[i].toString();

		}
		String[] toReturn = new String[maxLength];
		for (int j = 0; j < maxLength; j++)
			toReturn[j] = toStringList[j + end - maxLength + 1];

		return toReturn; // return the right thing
	}

	public static boolean areAnagrams(SortedString str1, SortedString str2) {
		if (str1.compareTo(str2) == 0) {
			return true;
		}
		// EXERCISE
		return false;
	}

}
