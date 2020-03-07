package com.stateless.autocomplete.loader;

import java.io.*;
import java.util.HashMap;

/**
 * This class helps load the corpus into memory. An array of <Code>{@link HashMap}</Code>
 * is used to store the corpus content.
 */
public class CorpusLoader {

    private static CorpusLoader corpusLoader;
    private HashMap<String, Integer>[] corpusEntryWithFrequencyHashMapArray;

    private CorpusLoader() {
        corpusEntryWithFrequencyHashMapArray = new HashMap[26];

        for (int i = 0; i < corpusEntryWithFrequencyHashMapArray.length; i++)
            corpusEntryWithFrequencyHashMapArray[i] = new HashMap<>();

    }

    public static CorpusLoader createInstance() {

        if (corpusLoader == null)
            corpusLoader = new CorpusLoader();

        return corpusLoader;
    }

    public HashMap<String, Integer>[] getCorpusEntryWithFrequencyHashMapArray() {
        return corpusEntryWithFrequencyHashMapArray;
    }

    public boolean loadCorpusDataFromFile(String pathToFile) {

        File file = new File("/Users/SubrataMohanty/Documents/abhijeet-mohanty-internship-2020/src/main/resources" +
                "/corpus.txt");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            bufferedReader
                    .lines()
                    .map(String::toLowerCase)
                    .filter(s -> s.matches("[a-z]*"))
                    .forEach(s -> corpusEntryWithFrequencyHashMapArray[s.charAt(0) - 'a'].put(s,
                            corpusEntryWithFrequencyHashMapArray[s.charAt(0) - 'a'].getOrDefault(s, 0) + 1));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


}
