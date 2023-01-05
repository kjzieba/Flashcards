package org.flashcards.src.creators;

import org.flashcards.src.Flashcard;
import org.flashcards.src.FlashcardWithText;
import org.flashcards.src.states.State;

public class FlashcardWithTextCreator implements FlashcardCreator {

    @Override
    public Flashcard createFlashcard(String... args) {
        return new FlashcardWithText(args[0], args[2]);
    }

}
