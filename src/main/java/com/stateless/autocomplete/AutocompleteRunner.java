package com.stateless.autocomplete;

import com.stateless.autocomplete.autocompletion.ResultsGenerator;
import com.stateless.autocomplete.executor.QueryExecutor;
import com.stateless.autocomplete.loader.CorpusLoader;
import com.stateless.autocomplete.utils.MessageUtils;
import com.stateless.autocomplete.utils.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * This class is the entry point to the program.
 */
public class AutocompleteRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutocompleteRunner.class);

    public static void main(String[] args) {
        run();
    }

    /**
     * Starts the autocompletion engine
     */
    private static void run() {

        LOGGER.info("Starting the application.");

        // Print introductory instructional message
        printIntroductionMessage();

        // Get loaded corpus
        HashMap<String, Integer>[] corpusMapArray = CorpusLoader.createInstance().loadCorpusDataFromFile("corpus.txt");

        ResultsGenerator resultsGenerator = ResultsGenerator.createInstance();

        QueryExecutor queryExecutor = QueryExecutor.createInstance();

        Scanner in = new Scanner(System.in);

        String choice = "";

        do {

            // Type in the query through standard input
            String query = in.nextLine();

            // Check if the query is of proper format
            if (canExecute(resultsGenerator, query)) {

                String[] queryParts = query.split(",");

                String action = queryParts[0].toLowerCase().trim();
                String prefix = queryParts[1].toLowerCase().trim();
                Integer maxCount = MethodUtils.getIntValue(queryParts[2].trim());

                // Execute the autocompletion query
                List<String> autocompletionStrings = queryExecutor.execute(resultsGenerator, corpusMapArray, action,
                        prefix, maxCount);

                // Display the autocompletion results
                System.out.println(autocompletionStrings.stream().collect(Collectors.joining(",")) + "\n");

            }
            else {
                System.out.println("Continuing the application");
            }

            System.out.println("Do you wish to continue (Y/N) ?");

            choice = in.nextLine();

        } while (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes"));

    }

    private static void printIntroductionMessage() {

        System.out.println("\n" +
                "|===============================================================================================================|\n" +
                "|                                         Autocompletion Engine                                                 |\n" +
                "|===============================================================================================================|\n" +
                "|         This application allows the client to obtain possible autocomplete results for some query.            |\n" +
                "|===============================================================================================================|\n" +
                "|The client has the following options :                                                                         |\n" +
                "|  1. Enter a search query in the form : complete,<prefix>,<max_count>                                          |\n" +
                "|  2. Continue/Exit the application by entering Y or N through standard input                                   |\n" +
                "|===============================================================================================================|");

    }

    /**
     * Checks the format of the query for it to be executed
     *
     * @param resultsGenerator This class is responsible for verifying the query
     * @param query            The query to be verified
     * @return <code>boolean</code> value denoting whether a query is verified or not
     */
    private static boolean canExecute(ResultsGenerator resultsGenerator, String query) {

        return resultsGenerator.verifyQuery(s -> {

            String[] queryArray = s.split(",");

            if (queryArray.length < 3) {

                LOGGER.error(MessageUtils.INCORRECT_QUERY_MESSAGE);
                return false;
            }

            if (!queryArray[0].toLowerCase().trim().equals("complete")) {

                LOGGER.error(MessageUtils.INCORRECT_QUERY_ACTION_MESSAGE);
                return false;

            }

            if (!queryArray[1].toLowerCase().trim().matches("[a-z]*")) {

                LOGGER.error(MessageUtils.INCORRECT_QUERY_PREFIX_MESSAGE);
                return false;
            }

            if (MethodUtils.getIntValue(queryArray[2].trim()) == null) {

                LOGGER.error(MessageUtils.INCORRECT_QUERY_MAX_COUNT_MESSAGE);
                return false;
            }

            LOGGER.info("Query {} has been successfully verified", query);
            return true;

        }, query);

    }
}
