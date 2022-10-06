package br.com.alura.sevendaysofcode.model;

import java.util.Collections;
import java.util.List;

public record Wrapper<T>(List<T> results, String errorMessage) {

    @Override
    public List<T> results() {
        return Collections.unmodifiableList(results);
    }
}
