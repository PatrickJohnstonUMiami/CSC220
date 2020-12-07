package lab12;
public class PacManTester {

    public static void main(String[] arg)
    {
    	Pacman dad = new Pacman("unsolvable.txt", "output.txt");
    	
    	dad.solveDFS();
    	
    	System.out.println(dad);
    	dad.writeOutput();
    	
    
   
   
  
}
}