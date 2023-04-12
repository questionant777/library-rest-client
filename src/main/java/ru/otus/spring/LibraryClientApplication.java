package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import ru.otus.spring.service.FillBookService;
import ru.otus.spring.service.HandleInOut;

@SpringBootApplication
public class LibraryClientApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(LibraryClientApplication.class, args);

		FillBookService fillBookService = ctx.getBean(FillBookService.class);
		HandleInOut handleInOut = ctx.getBean(HandleInOut.class);

		handleInOut.outAndVk("Добавлена книга:");

		handleInOut.outAndVk(fillBookService.insertAnyBook());
	}

}
