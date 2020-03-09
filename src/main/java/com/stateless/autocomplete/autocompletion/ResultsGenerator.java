package com.stateless.autocomplete.autocompletion;

import com.stateless.autocomplete.verifier.QueryVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * This class has 2 key functionality :
 * <ol>
 * <li>
 * To verify the query to execute.
 * </li>
 * <li>
 * To generate autocompletion results of the verified query
 * </li>
 * </ol>
 */
public class ResultsGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultsGenerator.class);
    private static ResultsGenerator resultsGenerator;

    private ResultsGenerator() {
    }

    public static ResultsGenerator createInstance() {

        if (resultsGenerator == null)
            resultsGenerator = new ResultsGenerator();

        return resultsGenerator;
    }

    public boolean verifyQuery(QueryVerifier queryVerifier, String query) {

        LOGGER.info("Attempting to verify the query {}", query);
        return queryVerifier.verifyQuery(query);
    }

    /**
     * Generates a <code>{@link List}</code> of strings denoting the autocompletion results
     *
     * @param corpusMapArray Stores the strings specified in the input corpus file
     * @param action         Action to be performed on the corpus
     * @param prefix         The prefix which is used to determine the autocompletion results
     * @param maxCount       The max. no. of strings present in the autocompletion results
     * @return <code>List</code> of autocompletion strings
     */
    public List<String> generateAutocompleteStrings(HashMap[] corpusMapArray, String action, String prefix, Integer
            maxCount) {

        LOGGER.info("Generating autocomplete strings.");

        List<String> autocompletionStrings = new ArrayList<>();

        // Stores autocompletion candidate in an ordering specified in the implementation
        // of <<code>AutocompleteCandidate</code>
        PriorityQueue<AutocompleteCandidate> candidates = new PriorityQueue<>();

        if (action.equals("complete")) {

            HashMap<String, Integer> corpusMap = corpusMapArray[prefix.charAt(0) - 'a'];

            // Check possible string values of which starts with prefix and add to candidates
            for (Map.Entry<String, Integer> entry : corpusMap.entrySet())
                if (entry.getKey().indexOf(prefix) == 0)
                    candidates.offer(new AutocompleteCandidate(entry.getKey(), entry.getValue()));

        }

        int i = 1;

        // Extract top maxCount candidates and add it to a list
        while (i <= maxCount && !candidates.isEmpty()) {
            autocompletionStrings.add(candidates.poll().getString());
            i++;
        }

        return autocompletionStrings;
    }

}
