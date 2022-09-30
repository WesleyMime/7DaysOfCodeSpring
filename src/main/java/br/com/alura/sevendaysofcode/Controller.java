package br.com.alura.sevendaysofcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private ImdbClient imdbClient;

    @GetMapping("/")
    public Wrapper<Movie> get250Movies() {
        return imdbClient.get250Movies();
    }
}
