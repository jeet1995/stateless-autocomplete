package com.stateless.autocomplete.verifier;

@FunctionalInterface
public interface QueryVerifier {

    boolean verifyQuery(String query);

}
