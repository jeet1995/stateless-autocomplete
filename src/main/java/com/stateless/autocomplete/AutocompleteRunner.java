package com.stateless.autocomplete;

import com.stateless.autocomplete.loader.CorpusLoader;

import java.util.HashMap;

public class AutocompleteRunner {

    public static void main(String[] args) {

        CorpusLoader corpusLoader = CorpusLoader.createInstance();

        corpusLoader.loadCorpusDataFromFile("corpus.txt");

        HashMap<String, Integer>[] array = corpusLoader.getCorpusEntryWithFrequencyHashMapArray();

        System.out.println();

    }
}
