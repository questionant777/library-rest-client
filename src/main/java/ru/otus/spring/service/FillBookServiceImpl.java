package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.dto.AuthorDto;
import ru.otus.spring.domain.dto.BookDto;
import ru.otus.spring.domain.dto.GenreDto;
import ru.otus.spring.service.rest.ObjRestTemplate;

@Service
public class FillBookServiceImpl implements FillBookService {
    private final String libraryWsUrl;

    public FillBookServiceImpl(@Value("${libraryWsUrl}") String libraryWsUrl) {
        this.libraryWsUrl = libraryWsUrl;
    }

    public String insertAnyBook() {
        GenreDto genreDto = new GenreDto(null, "genre1712");

        ObjRestTemplate<GenreDto> genreTemplate = new ObjRestTemplate<>(GenreDto.class);

        String respGenre = genreTemplate.saveObj(genreDto,
                libraryWsUrl + "genres/");

        AuthorDto authorDto = new AuthorDto(null, "author1712");

        ObjRestTemplate<AuthorDto> authorTemplate = new ObjRestTemplate<>(AuthorDto.class);

        String respAuthor = authorTemplate.saveObj(authorDto,
                libraryWsUrl + "authors/");

        BookDto bookDto = new BookDto(null,
                "book1712",
                respAuthor,
                respGenre);

        ObjRestTemplate<BookDto> bookTemplate = new ObjRestTemplate<>(BookDto.class);

        String respBook = bookTemplate.saveObj(bookDto,
                libraryWsUrl + "books/");

        return respBook;
    }
}
