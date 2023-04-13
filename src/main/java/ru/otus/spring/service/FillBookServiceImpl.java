package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.dto.AuthorDto;
import ru.otus.spring.domain.dto.BookCommentDto;
import ru.otus.spring.domain.dto.BookDto;
import ru.otus.spring.domain.dto.GenreDto;
import ru.otus.spring.service.rest.ObjRestTemplate;

@Service
public class FillBookServiceImpl implements FillBookService {
    private final String libraryWsUrl;

    public FillBookServiceImpl(@Value("${libraryWsUrl}") String libraryWsUrl) {
        this.libraryWsUrl = libraryWsUrl;
    }

    public String insertGenre(String name) {
        GenreDto genreDto = new GenreDto(null, name);

        ObjRestTemplate<GenreDto> genreTemplate = new ObjRestTemplate<>();

        return genreTemplate.saveObj(genreDto,
                libraryWsUrl + "genres/");
    }

    public String insertAuthor(String name) {
        AuthorDto authorDto = new AuthorDto(null, name);

        ObjRestTemplate<AuthorDto> authorTemplate = new ObjRestTemplate<>();

        return authorTemplate.saveObj(authorDto,
                libraryWsUrl + "authors/");
    }

    public String insertBook(String name, String authorLocation, String genreLocation) {
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
        BookCommentDto bookCommentDto = new BookCommentDto(null, comment, bookLocation);

        ObjRestTemplate<BookCommentDto> genreTemplate = new ObjRestTemplate<>();

        return genreTemplate.saveObj(bookCommentDto,
                libraryWsUrl + "bookComments/");
    }
}
