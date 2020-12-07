package lab05;

public class Tester {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SortedBinarySet listSeq = new SortedBinarySet();
		System.out.println("Testing Sequential search");
		listSeq.usesBinarySearch = false;
		System.out.println("Creating array with insert method number -10 to 10");


		for (int i = -10; i <= 10; i++) {
			listSeq.insert(i);
		}
		System.out.println(listSeq);
		System.out.println("Does the array contain 0: " + listSeq.contains(0));
		System.out.println("");

		System.out.println("Does the array contain 2: " + listSeq.contains(2));
		System.out.println("");

		System.out.println("Does the array contain 3: " + listSeq.contains(3));
		System.out.println("");

		System.out.println("Does the array contain 10: " + listSeq.contains(10));
		System.out.println("");

		System.out.println("Does the array contain -20: " + listSeq.contains(-20));
		System.out.println("");

		double listContainsTrue[] = {1,3,0,-1,1,4,5};
		System.out.println("Does the array contain the numbers {1,3,0,-1,1,4,5} : " + listSeq.containsAll(listContainsTrue));
		System.out.println("");

		double listContainsFalse[] = {1,300,0,-1,1,4,90};
		System.out.println("Does the array contain the numbers  {1,300,0,-1,1,4,90}: " + listSeq.containsAll(listContainsFalse));
		System.out.println("");

		System.out.println("===Removing 10 from array: " + listSeq.remove(10) + "===");
		System.out.println("");

		System.out.println(listSeq);
		System.out.println("===Removing 0 from array: " + listSeq.remove(0)+ "===");
		System.out.println("");

		System.out.println(listSeq);
		System.out.println("===Removing 2 from array: " + listSeq.remove(2) + "===");
		System.out.println("");

		System.out.println(listSeq);
		System.out.println("===Removing -10 from array: " + listSeq.remove(-10) + "===");
		System.out.println("");

		System.out.println(listSeq);
		System.out.println("===Removing -1 from array: " + listSeq.remove(-1) + "===");
		System.out.println("");

		System.out.println(listSeq);
		System.out.println("===Removing -1000 from array ( shoulde be false) : " + listSeq.remove(-10000) + "===");
		System.out.println("");

		System.out.println(listSeq);

		
		SortedBinarySet listBinary = new SortedBinarySet();
		System.out.println("");

		System.out.println("Testing for binary search");
		listBinary.usesBinarySearch = true;
		System.out.println("Creating array with insert method number -10 to 10");


		for (int i = -10; i <= 10; i++) {
			listBinary.insert(i);
		}
		System.out.println(listBinary);
		System.out.println("");

		
		System.out.println("Does the array contain 0: " + listBinary.contains(0));
		System.out.println("");

		System.out.println("Does the array contain 2: " + listBinary.contains(2));
		System.out.println("");

		System.out.println("Does the array contain 3: " + listBinary.contains(3));
		System.out.println("");

		System.out.println("Does the array contain 10: " + listBinary.contains(10));
		System.out.println("");

		System.out.println("Does the array contain -20: " + listBinary.contains(-20));
		System.out.println("");

		System.out.println("Does the array contain the numbers {1,3,0,-1,1,4,5} : " + listBinary.containsAll(listContainsTrue));
		System.out.println("");

		System.out.println("Does the array contain the numbers  {1,300,0,-1,1,4,90}: " + listBinary.containsAll(listContainsFalse));
		System.out.println("");

		System.out.println("===Removing 10 from array: " + listBinary.remove(10) + "===");
		System.out.println("");

		System.out.println(listBinary);
		System.out.println("===Removing 0 from array: " + listBinary.remove(0)+ "===");
		System.out.println("");

		System.out.println(listBinary);
		System.out.println("===Removing 2 from array: " + listBinary.remove(2) + "===");
		System.out.println("");

		System.out.println(listBinary);
		System.out.println("===Removing -10 from array: " + listBinary.remove(-10) + "===");
		System.out.println("");

		System.out.println(listBinary);
		System.out.println("===Removing -1 from array: " + listBinary.remove(-1) + "===");
		System.out.println("");

		System.out.println(listBinary);
		System.out.println("===Removing -1000 from array ( shoulde be false) : " + listBinary.remove(-10000) + "===");
		System.out.println("");

		System.out.println(listBinary);



		System.out.println("");
		System.out.println("");
		System.out.println("");





	
		System.out.println("Testing Binary search with sorted BinarySet with a list of  {1,3,0,-1,45,1,4,5}");

double listTest[] = {1,3,0,-1,45,1,4,5};
SortedBinarySet list2 = new SortedBinarySet(listTest);
System.out.println(list2);
System.out.println("");

System.out.println("Testing sequential search with sorted BinarySet with a list of  {1,3,0,-1,45,1,4,5}");


SortedBinarySet list3 = new SortedBinarySet(listTest);
System.out.println(list3);
System.out.println("");

listBinary.usesBinarySearch = true;

System.out.println("testing which sorting method is fastest by making a list of 1000000 variables and seeing which can sort it the fastest");
System.out.println("First: Binary Search inserting into the numbers 0 - 1000000 into the sorted set and seeing how fast it is");
long nano_startTime = System.nanoTime(); 
list2.usesBinarySearch = true;

for(int i = 0; i <= 10000; i ++) {
	list2.insert(i);
}
long nano_endTime = System.nanoTime(); 
long binaryTime = (nano_endTime - nano_startTime);
System.out.println("Time taken for binary in nano seconds =: "
        + (binaryTime)); 
System.out.println("testing which sorting method is fastest by making a list of 1000000 variables and seeing which can sort it the fastest");
System.out.println("First: sequential Search inserting into the numbers 0 - 1000000 into the sorted set and seeing how fast it is");
long nano_startTime2 = System.nanoTime(); 
list3.usesBinarySearch = false;

for(int i = 0; i <= 100000; i ++) {
	list2.insert(i);
}
long nano_endTime2 = System.nanoTime(); 
long seqTime = (nano_endTime2 - nano_startTime2);
System.out.println("Time taken for sequential in nano seconds =: "
        + (seqTime)); 


if( seqTime > binaryTime) {
	System.out.println("sequential is faster");
}
if( seqTime < binaryTime) {
	System.out.println("binary is faster");
}
	}
	
	
}
