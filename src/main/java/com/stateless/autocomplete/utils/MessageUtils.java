package com.stateless.autocomplete.utils;

public class MessageUtils {

    private MessageUtils() {
    }

    public static final String INCORRECT_QUERY_MESSAGE = "The query is incomplete, please refer to introduction " +
            "message for the query format";

    public static final String INCORRECT_QUERY_ACTION_MESSAGE = "The query should be of the format complete,... " +
            "Please refer to introduction message for the query format";

    public static final String INCORRECT_QUERY_PREFIX_MESSAGE = "The query should include a prefix comprised of " +
            "letters and spaces. Please refer to introduction message for the query format";

    public static final String INCORRECT_QUERY_MAX_COUNT_MESSAGE = "The max_count value specified in the query is not an integer";
}
