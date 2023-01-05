package org.flashcards.src.repositories;

import java.util.ArrayList;

public class AllFlashcards {

    ArrayList<FlashcardRepository> allFlashcards = new ArrayList<>();


    public FlashcardRepository queryFromAll(FlashcardRepository flashcardRepository) {
        return allFlashcards.get(allFlashcards.indexOf(flashcardRepository));
    }

    public void addToAll(FlashcardRepository flashcardRepo) {
        allFlashcards.add(flashcardRepo);
    }

    public void deleteFromAll(FlashcardRepository flashcardRepository) {
        allFlashcards.remove(flashcardRepository);
    }
}
