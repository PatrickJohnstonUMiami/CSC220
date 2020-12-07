package lab13;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Object;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.*;

public class WordFisher {

	public HashMap<String, Integer> vocabulary;
	private List<String> stopwords;
	private String inputTextFile;
	private String stopwordsFile;

	public WordFisher(String inputTextFile, String stopwordsFile) {

		this.stopwordsFile = stopwordsFile;
		this.inputTextFile = inputTextFile;

		getStopwords();
		buildVocabulary();

	}
	private static class WordNode {
	
		private String word;
		private int frequency;


		public WordNode(String w, int  freq) {
			this.word = w;
			this.frequency = freq;
			
		}

	
	}

	/*
	 * This method populates the stopwords list from a file containing all
	 * stopwords, as pointed to by the member variable stopwordsFile. This file
	 * contains one stopword per line. You should be familiar with how to read from
	 * a file from the previous labs.
	 */

	private void getStopwords() {

		stopwords = new ArrayList<String>();
		BufferedReader input = null;
		
		// Reading from file
		try {
			input = new BufferedReader(new FileReader(stopwordsFile));
			String str = input.readLine();
			
			int counter = 0;
			while (str != null) {
				this.stopwords.add(str);
				//adding  word to stop words
				str = input.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/*
	 * This method populates the map vocabulary from a file containing the full text
	 * in plaintext format. Note that each word of the text is space delimited.
	 * Additionally, the text may contain non-alphanumeric characters like “?”, “--
	 * ”, and “)” which must be filtered out.
	 */
	private void buildVocabulary() {
		vocabulary = new HashMap<String, Integer>();
		String[] allWords;
		try {
			String reader = new String(Files.readAllBytes(Paths.get(inputTextFile))).toLowerCase()
					.replaceAll("[^a-zA-Z0-9 ]", " ");
			allWords = reader.split("\\s+"); 
			
			for (int i = 0; i < allWords.length; i++) {
				
				if (vocabulary.containsKey(allWords[i])) {
					int select = vocabulary.get(allWords[i]) + 1;
					vocabulary.put(allWords[i], select);
				} else {
					vocabulary.put(allWords[i], 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Used for debugging and getting value of a entry in the hashmap by grabbing
		 * its word
		 */
	}

	public int getFrequency(String word) {
		if(vocabulary.get(word) == null) {
			return -1;
		}
		return vocabulary.get(word);
	}

	public int getWordCount() {
		int counter = 0;
		// found using
		// https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html#entrySet--
		for (Map.Entry<String, Integer> selectedHashValue : vocabulary.entrySet()) {

			counter = counter + selectedHashValue.getValue();
		}
		return counter;
	}

	
	public int getNumUniqueWords() {
		int counter = 0;
		
			for (Map.Entry<String, Integer> selectedHashValue : vocabulary.entrySet()) {
					counter = counter +1;
					
			}
			
		
		
		
		return counter;
	}
	
	public void pruneVocabulary() {
		 for (String key : stopwords) {
			 if(vocabulary.containsKey(key)) {
				 vocabulary.remove(key);
			 }
		
	
		}
		
	}
	
	public ArrayList<String> getTopWords(int n){
        ArrayList<String> returnList = new ArrayList<String>(); 
		ArrayList<String> nonreverseList = new ArrayList<String>();
		PriorityQueue<WordNode> pq = new PriorityQueue<WordNode>(11, new compareWordNode());
		Comparator<WordNode> compareFunc;
		for (Map.Entry<String, Integer> selectedHashValue : vocabulary.entrySet()) {
			WordNode tempNode  = new WordNode(selectedHashValue.getKey(),selectedHashValue.getValue());
			pq.offer(tempNode);
			
	}
		for(int i = n;i > 0; i--) {
			nonreverseList.add(0, pq.poll().word);
		}
        for (int i = nonreverseList.size() - 1; i >= 0; i--) { 
            returnList.add(nonreverseList.get(i)); 
        } 
		
		return returnList;
	}
	
	protected class compareWordNode implements Comparator<WordNode> 
	{ 
	  
	    public int compare(WordNode a, WordNode b) 
	    { 
            if (a.frequency <  b.frequency) { 
                return 1; 
            }
            else if (a.frequency > b.frequency) {
                return -1; 
            }
                            return 0;  
	    } 
	}
	
	public void printStopWord() {
		for (String temp : stopwords) {
			System.out.println(temp);
		}

	}
	
	public int stringToHash(String word, WordFisher wf) {
		return wf.getFrequency(word);
		
	}
	public ArrayList<String> commonPopularWords(int n, WordFisher other){
		
		ArrayList<String> returnList = new ArrayList<String>();
		ArrayList<String> otherList = new ArrayList<String>();
		otherList =other.getTopWords(n);
		ArrayList<String> thisList = new ArrayList<String>();
		thisList =this.getTopWords(n);
		for(String temp : otherList) {
			if(thisList.contains(temp)) {
				returnList.add(temp);
			}
				
		}
		
		return returnList;
	}

	public static void main(String[] arg) {
		WordFisher moby = new WordFisher("moby-dick.txt", "stopwords.txt");
		WordFisher alice = new WordFisher("carroll-alice.txt", "stopwords.txt");
		System.out.println("Number of appearnces of 'whale 'in alice carroll       : " + alice.getFrequency("whale"));
		System.out.println("Number of appearnces of 'whale 'in moby dick           : " + moby.getFrequency("whale"));
		
		System.out.println("Number of appearnces of 'handkerchief'in alice carroll : " + alice.getFrequency("handkerchief"));
		System.out.println("Number of appearnces of 'handkerchief'in moby dick     : " + moby.getFrequency("handkerchief"));
		
		System.out.println("Number of  words in alice carroll                      : " + alice.getWordCount());
		System.out.println("Number of  words in moby dick                          : " + moby.getWordCount());
		
		System.out.println("Number of unique words in alice carroll                : " + alice.getNumUniqueWords());
		System.out.println("Number of unique words in moby dick                    : " + moby.getNumUniqueWords());
		
		moby.pruneVocabulary();
		alice.pruneVocabulary();
		System.out.println("Number of words in alice carroll after pruneVocabulary : " + alice.getWordCount());
		System.out.println("Number of words in moby dick after pruneVocabulary     : " + moby.getWordCount());
		System.out.println("Top(10) most popular in moby dick after pruneVocabulary: " + moby.getTopWords(10));
		System.out.println("Top(10) most popular in Alice after pruneVocabulary    : " + alice.getTopWords(10));
		System.out.println("Top(20) common popular words between moby and alice    :" + moby.commonPopularWords(20,alice));

	}

}
