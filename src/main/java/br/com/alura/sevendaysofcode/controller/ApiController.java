package br.com.alura.sevendaysofcode.controller;

import br.com.alura.sevendaysofcode.client.ImdbClient;
import br.com.alura.sevendaysofcode.model.Anime;
import br.com.alura.sevendaysofcode.model.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ApiController {

    private Wrapper<Anime> cached = new Wrapper<>();

    private final Set<Anime> favorites = new HashSet<>();

    @Autowired
    private ImdbClient imdbClient;

    @GetMapping("/api/")
    public Wrapper<Anime> get250Animes() {
        if (cached.getResults().isEmpty()) {
            Wrapper<Anime> animes = imdbClient.get250Animes();
            cached = animes;
            return animes;
        }
        return cached;
    }

    @GetMapping("/api/{title}")
    public Wrapper<Anime> getAnimeByTitle(@PathVariable("title") String title) {
        List<Anime> animeByTitle = get250Animes().getResults().stream().filter(anime -> {
            String animeTitleLow = anime.title().toLowerCase();
            String userTitleLow = title.toLowerCase();
            return animeTitleLow.contains(userTitleLow);
        }).toList();
        return new Wrapper<>(animeByTitle);
    }

    @GetMapping("/api/favorites")
    public Wrapper<Anime> getFavorites() {
        return new Wrapper<>(favorites);
    }

    @PostMapping("/api/favorites/{id}")
    public ResponseEntity<Wrapper<Anime>> postAnimeToFavorites(@PathVariable("id") String id) {
        Optional<Anime> animeOptional = findAnimeById(id, get250Animes().getResults());

        if (animeOptional.isEmpty()) {
            return new ResponseEntity<>(new Wrapper<>("Anime not found."), HttpStatus.NOT_FOUND);
        }
        Anime anime = animeOptional.get();
        favorites.add(anime);
        return new ResponseEntity<>(new Wrapper<>(List.of(anime)), HttpStatus.CREATED);
    }

    @DeleteMapping("/api/favorites/{id}")
    public ResponseEntity<Wrapper<Anime>> removeFromFavorites(@PathVariable("id") String id) {
        Optional<Anime> animeOptional = findAnimeById(id, favorites);

        if (animeOptional.isEmpty()) {
            return new ResponseEntity<>(new Wrapper<>("Anime not found."), HttpStatus.NOT_FOUND);
        }
        Anime anime = animeOptional.get();
        favorites.remove(anime);
        return new ResponseEntity<>(new Wrapper<>(List.of(anime)), HttpStatus.OK);
    }

    private Optional<Anime> findAnimeById(String id, Collection<Anime> collection) {
        return collection.stream().filter(anime -> {
            return anime.id().equals(id);
        }).findFirst();
    }
}
