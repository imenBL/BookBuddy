package models;
import java.util.ArrayList;
import java.util.List;
public class Member {
	
	    private int id;
	    private String name;
	    private String email;
	    private List<Book> borrowedBooks;

	    public Member() {
	        this.borrowedBooks = new ArrayList<>();
	    }

		public Member(int id, String name, String email, List<Book> borrowedBooks) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.borrowedBooks = borrowedBooks;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public List<Book> getBorrowedBooks() {
			return borrowedBooks;
		}

		public void setBorrowedBooks(List<Book> borrowedBooks) {
			this.borrowedBooks = borrowedBooks;
		}

		@Override
		public String toString() {
			return "Member [id=" + id + ", name=" + name + ", email=" + email + ", borrowedBooks=" + borrowedBooks
					+ "]";
		}

	    
	}


