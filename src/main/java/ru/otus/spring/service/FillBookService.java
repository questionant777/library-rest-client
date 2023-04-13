package ru.otus.spring.service;

public interface FillBookService {
    String insertGenre(String name);
    String insertAuthor(String name);
    String insertBook(String name, String authorLocation, String genreLocation);
    String insertBookComment(String comment, String bookLocation);
}
