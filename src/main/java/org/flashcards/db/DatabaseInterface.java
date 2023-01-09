package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

public interface DatabaseInterface {
    void addFlashcardList(FlashcardCollectionInterface list);
    FlashcardCollectionInterface getFlashcardList();
    void deleteFlashcardList();
}
