package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.dto.AuthorDto;
import ru.otus.spring.domain.dto.BookCommentDto;
import ru.otus.spring.domain.dto.BookDto;
import ru.otus.spring.domain.dto.GenreDto;
import ru.otus.spring.service.rest.ObjRestTemplate;

@Service
public class FillBookServiceImpl implements FillBookService {
    private final String libraryWsUrl;
    private final HandleInOut handleInOut;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public FillBookServiceImpl(@Value("${libraryWsUrl}") String libraryWsUrl,
                               HandleInOut handleInOut,
                               CircuitBreakerFactory circuitBreakerFactory
    ) {
        this.libraryWsUrl = libraryWsUrl;
        this.handleInOut = handleInOut;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    public String insertGenre(String name) {
        return circuitBreakerFactory.create("insertGenre").run(
                () -> insertGenreBase(name),
                t -> "defaultGenre"
        );
    }

    private String insertGenreBase(String name) {
        GenreDto genreDto = new GenreDto(null, name);

        ObjRestTemplate<GenreDto> genreTemplate = new ObjRestTemplate<>();

        return genreTemplate.saveObj(genreDto,
                libraryWsUrl + "genres/");
    }

    public String insertAuthor(String name) {
        return circuitBreakerFactory.create("insertAuthor").run(
                () -> insertAuthorBase(name),
                t -> "defaultAuthor"
        );
    }

    private String insertAuthorBase(String name) {
        AuthorDto authorDto = new AuthorDto(null, name);

        ObjRestTemplate<AuthorDto> authorTemplate = new ObjRestTemplate<>();

        return authorTemplate.saveObj(authorDto,
                libraryWsUrl + "authors/");
    }

    public String insertBook(String name, String authorLocation, String genreLocation) {
        return circuitBreakerFactory.create("insertBook").run(
                () -> insertBookBase(name, authorLocation, genreLocation),
                t -> "defaultBook"
        );
    }

    private String insertBookBase(String name, String authorLocation, String genreLocation) {
        BookDto bookDto = new BookDto(null,
                name,
                authorLocation,
                genreLocation,
                null);

        ObjRestTemplate<BookDto> bookTemplate = new ObjRestTemplate<>();

        return bookTemplate.saveObj(bookDto,
                libraryWsUrl + "books/");
    }

    public String insertBookComment(String comment, String bookLocation) {
        return circuitBreakerFactory.create("insertBookComment").run(
                () -> insertBookCommentBase(comment, bookLocation),
                t -> "defaultBookComment"
        );
    }

    private String insertBookCommentBase(String comment, String bookLocation) {
        BookCommentDto bookCommentDto = new BookCommentDto(null, comment, bookLocation);

        ObjRestTemplate<BookCommentDto> genreTemplate = new ObjRestTemplate<>();

        return genreTemplate.saveObj(bookCommentDto,
                libraryWsUrl + "bookComments/");
    }

    @Override
    public void insertBook(String postfix, String bookName, String authorName, String genreName) {

        String authorLocation = insertAuthor("author" + postfix);
        String genreLocation = insertGenre("genre" + postfix);
        String bookLocation = insertBook("book" + postfix, authorLocation, genreLocation);

        handleInOut.outAndVk("Добавлена книга:");

        handleInOut.outAndVk(bookLocation);

        String bookCommentLocation1 = insertBookComment("comment" + postfix, bookLocation);
        String bookCommentLocation2 = insertBookComment("commentMore" + postfix, bookLocation);

        handleInOut.outAndVk("Добавлены комментарии к книге:");

        handleInOut.outAndVk(bookCommentLocation1);
        handleInOut.outAndVk(bookCommentLocation2);
    }
}
