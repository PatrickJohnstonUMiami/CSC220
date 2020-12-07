package lab11;


public class QuadraticProbingHashTable
{
	
    public HashEntry [ ] HashTable;   // The array that holds the hash table
    public int currentSize;       // The number of occupied cells
  

	// constructor to create the HashTable of initial size = size
    // and sets all of its elements to null.
    public QuadraticProbingHashTable( int size )
    {


    	currentSize = 0;
  

    	HashTable =  new HashEntry [size];

    	
    }
    public double loader() {
    	return (1.0 + currentSize) / HashTable.length;
    }
 
    // insert into the hash table
    // if the item is already present, do nothing and simply return
    // be careful you might need to rehash - reshape when the load factor is .75
    // Hint: first check the load factor after add - if the size is beyond rehash first!
    public void insert( int x )
    {    
    	double tip = loader();
    	if(tip >= 0.75) {
    		rehash();
    	}
    	int spot = hash(x,HashTable.length);
    	HashEntry temp = HashTable[spot];
    	if( temp == null || false == temp.isActive  ) {
    		HashTable[spot] = new HashEntry(x);
    		currentSize = currentSize+1;
    	}
    	else {
    		quadProber(x, spot);
    	}
    	
    
    }

    // this function will increase the size of the hash table by a factor of two
    // and do the rehash of the current elements inside the hash table
    public void rehash( )
    {
     	int tempSize = HashTable.length * 2; 
     	QuadraticProbingHashTable  tempTable = new QuadraticProbingHashTable(tempSize); 
    	for (int x = 0; x <  HashTable.length; x++) {
    		if (HashTable[x] != null)
    			tempTable.insert(HashTable[x].element);
    	}
    	HashTable = tempTable.HashTable;
    	
    		
    	
    	
    	
    	
    	
    	
    	
    	// FILL IN
    }
    public void quadProber(int variable, int index) {
    	int eq = index;
    	int counter = 0;
    	HashEntry hashed = HashTable[eq];
   		
    	while(hashed != null && hashed.isActive){
    		counter = counter +1;
    		eq =(int)((index + Math.pow(counter, 2)) % HashTable.length);
    		hashed = HashTable[eq];
    	};
    	HashTable[eq] = new HashEntry(variable);
    	currentSize = currentSize +1;
    }
    
    // a simple hash function for int values
    // the hash value should be a number between 0 and tableSize-1
    // use the mod operator as suggested in class
    public int hash(int value, int tableSize )
    {
    	return Math.abs(value) % tableSize;
    	
    
    	// FILL IN - DO NOT RETURN -1
    
    }  
    

    // this function will remove an element from the hash table
    // remember you are not going to remove the element from the hash table (physcially)
    // instead you are supposed to make it inactive
    public void remove( int x )
    {
    	int spot = find(x);
    	
    	
    	if (-1 == spot) {
    		return;
    	}
    
    	HashTable[spot].isActive = false;
    	
    	
    	currentSize = currentSize -1;

    }

    // this function finds an element in the hash table
    // x is the value we are looking for
    // This function returns the index in which the value resides
    // if not in the hashtable return -1
    public int find( int x )
    {
    	int spot = hash(x,HashTable.length);
    	int eq = spot;
    	HashEntry temp = HashTable[spot];
    	if(temp.isActive  && temp != null && temp.element == x) {
    		return spot; 
    	}else {
    		int checker = 0;
    		while(temp != null && temp.isActive && temp.element != x) {
        		checker = checker +1;
        		eq = (int) ((spot + Math.pow(checker, 2)) % HashTable.length);
        		temp =HashTable[eq];
        		if (temp == null || checker>HashTable.length)
        			return -1;
    		}
    	}
    
    	
    	return eq;
    }
    
    
    // DO NOT CHNAGE THIS FUNCTION!
    public String toString(){
    	String toReturn = "";
    	for (int i = 0; i < HashTable.length; i++){
    		if (HashTable[i] == null){
    				toReturn += ("eF ");
    		}else{
    			if (HashTable[i].isActive)
    				toReturn += (HashTable[i].element + "T ");
    			else
    				toReturn += (HashTable[i].element + "F ");
    		}
    	}
    	return toReturn;
    }
    
    
    public static void main(String[] args){
    	
    	
    	// ********************* TESTS FOR LAB ****************************//
    	
    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
    		System.err.print("TEST FAILED: constructor ( 0 )");
    	System.out.println("Construct 1 works");
    
    	h1.insert(89);
    	h1.insert(58);
    	h1.insert(6);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
    		System.err.println("TEST FAILED: insert ( 1 )");
    	
    	h1.insert(16);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
    			System.err.println("TEST FAILED: insert ( 2 )");
    	
    	h1.insert(9);
    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
			System.err.println("TEST FAILED: insert ( 3 )");   
    	
    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
    	
    	h2.insert(0);
    	h2.insert(1);
    	h2.insert(2);
    	h2.insert(3);
    	h2.insert(4);
    	h2.insert(5);
    	
    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 4 )"); 
    	
    	System.out.println("Lab Testing Done!!!");
    	
    	
    	// ********************* TESTS FOR ASSIGNMENT ****************************//
    	
    	QuadraticProbingHashTable h3 = new QuadraticProbingHashTable(11);
    	
    	if (!h3.toString().equals("eF eF eF eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 5 )");    	    	
    	
    	h3.insert(44);    	
    	h3.insert(4);
    	
    	h3.remove(44);
    	
    	if (!h3.toString().equals("44F eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: remove ( 6 )");    	    	
    	
    	h3.insert(77);
    	
    	if (!h3.toString().equals("77T eF eF eF 4T eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 7 )");    	    	    	
    	
    	h3.insert(16);    	
    	h3.insert(28);
    	h3.insert(21);    	
    	h3.insert(11);    	
    	h3.insert(22);
    	h3.insert(33);  
    	
    	if (!h3.toString().equals("77T 11T eF 33T 4T 16T 28T eF eF 22T 21T "))
			System.err.println("TEST FAILED: insert ( 8 )");    	    	

    	h3.insert(55);
    	
    	if (!h3.toString().equals("22T eF eF eF 4T eF 28T eF eF eF eF 77T 11T eF eF 33T 16T eF eF eF 55T 21T "))
			System.err.println("TEST FAILED: insert ( 9 )");    	    	    	
    	
    	if (h3.find(4) != 4)
    		System.err.print("TEST FAILED: find ( 10 )");
    	
    	if (h3.find(44) != -1)
    		System.err.print("TEST FAILED: find ( 11 )");
    	
    	if (h3.find(77) != 11)
    		System.err.print("TEST FAILED: find ( 12 )");    
    	
    	System.out.println("Assignment Testing Done!!!");
    	
    }

}