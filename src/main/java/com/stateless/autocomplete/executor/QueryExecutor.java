package com.stateless.autocomplete.executor;

import com.stateless.autocomplete.autocompletion.ResultsGenerator;

import java.util.HashMap;
import java.util.List;

public class QueryExecutor {

    private static QueryExecutor queryExecutor;

    private QueryExecutor() {}

    public static QueryExecutor createInstance() {
        if (queryExecutor == null)
            queryExecutor = new QueryExecutor();

        return queryExecutor;
    }

    public List<String> execute(ResultsGenerator resultsGenerator, HashMap[] corpusMapArray, String action, String prefix, Integer maxCount) {
        return resultsGenerator.generateAutocompleteStrings(corpusMapArray, action, prefix, maxCount);
    }
}
