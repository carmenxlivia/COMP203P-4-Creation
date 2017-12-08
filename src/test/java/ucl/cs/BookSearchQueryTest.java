package ucl.cs;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static ucl.cs.BookSearchQueryBuilder.books;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;
import ucl.cs.catalogues.BritishLibraryCatalogue;
import ucl.cs.catalogues.Catalogue;

public class BookSearchQueryTest {

    private static final List<Book> BOOKS = Arrays.asList(new Book("A Christmas Carol","Charles Dickens",1766));
    @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Catalogue catalogue = context.mock(Catalogue.class);

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorSurname() {

      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("LASTNAME='dickens' "); will(returnValue(BOOKS));
      }});

    List<Book> books = books().setSurname("dickens").build().execute(catalogue);

    assertThat(books, is(BOOKS));

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByAuthorFirstname() {


      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("FIRSTNAME='Jane' ");
      }});

    books().setFirstName("Jane").build().execute(catalogue);

  }

  @Test
  public void searchesForBooksInLibraryCatalogueByTitle() {

      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("TITLECONTAINS(Two Cities) ");
      }});

    List<Book> books = books().setTitleContent("Two Cities").build().execute(catalogue);


  }

  @Test
  public void searchesForBooksInLibraryCatalogueBeforeGivenPublicationYear() {


      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("PUBLISHEDBEFORE(1700) ");
      }});

    List<Book> books = books().setPublishedBefore(1700).build().execute(catalogue);


  }

  @Test
  public void searchesForBooksInLibraryCatalogueAfterGivenPublicationYear() {


      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("PUBLISHEDAFTER(1950) ");
      }});

    List<Book> books = books().setPublishedAfter(1950).build().execute(catalogue);


  }

  @Test
  public void searchesForBooksInLibraryCatalogueWithCombinationOfParameters() {

      context.checking(new Expectations() {{

          exactly(1).of(catalogue).searchFor("LASTNAME='dickens' PUBLISHEDBEFORE(1840) ");
      }});

    List<Book> books = books().setSurname("dickens").setPublishedBefore(1840).build().execute(catalogue);


  }
}
