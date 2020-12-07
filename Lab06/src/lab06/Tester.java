package lab06;

public class Tester {
	public static void main(String[] args) {
		SortedString brain = new SortedString("brain");
		SortedString brian = new SortedString("brian");
		SortedString abaccus = new SortedString("abaccus");
		SortedString zxec = new SortedString("zxec");
		SortedString zebra = new SortedString("zebra");
		SortedString joy = new SortedString("joy");
		SortedString ski = new SortedString("ski");
		SortedString elbow = new SortedString("Elbow");
		SortedString below = new SortedString("below");
		SortedString empty = new SortedString("");
		SortedString fed= new SortedString("fed");
		SortedString cat= new SortedString("cat");


	       String [] tester1 = {"jack", "anthony", "dabe" };


	       SortedString.toSortedString(tester1);



		System.out.println("compare");
		System.out.println(joy.compareTo(ski));
		System.out.println(elbow.compareTo(below));
		
			Integer num [] = {20,10,30,50, 300,400,-1};
	       SortedString [] tester = { brain, abaccus, ski, joy, below, empty  };
	      SortedString [] stringTest = { joy ,ski,fed, cat};


		
	System.out.println(AnagramUtil.areAnagrams(below,empty));
	System.out.println(AnagramUtil.areAnagrams(below,elbow));
	System.out.println(AnagramUtil.areAnagrams(brian,elbow));
	System.out.println(AnagramUtil.areAnagrams(brian,brain));
	System.out.println(AnagramUtil.areAnagrams(joy,joy));
    InsertionSort<SortedString> k =new InsertionSort<>();
    InsertionSort<Integer> o =new InsertionSort<>();
    InsertionSort<String> s =new InsertionSort<>();
    MergeSort<Integer> g =new MergeSort<>();

stringTest = k.sort(stringTest);
    
  Integer numOut[];

for(int i = 0; i <stringTest.length ; i++) {
	System.out.println(stringTest[i]);
}
o.fit(num);

System.out.println(o.predict(1000));
System.out.println(o.predict(10));
System.out.println(o.predict(100));
System.out.println(o.predict(10000) + "is the amount for n = 1000000 ");

String[] s3 = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
for(int i = 0; i < s3.length; i ++) {
	System.out.println(s3[i]);
}

Integer numOut2[];
g.fit(num);
System.out.println("mergesort time");

numOut2 = g.sort(num);
for(int i = 0; i < num.length ; i++) {
	System.out.println(numOut2[i]);
}
System.out.println(g.predict(1000));
System.out.println(g.predict(10));
System.out.println(g.predict(100));
System.out.println(g.predict(10000)+ "is the amount for n = 1000000 ");
}









	}


