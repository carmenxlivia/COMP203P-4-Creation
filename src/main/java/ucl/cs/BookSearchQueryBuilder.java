package ucl.cs;

public class BookSearchQueryBuilder {
    private String firstName = null;
    private String surname = null;
    private String title = null;
    private Integer publishedAfter = null;
    private Integer publishedBefore = null;

    private BookSearchQueryBuilder() {
    }

    public static BookSearchQueryBuilder books() {
        return new BookSearchQueryBuilder();
    }

    public BookSearchQueryBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public BookSearchQueryBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public BookSearchQueryBuilder setTitleContent(String title) {
        this.title = title;
        return this;
    }

    public BookSearchQueryBuilder setPublishedAfter(Integer publishedAfter) {
        this.publishedAfter = publishedAfter;
        return this;
    }

    public BookSearchQueryBuilder setPublishedBefore(Integer publishedBefore) {
        this.publishedBefore = publishedBefore;
        return this;
    }

    public BookSearchQuery build() {
        return new BookSearchQuery(firstName, surname, title, publishedAfter, publishedBefore);
    }
}