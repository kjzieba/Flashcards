package org.flashcards.repositories;

import java.util.HashMap;
import java.util.Map;

public class AllCards {

    private Map<Long, CardsRepo> allFlashcards = new HashMap<>();

    public CardsRepo query(Long id){
        return allFlashcards.get(id);
    }

    public void addToAll(Long id, CardsRepo cardsRepo) {
        allFlashcards.put(id, cardsRepo);
    }

    public CardsRepo deleteFromAll(Long id) {
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
