package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.FillBookService;
import ru.otus.spring.service.HandleInOut;

@SpringBootApplication
public class LibraryClientApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LibraryClientApplication.class, args);

		FillBookService fillBookService = ctx.getBean(FillBookService.class);
		HandleInOut handleInOut = ctx.getBean(HandleInOut.class);

		String postfix = "1";

		String authorLocation = fillBookService.insertAuthor("author" + postfix);
		String genreLocation  = fillBookService.insertGenre("genre" + postfix);
		String bookLocation   = fillBookService.insertBook("book" + postfix, authorLocation, genreLocation);

		handleInOut.outAndVk("Добавлена книга:");

		handleInOut.outAndVk(bookLocation);

		String bookCommentLocation1  = fillBookService.insertBookComment("comment" + postfix, bookLocation);
		String bookCommentLocation2  = fillBookService.insertBookComment("commentMore" + postfix, bookLocation);

		handleInOut.outAndVk("Добавлены комментарии к книге:");

		handleInOut.outAndVk(bookCommentLocation1);
		handleInOut.outAndVk(bookCommentLocation2);
	}

}
