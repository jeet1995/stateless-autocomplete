Goal : To build an autocompletion engine which generates a list of words based on a specified prefix.
---
Name : Abhijeet Mohanty
---
### Development environment
   - **OS:** MacOs Mojave 
   - **IDE:** IntelliJ IDEA Ultimate 2018.1
   - **Java version:** 1.8.121
   - **Maven version:** 3.3.9
   
### Instructions
   * Once the project has been unzipped, navigate to the project directory using the command :
   
        `cd abhijeet-mohanty-internship-2020/`
    
   * Run the following command to build the project
   
        `mvn clean install`
   
   * Run the following command, to run `AutocompleteRunner`, specify the absolute path to the corpus file (*.txt) file
   as a command line argument.
        
        `mvn clean install exec:java -Dexec.mainClass="com.stateless.autocomplete.AutocompleteRunner"`
        
   * An introductory instructional message will be displayed, please go ahead and type the query
   
   * A prompt to continue/ exit the application is displayed after the result of each query is displayed 

### Designing the application
   * `AutocompleteRunner` class
        * This class is from where the application starts.
        * It prints an introductory instructional message, verifies the query typed in by the user.
        * In order to verify the query, I decided to have a functional interface argument as this would mean
        I can pass different behaviours of the method to the `verifyQuery` method.
   *  `CorpusLoader` class
        * This class is responsible to load the corpus file (a .txt file) into an array of `HashMap` type
        defined by `String` and `Integer`
        * Choosing this particular data structure was inspired by making use of a `Trie` where each node 
        represents some character and it has a mapping to all the letters the succeed it.
        * As this application does not require functionality such as `delete`, I just make use of simple 
        array of `HashMap`.
        * The length of the aforementioned array is `26` which is basically all the letters of the english
        alphabet.
        * Each string in the corpus is converted to lower case, trimmed of whitespaces (trailing and leading
        whitespaces are removed) and extraneous whitespaces between words in the string are removed.
        * An example, the word `bat` is added to `corpusMapArray[1]` along with its frequency of occurrence
   * `ResultsGenerator` class
        * This class helps check for the correctness of the query through the `verifyQuery` method.
        * Examples of correct queries
            * *complete,b,9*
            * *cOmplete,    a, 901*
            * *complETE, a    bb cde,7*
        * Examples of incorrect queries
            * *com, 12, 8.9*
        * This class generates the autocompletion strings through the `generateAutocompleteStrings` method
            * Here the `prefix` is checked against the `corpusMapArray` for its presence.
            * In case, the first character of the prefix matches, the HashMap instance corresponding to
            that index is picked up.
            * Then, the `prefix` must not only be the substring of some keys in the `HashMap` but also
            the substring should start from **index 0** of the key.
            * An example:
                ```
                 -> corpus[0] has strings starting with a and their frequencies
                 -> say corpus[0] contains the following HashMap <(aba, 5), (aca, 3), (achen, 2)> and
                 the prefix is 'ac'
                 -> the possible candidate keys for autocompletion become, 'aca' and 'achen' as 'ac' is a substring
                 which starts from index 0 in these candidate keys
                ```
            * These candidate keys are added to an instance of a `PriorityQueue` class type defined by `AutocompleteCandidate`
            * A `PriorityQueue` is chosen as it sorts its elements based on some compare logic as defined in the `compareTo` in `AutocompleteCandidate` 
            * The compare logic in `AutocompleteCandidate` class is the following :
                * First, the higher the frequency, the more ahead the word comes in the list
                * Second, the lower the lexicographical ordering comes , the more ahead the word comes in the list
            * The `maxCount` is used to extract those many words from the `PriorityQueue`

### Execution logs

```
13:53:02.727 [main] ERROR com.stateless.autocomplete.AutocompleteRunner - The absolute path to the file has not been specified, using default corpus on the classpath
13:53:02.732 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Starting the application.
|===============================================================================================================|
|                                         Autocompletion Engine                                                 |
|===============================================================================================================|
|         This application enables the client to obtain possible autocomplete results for some query.           |
|===============================================================================================================|
|The client has the following options :                                                                         |
|  1. Enter a search query in the form : complete,<prefix>,<max_count>                                          |
|  2. Continue/Exit the application by entering Y or N through standard input                                   |
|===============================================================================================================|
13:53:02.733 [main] INFO com.stateless.autocomplete.loader.CorpusLoader - Loading file from path corpus-files/corpus.txt
13:53:02.903 [main] INFO com.stateless.autocomplete.loader.CorpusLoader - Loaded contents of file corpus.txt into memory
complete,bar,3
13:53:17.312 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,bar,3
13:53:17.315 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,bar,3 has been successfully verified
13:53:17.316 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
bar,bark

Do you wish to continue (Y/N) ?
y
complete,zo,9
13:53:32.540 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,zo,9
13:53:32.540 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,zo,9 has been successfully verified
13:53:32.540 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
zo,zor

Do you wish to continue (Y/N) ?
n

Process finished with exit code 0
```

### Future improvements
* Using a `Trie`. 
    * In the current submission, a HashMap is used to store strings starts with a particular letter.
    * In case a Trie is used, to search for `bar` and `bark` would require moving through the same entry.
    * In the current submission, `bar` and `bark` are two different entries.
    * So the time complexity is `O(m1 + m2 + .... + ml)` for `l` such strings, whereas in a `Trie` it would just
    be `O(k)` where `k` is the length of the longest string.
* Support for multiple corpus formats such as `.txt, .csv, .json` to name a few.  
* Handling very large corpus data and using a *Map-Reduce* approach to generate autocompletion strings
    * Map-Reduce could be used to determine the frequency of strings in each chunk and then across all chunks
    of the data in the corpus.
    * The resultant data can be loaded onto a `Trie` to help generate autocompletion strings
 
