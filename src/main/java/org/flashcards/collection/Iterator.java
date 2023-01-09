package org.flashcards.collection;

import org.flashcards.Card;

public interface Iterator {
    Card next();

    boolean isDone();
}
