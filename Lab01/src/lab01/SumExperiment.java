package lab01;

public class SumExperiment {
	
	public static int checkSum(int[] array){

//intitializing function variables that count down from the end of the array and
// count up from the fron of the array
		int increasingArrayCounter = 0;
		int decreasingArrayCounter = array.length -1;
		int sum = 0;
		int returnFigure = 0;

		while(sum != 20) {
			sum = (array[increasingArrayCounter] + array[decreasingArrayCounter]);
			if(sum == 20) {
				if(array[increasingArrayCounter] > array[decreasingArrayCounter]) {
					returnFigure = decreasingArrayCounter;
				}else if(increasingArrayCounter == decreasingArrayCounter) {
					returnFigure = -1;
					sum = 20;
				}else {
					returnFigure = increasingArrayCounter; 
				}

			}else if(sum > 20){
				decreasingArrayCounter--;
			}else if(sum < 20) {
				increasingArrayCounter++;
			}
		}
		return returnFigure;
	}
	
	
	public static void main(String[] args) {
		
		int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
		if (checkSum(array1) != 0)
			System.err.println("TEST1 FAILED");

		
		int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
		if (checkSum(array2) != 1)
			System.err.println("TEST2 FAILED");

		
		int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
		if (checkSum(array3) != 2)
			System.err.println("TEST3 FAILED");
		
		int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
		if (checkSum(array4) != -1)
			System.err.println("TEST4 FAILED");

		System.out.println("Done!!!");
	}
}
