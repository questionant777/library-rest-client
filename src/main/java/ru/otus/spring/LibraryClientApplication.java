package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.FillBookService;

@SpringBootApplication
public class LibraryClientApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LibraryClientApplication.class, args);

		FillBookService fillBookService = ctx.getBean(FillBookService.class);

		String postfix = "1";

		fillBookService.insertBook(postfix,
				"book",
				"author",
				"genre"
				);
	}
}
