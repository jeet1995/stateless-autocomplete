package com.stateless.autocomplete.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;

/**
 * This class helps load the corpus into memory. An array of <code>{@link HashMap}</code>
 * is used to store the corpus content.
 */
public class CorpusLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CorpusLoader.class);
    private static final HashMap<String, Integer>[] emptyCorpusMapArray = new HashMap[]{};
    private static CorpusLoader corpusLoader;

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
     * @return A corpus <code>{@link HashMap}</code> array either filled with some data or as an empty array.
     */
    public HashMap[] loadCorpusDataFromFile(String pathToFile) {

        LOGGER.info("Loading file from path {}", pathToFile);

        File file = new File(pathToFile);

        HashMap<String, Integer>[] corpusMapArray;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            corpusMapArray = new HashMap[26];

            for (int i = 0; i < corpusMapArray.length; i++)
                corpusMapArray[i] = new HashMap<>();

            bufferedReader.lines().map(String::toLowerCase).filter(s -> s.matches("[a-z]*")).forEach(s ->
                    corpusMapArray[s.charAt(0) - 'a'].put(s, corpusMapArray[s.charAt(0) - 'a'].getOrDefault(s, 0) + 1));

            LOGGER.info("Loaded contents of file {} into memory", file.getName());

            return corpusMapArray;

        } catch (FileNotFoundException e) {
            LOGGER.error("The file was not found on the path, exiting the application!");
            System.exit(-1);
        } catch (IOException e) {
            LOGGER.error("An exception was thrown read the file, exiting the application");
            System.exit(-1);
        }

        // Helps avert the null pointer exception
        return emptyCorpusMapArray;
    }


}
