package org.flashcards.collection;

import org.flashcards.src.Flashcard;

public interface Iterator {
    Flashcard next();

    boolean isDone();
}
