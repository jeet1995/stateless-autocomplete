package com.stateless.autocomplete;

import com.stateless.autocomplete.autocompletion.ResultsGenerator;
import com.stateless.autocomplete.executor.QueryExecutor;
import com.stateless.autocomplete.loader.CorpusLoader;
import com.stateless.autocomplete.utils.MessageUtils;
import com.stateless.autocomplete.utils.MethodUtils;
import com.sun.xml.internal.ws.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class AutocompleteRunner {

    public static void main(String[] args) {
        run();
    }

    private static void run() {

        printIntroductionMessage();
        HashMap<String, Integer>[] corpusMapArray = CorpusLoader.createInstance().loadCorpusDataFromFile("corpus.txt");
        ResultsGenerator resultsGenerator = ResultsGenerator.createInstance();
        QueryExecutor queryExecutor = QueryExecutor.createInstance();
        Scanner in = new Scanner(System.in);
        String choice = "";

        do {

            String query = in.nextLine();

            if (canExecute(resultsGenerator, query)) {

                String[] queryParts = query.split(",");

                String action = queryParts[0].toLowerCase().trim();
                String prefix = queryParts[1].toLowerCase().trim();
                Integer maxCount = MethodUtils.getIntValue(queryParts[2].trim());

                List<String> autocompletionStrings = queryExecutor.execute(resultsGenerator, corpusMapArray, action, prefix, maxCount);
                autocompletionStrings.forEach(System.out::print);

            } else {
                System.out.println("Continuing the application");
                continue;
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
                "|  1. Continue/Exit the application by entering Y or N through standard input                                   |\n" +
                "|  2. If Y is entered, enter a search query in the form : complete,<prefix>,<max_count>                         |\n" +
                "|===============================================================================================================|");

    }

    private static boolean canExecute(ResultsGenerator resultsGenerator, String query) {

        return resultsGenerator.verifyQuery(s -> {

            String[] queryArray = s.split(",");

            if (queryArray.length < 3) {

                System.out.println(MessageUtils.INCORRECT_QUERY_MESSAGE);
                return false;
            }

            if (!queryArray[0].toLowerCase().trim().equals("complete")) {

                System.out.println(MessageUtils.INCORRECT_QUERY_ACTION_MESSAGE);
                return false;

            }

            if (!queryArray[1].toLowerCase().trim().matches("[a-z]*")) {

                System.out.println(MessageUtils.INCORRECT_QUERY_PREFIX_MESSAGE);
                return false;
            }

            if (MethodUtils.getIntValue(queryArray[2].trim()) == null) {

                System.out.println(MessageUtils.INCORRECT_QUERY_MAX_COUNT_MESSAGE);
                return false;
            }

            return true;

        }, query);

    }
}
