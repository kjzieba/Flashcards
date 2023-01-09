package org.flashcards.repositories;

import org.flashcards.collection.FlashcardCollectionInterface;

import java.util.HashMap;
import java.util.Map;

public class AllCards {

    private Map<Long, FlashcardCollectionInterface> allFlashcards = new HashMap<>();

    public FlashcardCollectionInterface query(Long id){
        return allFlashcards.get(id);
    }

    public void addToAll(Long id, FlashcardCollectionInterface cardsRepo) {
        allFlashcards.put(id, cardsRepo);
    }

    public FlashcardCollectionInterface deleteFromAll(Long id) {
         return allFlashcards.remove(id);
    }

    public void print(){
        for (Map.Entry entry : allFlashcards.entrySet())
        {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }
    @Override
    public String toString() {
        return "AllCards{" +
                "allFlashcards=" + allFlashcards +
                '}';
    }
}
