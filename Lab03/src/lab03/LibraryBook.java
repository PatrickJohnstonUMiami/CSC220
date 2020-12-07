package lab03;
import java.util.GregorianCalendar;

// What imports do you need to include? Put them here.

public class LibraryBook extends Book { 
	
	// A LibraryBook contains a holder (a String) and due date represented by
	private String holder;
	// a GregorianCalendar
	private GregorianCalendar dueDate;
	
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		//when a book is created, it is assumed to be checked in
		this.holder = null;
		this.dueDate = null;

		// FILL IN
	}

	public String getHolder() {
		// FILL IN
		return this.holder; 	}
	
	public GregorianCalendar getDueDate() {
		// FILL IN
		return this.dueDate; // placeholder
	}
	
	public void checkin() {
		this.holder = null;
		this.dueDate = null;
		// FILL IN
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		this.holder = holder;
		this.dueDate = dueDate;
		// FILL IN
	}	

	// Do not override the equals method in Book

}