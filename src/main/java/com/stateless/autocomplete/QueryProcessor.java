package com.stateless.autocomplete;

@FunctionalInterface
public interface QueryProcessor {

    boolean processQuery(String query);

}
