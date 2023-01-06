package org.flashcards.src.repositories;

import java.util.ArrayList;

public class AllCards {

    ArrayList<CardsRepo> allFlashcards = new ArrayList<>();

    public void addToAll(CardsRepo cardsRepo) {
        allFlashcards.add(cardsRepo);
    }

    public CardsRepo deleteFromAll(CardsRepo cardsRepo) {
         return allFlashcards.remove(allFlashcards.indexOf(cardsRepo));
    }
}
