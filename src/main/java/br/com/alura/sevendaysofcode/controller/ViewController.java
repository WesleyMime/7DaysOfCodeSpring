package br.com.alura.sevendaysofcode.controller;

import br.com.alura.sevendaysofcode.model.Anime;
import br.com.alura.sevendaysofcode.model.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ViewController {

    @Autowired
    private ApiController apiController;

    @GetMapping("/")
    public String get250Animes(Model model) {
        Wrapper<Anime> wrapper = apiController.get250Animes();
        model.addAttribute("wrapper", wrapper);
        model.addAttribute("title", "Top 250 Animes");
        return "animes";
    }

    @GetMapping("/{title}")
    public String getAnime(@PathVariable("title") String title, Model model) {
        Wrapper<Anime> wrapper = apiController.getAnimeByTitle(title);
        model.addAttribute("wrapper", wrapper);
        model.addAttribute("title", "Results for " + title);
        return "animes";
    }
}
