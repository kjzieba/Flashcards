package org.flashcards.Backend.creators;

import org.flashcards.Backend.Flashcard;
import org.flashcards.Backend.enums.FlashcardStates;

public interface FlashcardCreator {
    Flashcard createFlashcard(FlashcardStates state , String... args);

}
