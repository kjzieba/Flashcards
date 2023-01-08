package org.flashcards.collection;

import org.flashcards.src.Card;

public interface Iterator {
    Card next();

    boolean isDone();
}
