package com.stateless.autocomplete.executor;

import com.stateless.autocomplete.autocompletion.ResultsGenerator;

import java.util.HashMap;
import java.util.List;

public class QueryExecutor {

    private static QueryExecutor queryExecutor;

    private QueryExecutor() {
    }

    public static QueryExecutor createInstance() {
        if (queryExecutor == null)
            queryExecutor = new QueryExecutor();

        return queryExecutor;
    }

    /**
     * Executes the query supplied
     * @param resultsGenerator Generates results pertaining to the query
     * @param corpusMapArray Array of <code>{@link HashMap}</code> on which the query results are obtained
     * @param action The action which qualifies the query
     * @param prefix The prefix with which the results begin with
     * @param maxCount Max no. of results to be obtained
     * @return <code>{@link List}</code> of strings
     * */
    public List<String> execute(ResultsGenerator resultsGenerator, HashMap[] corpusMapArray, String action, String
            prefix, Integer maxCount) {
        return resultsGenerator.generateAutocompleteStrings(corpusMapArray, action, prefix, maxCount);
    }
}
