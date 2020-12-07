package lab03;
import java.util.Scanner;
public class SGTtest{
    public static void main(String[] args)
    {                 
        Scanner scan = new Scanner(System.in);
        /* Creating object of ScapeGoatTree */
        ScapeGoatTree sgt = new ScapeGoatTree(); 
        System.out.println("ScapeGoat Tree Test\n");          
        char ch;
        /*  Perform tree operations  */
        do    
        {
            System.out.println("\nScapeGoat Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. count nodes");
            System.out.println("3. search"); 
            System.out.println("4. check empty");
            System.out.println("5. make empty");
 
            int choice = scan.nextInt();            
            switch (choice)
            {
            case 1 : 
                System.out.println("Enter integer element to insert");
                sgt.add( scan.nextInt() );                     
                break;                                                    
            case 2 : 
                System.out.println("Nodes = "+ sgt.size());
                break; 
            case 3 : 
                System.out.println("Enter integer element to search");
                System.out.println("Search result : "+ sgt.search( scan.nextInt() ));
                break;                           
            case 4 :  
                System.out.println("Empty status = "+ sgt.isEmpty());
                break;
            case 5 :  
                System.out.println("\nTree cleared\n");
                sgt.makeEmpty();
                break;    
            case 6 :  
                System.out.println("\nTree cleared\n");
                sgt.printSideways();
                break; 
            default : 
                System.out.println("Wrong Entry \n ");
                break;           
            }
            /*  Display tree  */ 
            System.out.print("\nPost order : ");
            sgt.postorder();
            System.out.print("\nPre order : ");
            sgt.preorder();
            System.out.print("\nIn order : ");
            sgt.inorder();
 
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);                        
        } while (ch == 'Y'|| ch == 'y');               
    }
}