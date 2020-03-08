package com.stateless.autocomplete.loader;

import java.io.*;
import java.util.HashMap;

/**
 * This class helps load the corpus into memory. An array of <Code>{@link HashMap}</Code>
 * is used to store the corpus content.
 */
public class CorpusLoader {

    private static CorpusLoader corpusLoader;
    private static final HashMap<String, Integer>[] emptyCorpusMapArray = new HashMap[]{};

    private CorpusLoader() {
    }

    public static CorpusLoader createInstance() {

        if (corpusLoader == null)
            corpusLoader = new CorpusLoader();

        return corpusLoader;
    }

    /**
     * Load the corpus data into a {@link HashMap} array.
     *
     * @param pathToFile The absolute path to the file which contains the corpus information.
     *
     * @return A corpus {@link HashMap} array either filled with some data or as an empty array.
     * */
    public HashMap[] loadCorpusDataFromFile(String pathToFile) {

        File file = new File("/Users/SubrataMohanty/Documents/abhijeet-mohanty-internship-2020/src/main/resources" +
                "/corpus.txt");

        HashMap<String, Integer>[] corpusMapArray;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            corpusMapArray = new HashMap[26];

            for (int i = 0; i < corpusMapArray.length; i++)
                corpusMapArray[i] = new HashMap<>();

            bufferedReader
                    .lines()
                    .map(String::toLowerCase)
                    .filter(s -> s.matches("[a-z]*"))
                    .forEach(s -> corpusMapArray[s.charAt(0) - 'a'].put(s, corpusMapArray[s.charAt(0) - 'a'].getOrDefault(s, 0) + 1));

            return corpusMapArray;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Helps avert the null pointer exception
        return emptyCorpusMapArray;
    }


}
