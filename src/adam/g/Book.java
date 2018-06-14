package adam.g;

import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private String author;
    private int year;
    private String isbn;



    public Book(int id, String title, String author, int year, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    public Book(String title, String author, int year, String isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        final StringBuilder newString = new StringBuilder("Book{");
        newString.append("id=").append(id);
        newString.append(", title='").append(title).append('\'');
        newString.append(", author='").append(author).append('\'');
        newString.append(", year=").append(year);
        newString.append(", isbn=").append(isbn);
        newString.append('}');
        return newString.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }


}
