package exam01;

public class Book {
    private int isbn;
    private String title;

    public Book(int isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Book () {}

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                '}';
    }
}
