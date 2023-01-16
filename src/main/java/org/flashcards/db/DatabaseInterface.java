package org.flashcards.db;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.util.Map;

public interface DatabaseInterface {
    void addFlashcardList(FlashcardCollectionInterface list);

    FlashcardCollectionInterface getFlashcardList(Long id, String type);

    void deleteFlashcardList(Long id);

    Map<Long, String> getAllLists();

    String getTypeByID(Long id);
}
