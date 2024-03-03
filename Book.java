package Library;

/**
 * Name:Lidor Pahima
 * Book Class represent the Book information :Book name,Author name ,UniqueID and quantity
 */
public class Book {
    private String name;
    private String author;
    private final int uniqueID;
    private int quantity ;

    /**
     * Constructor for new book.
     * @param name
     * @param author
     */
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
        this.uniqueID = Library.idx;
        this.quantity = 1;
    }

    /**
     * To String book information
     * @return
     */
    @Override
    public String toString(){
        return "Name:"+this.name+" Author:"+this.author;
    }

    /**
     * Return the name of the book
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Get book object by UniqueID
     * @param UniqueID
     * @return
     */
    public Book getBookByID(int UniqueID) {
        for (int i = 0; i < Library.Library.size(); i++) {
            Book book = Library.Library.getData(i);
            if (book.uniqueID == UniqueID) {
                return book;
            }
        }
        return null;
    }

    /**
     * Get Author name
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Get UniqueID
     * @return
     */
    public int getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Get Quantity
     * @return
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Set Quantity
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
