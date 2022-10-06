package br.com.alura.sevendaysofcode.client;

import br.com.alura.sevendaysofcode.model.Anime;
import br.com.alura.sevendaysofcode.model.Wrapper;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "imdb",
        url = "https://imdb-api.com/en/API",
        configuration = ImdbClientConfiguration.class)
public interface ImdbClient {

    @GetMapping("/AdvancedSearch/${imdb-key}?title_type=tv_series&genres=animation&countries=jp&count=250")
    Wrapper<Anime> get250Animes();
}
