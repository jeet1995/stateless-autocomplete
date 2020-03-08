package com.stateless.autocomplete;

import java.util.HashMap;

public class ResultsGenerator {

    private static ResultsGenerator resultsGenerator;

    private HashMap<String, Integer>[] corpusMapArray;

    private ResultsGenerator(HashMap[] corpusMapArray) {
        this.corpusMapArray = corpusMapArray;
    }

    public static ResultsGenerator createInstance(HashMap[] corpusMapArray) {

        if (resultsGenerator == null)
            resultsGenerator = new ResultsGenerator(corpusMapArray);

        return resultsGenerator;
    }

    public boolean processQuery(QueryProcessor processor, String query) {
        return processor.processQuery(query);
    }
}
