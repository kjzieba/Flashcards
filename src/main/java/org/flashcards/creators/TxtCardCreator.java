package org.flashcards.creators;

import org.flashcards.TxtCard;

public class TxtCardCreator implements CardCreator {

    @Override
    public TxtCard createFlashcard(Long id, String... args) {
        return new TxtCard(id, args[0], args[1]);
    }

}
