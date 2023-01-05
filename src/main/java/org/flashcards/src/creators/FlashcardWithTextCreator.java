package org.flashcards.src.creators;

import org.flashcards.src.Flashcard;
import org.flashcards.src.FlashcardWithText;
import org.flashcards.src.states.State;

public class FlashcardWithTextCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(State flashcardState, String... args) {
        return new FlashcardWithText(args[0], args[1], flashcardState);
    }

}
