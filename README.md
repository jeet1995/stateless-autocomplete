Goal :
---
Name :
---

### Execution logs

```
20:13:59.584 [main] ERROR com.stateless.autocomplete.AutocompleteRunner - The absolute path to the file has not been specified, using default corpus on the classpath
20:13:59.593 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Starting the application.

|===============================================================================================================|
|                                         Autocompletion Engine                                                 |
|===============================================================================================================|
|         This application enables the client to obtain possible autocomplete results for some query.           |
|===============================================================================================================|
|The client has the following options :                                                                         |
|  1. Enter a search query in the form : complete,<prefix>,<max_count>                                          |
|  2. Continue/Exit the application by entering Y or N through standard input                                   |
|===============================================================================================================|
20:13:59.595 [main] INFO com.stateless.autocomplete.loader.CorpusLoader - Loading file from path corpus-files/corpus.txt
20:13:59.861 [main] INFO com.stateless.autocomplete.loader.CorpusLoader - Loaded contents of file corpus.txt into memory
complete,b,3
20:14:16.629 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,b,3
20:14:16.633 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,b,3 has been successfully verified
20:14:16.634 [main] INFO com.stateless.autocomplete.executor.QueryExecutor - Attempting to execute the query
20:14:16.635 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
bat,bark,bar

Do you wish to continue (Y/N) ?
y
complete,z,900
20:14:27.139 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,z,900
20:14:27.140 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,z,900 has been successfully verified
20:14:27.140 [main] INFO com.stateless.autocomplete.executor.QueryExecutor - Attempting to execute the query
20:14:27.140 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
zor,zo,za,z

Do you wish to continue (Y/N) ?
y
complete,zo,3
20:14:42.363 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Attempting to verify the query complete,zo,3
20:14:42.364 [main] INFO com.stateless.autocomplete.AutocompleteRunner - Query complete,zo,3 has been successfully verified
20:14:42.364 [main] INFO com.stateless.autocomplete.executor.QueryExecutor - Attempting to execute the query
20:14:42.364 [main] INFO com.stateless.autocomplete.autocompletion.ResultsGenerator - Generating autocomplete strings.
zor,zo

Do you wish to continue (Y/N) ?
cOmplete,zO,5

Process finished with exit code 0

```
