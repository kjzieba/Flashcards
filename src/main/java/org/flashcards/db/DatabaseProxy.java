package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

public class DatabaseProxy implements DatabaseInterface {
    @Override
    public void addFlashcardList(FlashcardCollectionInterface list) {

    }

    @Override
    public FlashcardCollectionInterface getFlashcardList(Long id, String type) {
        return null;
    }

    @Override
    public void deleteFlashcardList(Long id) {

    }
}
