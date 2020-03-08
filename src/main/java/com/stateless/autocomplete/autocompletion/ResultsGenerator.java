package com.stateless.autocomplete.autocompletion;

import com.stateless.autocomplete.verifier.QueryVerifier;

import java.util.*;

public class ResultsGenerator {

    private static ResultsGenerator resultsGenerator;

    private ResultsGenerator() {
    }

    public static ResultsGenerator createInstance() {

        if (resultsGenerator == null)
            resultsGenerator = new ResultsGenerator();

        return resultsGenerator;
    }

    public boolean verifyQuery(QueryVerifier queryVerifier, String query) {
        return queryVerifier.verifyQuery(query);
    }

    public List<String> generateAutocompleteStrings(HashMap[] corpusMapArray, String action, String prefix, Integer
            maxCount) {

        List<String> autocompletionStrings = new ArrayList<>();
        PriorityQueue<AutocompleteCandidate> candidates = new PriorityQueue<>();

        if (action.equals("complete")) {

            HashMap<String, Integer> corpusMap = corpusMapArray[prefix.charAt(0) - 'a'];

            for (Map.Entry<String, Integer> entry : corpusMap.entrySet())
                if (entry.getKey().indexOf(prefix) == 0)
                    candidates.offer(new AutocompleteCandidate(entry.getKey(), entry.getValue()));

        }

        for (int i = 0; i < maxCount; i++)
            autocompletionStrings.add(candidates.poll().getString());

        return autocompletionStrings;
    }

}
