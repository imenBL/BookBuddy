package models;
import javax.swing.table.DefaultTableModel;
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private String status;
    
    
	public Book(String title, String author, String isbn, int publicationYear, String status) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.status = status;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getPublicationYear() {
		return publicationYear;
	}


	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", publicationYear="
				+ publicationYear + ", status=" + status + "]";
	}
	
	
	
	

    
}