package org.flashcards.creators;

import org.flashcards.Card;

public interface CardCreator {
    Card createFlashcard(Long id, String... args);

}
