package com.stateless.autocomplete.loader;


import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

public class CorpusLoaderUnitTest {

    private static CorpusLoader corpusLoader;

    @BeforeClass
    public static void init() {
        corpusLoader = CorpusLoader.createInstance();
    }

    @Test
    public void testLoadFileIntoCorpus() {

        HashMap<String, Integer>[] actualCorpusMapArray = corpusLoader.loadCorpusDataFromFile("corpus-files/corpus-test.txt");
        HashMap<String, Integer>[] expectedCorpusMapArray = new HashMap[26];

        for (int i = 0; i < 26; i++) {
            expectedCorpusMapArray[i] = new HashMap<>();
        }

        expectedCorpusMapArray[1].put("bat", 2);
        expectedCorpusMapArray[1].put("bar", 1);
        expectedCorpusMapArray[1].put("bark", 1);
        expectedCorpusMapArray[0].put("aba", 1);
        expectedCorpusMapArray[25].put("z", 1);
        expectedCorpusMapArray[25].put("zo", 1);
        expectedCorpusMapArray[25].put("zor", 1);
        expectedCorpusMapArray[25].put("za", 1);

        Assert.assertArrayEquals(expectedCorpusMapArray, actualCorpusMapArray);
    }

    @Test
    public void testLoadFileIntoCorpusWithFileNotFoundException() {
        Assert.assertArrayEquals(new HashMap[]{}, corpusLoader.loadCorpusDataFromFile("corpus-file/corpus-test.txt"));
    }
}
