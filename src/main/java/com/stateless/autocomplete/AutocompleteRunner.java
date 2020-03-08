package com.stateless.autocomplete;

import com.stateless.autocomplete.loader.CorpusLoader;
import com.stateless.autocomplete.utils.MessageUtils;
import com.stateless.autocomplete.utils.MethodUtils;

import java.util.HashMap;

public class AutocompleteRunner {

    public static void main(String[] args) {

        HashMap<String, Integer>[] corpusMapArray = CorpusLoader.createInstance().loadCorpusDataFromFile("corpus.txt");

        printIntroductionMessage();

        ResultsGenerator resultsGenerator = ResultsGenerator.createInstance(corpusMapArray);

        execute(resultsGenerator, "complete, bat, 2");

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

    private static void execute(ResultsGenerator resultsGenerator, String query) {

        resultsGenerator.processQuery(s -> {

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
