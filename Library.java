package Library;

import java.util.ArrayList;

import java.util.List;

/**
 * Name:Lidor Pahima
 * Library class represents a books collection with available and borrowed with functionality to add books,remove,borrow,return,total books and loaned
 */
public class Library {
    public static int idx = 0; // uniqueID
    static DataStructure<Book> Library; //Library structure
    DataStructure <Book> BorrowedBooks ; //Borrowed from library structure
    /**
     * Constructs a new Library
     */
    public Library() {
        Library = new DataStructure<Book>();
        BorrowedBooks = new DataStructure<Book>();
    }
    /**
     * Adds a book to the library. If the book already exists (based on name and author),
     * increments its quantity. Otherwise, adds a new book entry.
     *
     * @param name   The name of the book.
     * @param author The author of the book.
     */
    public void addBook(String name, String author) {
        boolean isFound = false;
        for (int i = 0; i < Library.size(); i++) {
            Book book = Library.getData(i);
            if (book.getName().equals(name) && book.getAuthor().equals(author)) {
                book.setQuantity(book.getQuantity() + 1);
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            Book newBook = new Book(name, author);
            Library.addToEnd(newBook);
            idx++;
        }
    }
    /**
     * Removes a book from the library based on its unique ID. If the book is not found,will print "Book not found".
     *
     * @param uniqueID The unique ID of the book to remove.
     */
public void removeBooks(int uniqueID) {
    for(int i = 0 ; i < Library.size() ; i++) {
        Book book = Library.getData(i);
                if(book.getUniqueID() == uniqueID){
                    Library.delete(book);
                    return;
                }
    }
    System.out.println("Book not found");
}
    /**
     * Borrows a book from the library by its unique ID. If the book is available,
     * its quantity will be -1 and handles the borrowed book tracking.
     *
     * @param uniqueID The unique ID of the book will borrow.
     * @return The borrowed Book object, or null if the borrow is not successful.
     */
    public Book borrowBook(int uniqueID){
        for (int i = 0; i < Library.size(); i++) {
            Book book = Library.getData(i);
            if (book.getUniqueID() == uniqueID && book.getQuantity() > 0) {

                book.setQuantity(book.getQuantity() - 1);

                for (int j = 0; j < BorrowedBooks.size(); j++) {
                    Book borrowedBook = BorrowedBooks.getData(j);
                    if (borrowedBook.getUniqueID() == uniqueID) {

                        borrowedBook.setQuantity(borrowedBook.getQuantity() + 1);
                        return borrowedBook;
                    }
                }

                Book newBorrowedBook = new Book(book.getName(), book.getAuthor());
                newBorrowedBook.setQuantity(1);
                BorrowedBooks.addToEnd(newBorrowedBook);
                return newBorrowedBook;
            }
        }

        return null;
    }

    /**
     * Borrows all books of a given author from the library. Reduces the available quantity to 0
     * and adds the books to the borrowed books list.
     *
     * @param author_name The name of the author whose books to borrow.
     * @return A list of Book objects that were borrowed.
     */
    public List<Book> borrowAllBooks(String author_name){
        List<Book> BorrowAllBooks = new ArrayList<Book>();
        for(int i = 0 ; i < Library.size() ; i++){
            Book book = Library.getData(i);
            {
                if(book.getAuthor().equals(author_name)){
                    BorrowedBooks.addToEnd(book);
                    Library.getData(i).setQuantity(0);
                    }
        }

    }
        return BorrowAllBooks;
    }
    /**
     * Sorts the library books by their unique IDs.
     */
    public void sortedByUniqueID(){
        DataStructure<Book> sortedArray = new DataStructure<Book>();
        Book book = Library.getData(0) ;

        for(int i = 0 ; i < Library.size() ; i++) {
            if(book.getBookByID(i) != null){
            sortedArray.addToEnd(book.getBookByID(i));
        }}
        Library.clear();
        for(int i = 0 ; i < sortedArray.size() ; i++){
            if (sortedArray.getData(i) != null) {
                Library.addToEnd(sortedArray.getData(i));
        }
    }
    }
    /**
     * Calculates the total number of books in the library, including both available and borrowed books(Available+borrowed = total books).
     * @return The total number of books.
     */
    public int totalBooksInLibrary() {
        int Count = 0;
        for (int i = 0; i < Library.size(); i++) {
            Book availablebooks = Library.getData(i);
            Count += availablebooks.getQuantity();
        }
        for (int i = 0; i < BorrowedBooks.size(); i++) {
            Book loanedbooks = BorrowedBooks.getData(i);
            Count += loanedbooks.getQuantity();
        }
        return Count;
    }

    /**
     * Calculates the total number of available books for loan in the library.
     *
     * @return The total number of available books.
     */
    public int totalAvailableBooks(){
        int Count = 0;
        for (int i = 0; i < Library.size(); i++) {
            Book book = Library.getData(i);
            Count += book.getQuantity();
        }
        return Count;
    }
    /**
     * Calculates the total number of books currently loaned from the library.
     * @return The total number of loaned books.
     */
    public int totalLoanBooks(){
        int Count = 0;
        for (int i = 0; i < BorrowedBooks.size(); i++) {
            Book loanedbooks = BorrowedBooks.getData(i);
            Count += loanedbooks.getQuantity();
        }
        return Count;
    }
    /**
     * Finds the author with the most books in the library.
     * @return The name of the author who has the most books.
     */
    public String authorWithMostBooks() {
        String authorWithMaxBooks = null;
        int maxCount = 0;

        for (int i = 0; i < Library.size(); i++) {
            Book book = Library.getData(i);
            String author = book.getAuthor();
            int count = 0;

            for (int j = 0; j < Library.size(); j++) {
                Book bookCompare = Library.getData(j);
                if (author.equals(bookCompare.getAuthor())) {
                    count++;
                }
                if(maxCount <= count){
                    authorWithMaxBooks = author;
                    maxCount = count;
                }
            }}
    return authorWithMaxBooks;
    }

    /**
     * Returns a book to the library based on its unique ID. Will change the book quantity from library and borrowed structure.
     *
     * @param uniqueID The unique ID of the book to return.
     * @return true if the book was successfully returned, false otherwise.
     */
    public boolean returnBook(int uniqueID){
        // חיפוש הספר ברשימת ההשאלות
        Book bookInBorrowed = null;
        for (int i = 0; i < BorrowedBooks.size(); i++) {
            Book book = BorrowedBooks.getData(i);
            if (book.getUniqueID() == uniqueID) {
                bookInBorrowed = book;
                bookInBorrowed.setQuantity(bookInBorrowed.getQuantity()-1);
                break;
            }
        }

        if (bookInBorrowed == null) {
            return false;
        }
        Book bookInLibrary = null;
        for (int i = 0; i < Library.size(); i++) {
            Book book = Library.getData(i);
            if (book.getUniqueID() == uniqueID) {
                bookInLibrary = book;
                break;
            }
        }
        if (bookInLibrary != null) {
            bookInLibrary.setQuantity(bookInLibrary.getQuantity() + 1);

            return true;
        } else {
       Library.addToEnd(bookInLibrary);
       return true;
        }
    }
    /**
     * Checks if a book with a given unique ID is currently borrowed.
     * @param uniqueID The unique ID of the book to check.
     * @return true if the book is borrowed, false otherwise.
     */
    public boolean isBorrowed(int uniqueID){
        Book ReturnBook = BorrowedBooks.getData(0);
        ReturnBook.getBookByID(uniqueID);
        if(ReturnBook != null && ReturnBook.getQuantity() > 0) {
        return true;
        }
        return false;
    }
    }
