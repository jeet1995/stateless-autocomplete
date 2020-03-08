package com.stateless.autocomplete.verifier;

/**
 * Functional interface abstracting query verification
 * */
@FunctionalInterface
public interface QueryVerifier {

    boolean verifyQuery(String query);

}
