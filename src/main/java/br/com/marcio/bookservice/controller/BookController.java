package br.com.marcio.bookservice.controller;

import br.com.marcio.bookservice.model.Book;
import br.com.marcio.bookservice.proxy.CambioProxy;
import br.com.marcio.bookservice.repository.BookRepository;
import br.com.marcio.bookservice.response.Cambio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;

@Tag(name = "Book endpoints")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CambioProxy cambioProxy;
//    @GetMapping(value = "/{id}/{currency}")
//    public Book findBook(@PathVariable("id") Long id,@PathVariable("currency") String currency){
//
//        Book book = bookRepository.findById(id).orElseThrow( () ->new RuntimeException("Book not found."));
//        HashMap<String,String> params =  new HashMap<>();
//        params.put("amount",book.getPrice().toString());
//        params.put("from","USD");
//        params.put("to",currency);
//
//        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio-service" +
//                "/{amount}/{from}/{to}", Cambio.class,params);
//
//        Cambio cambio = response.getBody();
//
//        var port = environment.getProperty("local.server.port");
//        book.setPrice(cambio.getConvertedValue());
//        book.setEnvironment(port);
////        book.setCurrency(currency);
//        return book;
//    }

    @Operation(summary = "Find Book by Id")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(@PathVariable("id") Long id,@PathVariable("currency") String currency){

        Book book = bookRepository.findById(id).orElseThrow( () ->new RuntimeException("Book not found."));

        Cambio cambio = cambioProxy.getCambio(book.getPrice(),"USD",currency);

        var port = environment.getProperty("local.server.port");
        book.setPrice(cambio.getConvertedValue());
        book.setEnvironment("Book port: "+port +" Cambio port: "+cambio.getEnvironment());
//        book.setCurrency(currency);
        return book;
    }
}
