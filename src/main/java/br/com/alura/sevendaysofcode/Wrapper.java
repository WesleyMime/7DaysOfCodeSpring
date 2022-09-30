package br.com.alura.sevendaysofcode;

import java.util.Collections;
import java.util.List;

public record Wrapper<T>(List<T> items, String errorMessage) {

    @Override
    public List<T> items() {
        return Collections.unmodifiableList(items);
    }
}
