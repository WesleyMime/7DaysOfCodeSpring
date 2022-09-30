package br.com.alura.sevendaysofcode;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "imdb",
        url = "https://imdb-api.com/en/API",
        configuration = ImdbClientConfiguration.class)
public interface ImdbClient {

    @GetMapping("/Top250Movies/${imdb-key}")
    Wrapper<Movie> get250Movies();
}
