package br.com.marcio.bookservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("book-service")
public class FoobarController {

    private Logger logger = LoggerFactory.getLogger(FoobarController.class);
    @GetMapping("/foo-bar")
    @Retry(name= "foo-bar")
    public String foobar(){
        logger.info("Request to foo-bar is received!");
        var response = new RestTemplate().getForEntity("http://localhost:8080/foo-bar",String.class);
//        return "foo-bar";
        return response.getBody();
    }
}