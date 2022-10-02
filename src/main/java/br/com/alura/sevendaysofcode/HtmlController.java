package br.com.alura.sevendaysofcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @Autowired
    private ImdbClient imdbClient;

    @GetMapping("/")
    public String get50Animes(Model model) {
        Wrapper<Anime> wrapper = imdbClient.get50Animes();
        model.addAttribute("wrapper", wrapper);
        return "animes";
    }
}
