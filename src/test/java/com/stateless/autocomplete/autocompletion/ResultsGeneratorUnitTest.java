package com.stateless.autocomplete.autocompletion;

import com.stateless.autocomplete.loader.CorpusLoader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ResultsGeneratorUnitTest {

    private HashMap<String, Integer>[] corpusMapArray = new HashMap[26];
    private ResultsGenerator resultsGenerator;

    @Before
    public void loadData() {
        corpusMapArray = CorpusLoader.createInstance().loadCorpusDataFromFile("corpus-files/corpus-test.txt");
        resultsGenerator = ResultsGenerator.createInstance();
    }

    @Test
    public void testGenerateAutocompleteStringsWithMaxCountLessThanNumCandidates() {

        List<String> actualAutocompleteCandidates = resultsGenerator.generateAutocompleteStrings(corpusMapArray, "complete", "b", 2);
        List<String> expectedAutocompleteCandidates = Arrays.asList("bat", "bar");

        Assert.assertEquals(expectedAutocompleteCandidates, actualAutocompleteCandidates);
    }

    @Test
    public void testGenerateAutocompleteStringsWithMaxCountMoreThanNumCandidates() {

        List<String> actualAutocompleteCandidates = resultsGenerator.generateAutocompleteStrings(corpusMapArray, "complete", "zo", 100);
        List<String> expectedAutocompleteCandidates = Arrays.asList("zo", "zor");

        Assert.assertEquals(expectedAutocompleteCandidates, actualAutocompleteCandidates);
    }



}
