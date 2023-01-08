package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;
import org.flashcards.src.Flashcard;

public interface DatabaseInterface {
    void addFlashcardList(FlashcardCollectionInterface list);
    FlashcardCollectionInterface getFlashcardList();
    void deleteFlashcardList();
}
