package br.com.alura.sevendaysofcode.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Wrapper<T> {

    private final Collection<T> results;

    private final String errorMessage;

    public Wrapper() {
        this.results = List.of();
        this.errorMessage = null;
    }

    public Wrapper(Collection<T> results) {
        this.results = results;
        this.errorMessage = null;
    }

    public Wrapper(String errorMessage) {
        this.results = List.of();
        this.errorMessage = errorMessage;
    }

    public Collection<T> getResults() {
        return Collections.unmodifiableCollection(results);
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
