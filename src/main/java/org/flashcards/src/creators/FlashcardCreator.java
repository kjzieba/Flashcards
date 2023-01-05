package org.flashcards.src.creators;

import org.flashcards.src.Flashcard;
import org.flashcards.src.State;

public interface FlashcardCreator {
    Flashcard createFlashcard(State flashcardState, String... args);

}
