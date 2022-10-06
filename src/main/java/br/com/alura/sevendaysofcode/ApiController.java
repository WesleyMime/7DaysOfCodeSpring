package br.com.alura.sevendaysofcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ImdbClient imdbClient;

    @GetMapping("/api/")
    public Wrapper<Anime> get50Animes(Model model) {
        return imdbClient.get50Animes();
    }
}
