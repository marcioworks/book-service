package br.com.marcio.bookservice.controller;

import br.com.marcio.bookservice.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,@PathVariable("currency") String currency){
        var port = environment.getProperty("local.server.port");
        return new Book(1L,"Marcio S Santos","Super dev",new Date(),30.0,currency,port);
    }
}
