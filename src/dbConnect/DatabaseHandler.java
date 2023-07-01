package dbConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Book;
import models.Member;



public class DatabaseHandler {
    private String url = "jdbc:mysql://localhost:3306/bookbuddy";
    private String username = "root";
    private String password = "";
    private Connection conn;

    public DatabaseHandler() {
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        String sql = "INSERT INTO Books(title, author, isbn, publication_year, status) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setString(5, book.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(int bookId) {
        String sql = "DELETE FROM Books WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bookId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book) {
        String sql = "UPDATE Books SET title = ?, author = ?, isbn = ?, publication_year = ?, status = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setString(3, book.getIsbn());
            pstmt.setInt(4, book.getPublicationYear());
            pstmt.setString(5, book.getStatus());
            pstmt.setInt(6, book.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addMember(Member member) {
    String sql = "INSERT INTO Members(name, email) VALUES(?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, member.getName());
        pstmt.setString(2, member.getEmail());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void deleteMember(int memberId) {
    String sql = "DELETE FROM Members WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, memberId);
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void updateMember(Member member) {
    String sql = "UPDATE Members SET name = ?, email = ? WHERE id = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, member.getName());
        pstmt.setString(2, member.getEmail());
        pstmt.setInt(3, member.getId());
        pstmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void borrowBook(int memberId, int bookId) {
    String sqlUpdateBook = "UPDATE Books SET status = 'Checked Out' WHERE id = ?";
    String sqlInsertBorrow = "INSERT INTO BorrowedBooks(member_id, book_id, borrow_date, due_date) VALUES(?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 14 DAY))";
    try (PreparedStatement pstmtUpdateBook = conn.prepareStatement(sqlUpdateBook);
         PreparedStatement pstmtInsertBorrow = conn.prepareStatement(sqlInsertBorrow)) {
        pstmtUpdateBook.setInt(1, bookId);
        pstmtInsertBorrow.setInt(1, memberId);
        pstmtInsertBorrow.setInt(2, bookId);
        pstmtUpdateBook.executeUpdate();
        pstmtInsertBorrow.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void returnBook(int memberId, int bookId) {
    String sqlUpdateBook = "UPDATE Books SET status = 'Available' WHERE id = ?";
    String sqlDeleteBorrow = "DELETE FROM BorrowedBooks WHERE member_id = ? AND book_id = ?";
    try (PreparedStatement pstmtUpdateBook = conn.prepareStatement(sqlUpdateBook);
         PreparedStatement pstmtDeleteBorrow = conn.prepareStatement(sqlDeleteBorrow)) {
        pstmtUpdateBook.setInt(1, bookId);
        pstmtDeleteBorrow.setInt(1, memberId);
        pstmtDeleteBorrow.setInt(2, bookId);
        pstmtUpdateBook.executeUpdate();
        pstmtDeleteBorrow.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public Book getBook(int id) {
        String sql = "SELECT * FROM Books WHERE id = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                String isbn = rs.getString("isbn");
                int publicationYear = rs.getInt("publication_year");
                String status = rs.getString("status");

                Book book = new Book(title, author, isbn, publicationYear, status);
                book.setId(id);
                return book;
            } else {
                System.out.println("No book found with the given ID.");
                return null;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }


		public List<Book> getAllBooks() {
		        String sql = "SELECT * FROM Books";
		        List<Book> books = new ArrayList<>();
		        try {
		            Statement stmt = conn.createStatement();
		            ResultSet rs = stmt.executeQuery(sql);
		
		            while (rs.next()) {
		                int id = rs.getInt("id");
		                String title = rs.getString("title");
		                String author = rs.getString("author");
		                String isbn = rs.getString("isbn");
		                int publicationYear = rs.getInt("publication_year");
		                String status = rs.getString("status");
		
		                Book book = new Book(title, author, isbn, publicationYear, status);
		                book.setId(id);
		                books.add(book);
		            }
		        } catch (SQLException ex) {
		            System.out.println(ex.getMessage());
		        }
		        return books;
		    }
		}

