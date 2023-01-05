package org.flashcards.Backend.creators;

import org.flashcards.Backend.Flashcard;
import org.flashcards.Backend.FlashcardWithText;
import org.flashcards.Backend.enums.FlashcardStates;

public class FlashcardWithTextCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(FlashcardStates state, String... args) {
        return new FlashcardWithText(args[0], args[1], state);
    }

}
