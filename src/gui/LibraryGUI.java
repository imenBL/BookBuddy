package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dbConnect.DatabaseHandler;
import models.Book;
import service.Librarian;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LibraryGUI {
    // Déplacez les champs et les boutons vers le haut pour qu'ils puissent être accessibles à partir de plusieurs méthodes
    private static JTextField titleField = new JTextField();
    private static JTextField authorField = new JTextField();
    private static JTextField isbnField = new JTextField();
    private static JTextField pubYearField = new JTextField();
    private static JTextField deleteBookField = new JTextField();
    private static JTextField updateBookIdField = new JTextField();
    private static JTextField getBookIdField = new JTextField();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Créer des instances de Librarian et DatabaseHandler
            DatabaseHandler dbHandler = new DatabaseHandler();
            Librarian librarian = new Librarian(dbHandler);

            // Créer un nouveau JFrame
            JFrame frame = new JFrame("BookBuddy: Library Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            // Créer un panneau principal pour contenir les autres composants
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout());

            // Créer un panneau pour l'en-tête
            JPanel headerPanel = createHeaderPanel();

            // Créer un panneau pour le contenu
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridLayout(7, 1)); // 7 lignes pour 7 groupes de composants

            // Ajouter des champs pour obtenir les détails du livre et les boutons pour effectuer des actions sur les livres
            contentPanel.add(createBookDetailsPanel());
            contentPanel.add(createAddBookButton(librarian));
            contentPanel.add(createDeleteBookPanel());
            contentPanel.add(createDeleteBookButton(librarian));
            contentPanel.add(createUpdateBookPanel());
            contentPanel.add(createUpdateBookButton(librarian));
            contentPanel.add(createGetBookPanel());
            contentPanel.add(createGetBookButton(librarian));
            contentPanel.add(createGetAllBooksButton(librarian));

            // Ajouter les panneaux d'en-tête et de contenu au panneau principal
            mainPanel.add(headerPanel, BorderLayout.NORTH);
            mainPanel.add(contentPanel, BorderLayout.CENTER);

            // Ajouter le panneau principal à la fenêtre
            frame.add(mainPanel);

            // Rendre la fenêtre visible
            frame.setVisible(true);
        });
    }

    // Méthodes pour créer des groupes de composants

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.GRAY);
        JLabel headerLabel = new JLabel("Welcome to BookBuddy");
        headerPanel.add(headerLabel);
        return headerPanel;
    }

    private static JPanel createBookDetailsPanel() {
        JPanel bookDetailsPanel = new JPanel(new GridLayout(4, 2));
        JLabel titleLabel = new JLabel("Title");
        bookDetailsPanel.add(titleLabel);
        bookDetailsPanel.add(titleField);

        JLabel authorLabel = new JLabel("Author");
        bookDetailsPanel.add(authorLabel);
        bookDetailsPanel.add(authorField);

        JLabel isbnLabel = new JLabel("ISBN");
        bookDetailsPanel.add(isbnLabel);
        bookDetailsPanel.add(isbnField);

        JLabel pubYearLabel = new JLabel("Publication Year");
        bookDetailsPanel.add(pubYearLabel);
        bookDetailsPanel.add(pubYearField);

        return bookDetailsPanel;
    }

    private static JButton createAddBookButton(Librarian librarian) {
        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int pubYear = Integer.parseInt(pubYearField.getText());
            Book newBook = new Book(title, author, isbn, pubYear, "Available");
            librarian.addBook(newBook);
        });
        return addBookButton;
    }

    private static JPanel createDeleteBookPanel() {
        JPanel deleteBookPanel = new JPanel(new GridLayout(1, 2));
        JLabel deleteBookLabel = new JLabel("Book ID to delete");
        deleteBookPanel.add(deleteBookLabel);
        deleteBookPanel.add(deleteBookField);
        return deleteBookPanel;
    }

    private static JButton createDeleteBookButton(Librarian librarian) {
        JButton deleteBookButton = new JButton("Delete Book");
        deleteBookButton.addActionListener(e -> {
            int bookId = Integer.parseInt(deleteBookField.getText());
            librarian.deleteBook(bookId);
        });
        return deleteBookButton;
    }

    private static JPanel createUpdateBookPanel() {
        JPanel updateBookPanel = new JPanel(new GridLayout(1, 2));
        JLabel updateBookIdLabel = new JLabel("Book ID to update");
        updateBookPanel.add(updateBookIdLabel);
        updateBookPanel.add(updateBookIdField);
        return updateBookPanel;
    }

    private static JButton createUpdateBookButton(Librarian librarian) {
        JButton updateBookButton = new JButton("Update Book");
        updateBookButton.addActionListener(e -> {
            int bookId = Integer.parseInt(updateBookIdField.getText());
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            int pubYear = Integer.parseInt(pubYearField.getText());
            Book updatedBook = new Book(title, author, isbn, pubYear, "Available");
            updatedBook.setId(bookId);
            librarian.updateBook(updatedBook);
        });
        return updateBookButton;
    }

    private static JPanel createGetBookPanel() {
        JPanel getBookPanel = new JPanel(new GridLayout(1, 2));
        JLabel getBookIdLabel = new JLabel("Book ID to get");
        getBookPanel.add(getBookIdLabel);
        getBookPanel.add(getBookIdField);
        return getBookPanel;
    }

    private static JButton createGetBookButton(Librarian librarian) {
        JButton getBookButton = new JButton("Get Book");
        getBookButton.addActionListener(e -> {
            int bookId = Integer.parseInt(getBookIdField.getText());
            Book book = librarian.getBook(bookId);

            if (book != null) {
                // Use HTML to format the book information
                String bookInfo = "<html>"
                    + "<h2>Book Information</h2>"
                    + "<p>ID: " + book.getId() + "</p>"
                    + "<p>Title: " + book.getTitle() + "</p>"
                    + "<p>Author: " + book.getAuthor() + "</p>"
                    + "<p>ISBN: " + book.getIsbn() + "</p>"
                    + "<p>Publication Year: " + book.getPublicationYear() + "</p>"
                    + "<p>Status: " + book.getStatus() + "</p>"
                    + "</html>";

                // Show the book information in a JOptionPane
                JOptionPane.showMessageDialog(null, bookInfo, "Book Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Show an error message if the book is not found
                JOptionPane.showMessageDialog(null, "No book found with ID: " + bookId, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        return getBookButton;
    }


    
    
    private static JButton createGetAllBooksButton(Librarian librarian) {
        JButton getAllBooksButton = new JButton("Get All Books");
        getAllBooksButton.addActionListener(e -> {
            List<Book> allBooks = librarian.getAllBooks();

            // Create a new JFrame for the book list
            JFrame bookListFrame = new JFrame("Book List");
            bookListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            bookListFrame.setSize(800, 600);

            // Create a table model and a table
            DefaultTableModel tableModel = new DefaultTableModel();
            JTable bookTable = new JTable(tableModel);

            // Add columns to the table model
            tableModel.addColumn("ID");
            tableModel.addColumn("Title");
            tableModel.addColumn("Author");
            tableModel.addColumn("ISBN");
            tableModel.addColumn("Publication Year");
            tableModel.addColumn("Status");

            // Add each book as a row in the table model
            for (Book book : allBooks) {
                tableModel.addRow(new Object[]{
                    book.getId(),
                    book.getTitle(),
                    book.getAuthor(),
                    book.getIsbn(),
                    book.getPublicationYear(),
                    book.getStatus()
                });
            }

            // Add the table to a JScrollPane and add the scroll pane to the frame
            bookListFrame.add(new JScrollPane(bookTable));

            // Show the frame
            bookListFrame.setVisible(true);
        });
        return getAllBooksButton;
    }

}
