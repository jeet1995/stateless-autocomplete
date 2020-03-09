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
   
        `mvn clean install compile`
   
   * Run the following command, to run `AutocompleteRunner`, specify the absolute path to the corpus file (*.txt) file
   as a command line argument.
        
        `mvn exec:java -Dexec.args="<path-to-some-corpus-file>"`
        
        * An example *(In any Linux based system)* :
        
        `mvn exec:java -Dexec.args="/Users/Mohanty/Documents/corpus.txt"`
        
   * An introductory instructional message will be displayed, please go ahead and type the query
   
   * A prompt to continue/ exit the application is displayed after the result of each query is displayed 

### Designing the application
   * `AutocompleteRunner` class
        * This class is from where the application starts.
        * It prints an *introductory instructional message* and *verifies the query* typed in by the user.
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
        * This class helps check the correctness of the query through the `verifyQuery` method.
        * Examples of correct queries
            * *complete,b,9*
            * *cOmplete,    a, 901*
            * *complETE, a    bb cde,7*
        * Examples of incorrect queries
            * *com, 12, 8.9*
        * This class generates the autocompletion strings through the `generateAutocompleteStrings` method
            * Here, the `prefix` is checked against the `corpusMapArray` for its presence.
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
            * A `PriorityQueue` is chosen as it sorts its elements based on some compare logic as defined in the `compareTo` of the `AutocompleteCandidate` class.
            * The compare logic in `AutocompleteCandidate` class is the following :
                * First, the higher the frequency, the more ahead the word comes in the list
                * Second, the lower the lexicographical ordering of the word, the more ahead the word comes in the list
            * The `maxCount` is used to extract those many words from the `PriorityQueue`
            
### Testing
* The `ResultsGeneratorUnitTest` and `CorpusLoaderUnitTest` check the essential functionality of the application such as :
    * Creation of the `corpusMapArray`
    * Generation of `autocompletionStrings`

### Execution logs
* Input corpus.txt

```
bat
Bat
aBa
bark
bar
ba ba    black    sheep
z
zo
zor
za
```

* Execution logs :
```
15:40:02.771 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.AutocompleteRunner - Starting the application.
|===============================================================================================================|
|                                         Autocompletion Engine                                                 |
|===============================================================================================================|
|         This application enables the client to obtain possible autocomplete results for some query.           |
|===============================================================================================================|
|The client has the following options :                                                                         |
|  1. Enter a search query in the form : complete,<prefix>,<max_count>                                          |
|  2. Continue/Exit the application by entering Y or N through standard input                                   |
|===============================================================================================================|
15:40:02.775 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.loader.CorpusLoader - Loading file from path /Users/SubrataMohanty/Documents/abhijeet-mohanty-internship-2020/corpus-files/corpus.txt
15:40:02.785 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.loader.CorpusLoader - Loaded contents of file corpus.txt into memory
complete,ba, 2
15:40:26.390 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,b, 2
15:40:26.392 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,b, 2 has been successfully verified
15:40:26.392 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
bat,ba ba black sheep

Do you wish to continue (Y/N) ?
y
complete,Ba Ba,100
15:44:43.316 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,Ba Ba,100
15:44:43.316 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,Ba Ba,100 has been successfully verified
15:44:43.316 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
ba ba black sheep

Do you wish to continue (Y/N) ?
y
comPLETE,   bar,    -5
15:45:01.284 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query comPLETE,   bar,    -5
15:45:01.284 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.AutocompleteRunner - Query comPLETE,   bar,    -5 has been successfully verified
15:45:01.285 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
bar,bark

Do you wish to continue (Y/N) ?
y
complete,   ba,  1
15:45:26.518 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,   ba,  1
15:45:26.518 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,   ba,  1 has been successfully verified
15:45:26.518 [com.stateless.autocomplete.AutocompleteRunner.main()] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
bat

Do you wish to continue (Y/N) ?
n
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
 
