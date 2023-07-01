package service;

import java.util.List;

import dbConnect.DatabaseHandler;
import models.Book;
import models.Member;

public class Librarian {
	
	
    private DatabaseHandler dbHandler;

    
    
    public Librarian(DatabaseHandler dbHandler) {
		super();
		this.dbHandler = dbHandler;
	}

	public Librarian() {
        this.dbHandler = new DatabaseHandler();
    }

    // Method to add a new book
    public void addBook(Book book) {
        dbHandler.addBook(book);
    }

    // Method to delete a book
    public void deleteBook(int bookId) {
        dbHandler.deleteBook(bookId);
    }
    
    // get book by id 
    public Book getBook(int id) {
    	return dbHandler.getBook(id);
    }
    
    
 // get book by id 
    public List<Book> getAllBooks() {
    	return dbHandler.getAllBooks();
    }

    // Method to update a book
    public void updateBook(Book book) {
        dbHandler.updateBook(book);
    }

    // Method to add a new member
    public void addMember(Member member) {
        dbHandler.addMember(member);
    }

    // Method to delete a member
    public void deleteMember(int memberId) {
        dbHandler.deleteMember(memberId);
    }

    // Method to update a member
    public void updateMember(Member member) {
        dbHandler.updateMember(member);
    }

    // Method to borrow a book
    public void borrowBook(int memberId, int bookId) {
        dbHandler.borrowBook(memberId, bookId);
    }

    // Method to return a book
    public void returnBook(int memberId, int bookId) {
        dbHandler.returnBook(memberId, bookId);
    }
}