package com.stateless.autocomplete.autocompletion;

public class AutocompleteCandidate implements Comparable<AutocompleteCandidate> {

    private String string;
    private int frequency;

    public AutocompleteCandidate(String string, int frequency) {
        this.string = string;
        this.frequency = frequency;
    }

    public String getString() {
        return string;
    }

    @Override
    public int compareTo(AutocompleteCandidate o) {

        if (o.frequency != this.frequency)
            return o.frequency - this.frequency;

        return o.string.compareTo(this.string);
    }
}
