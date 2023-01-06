package org.flashcards.src.creators;

import org.flashcards.src.Card;

public interface CardCreator {
    Card createFlashcard(Long id, String... args);

}
