package br.com.alura.sevendaysofcode.controller;

import br.com.alura.sevendaysofcode.client.ImdbClient;
import br.com.alura.sevendaysofcode.model.Anime;
import br.com.alura.sevendaysofcode.model.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ApiController {

    private Wrapper<Anime> cached = new Wrapper<>(List.of(), "empty");

    @Autowired
    private ImdbClient imdbClient;

    @GetMapping("/api/")
    public Wrapper<Anime> get250Animes() {
        if (cached.results().isEmpty()) {
            Wrapper<Anime> animes = imdbClient.get250Animes();
            cached = animes;
            return animes;
        }
        return  cached;
    }

    @GetMapping("/api/{title}")
    public Wrapper<Anime> getAnimeByTitle(@PathVariable("title") String title) {
        List<Anime> animeByTitle = get250Animes().results().stream().filter(anime -> {
            return anime.title().contains(title);
        }).toList();

        return new Wrapper<>(animeByTitle, null);
    }
}
