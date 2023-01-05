package org.flashcards.src.repositories;

import java.util.ArrayList;

public class AllFlashcards {

    ArrayList<FlashcardRepository> allFlashcards = new ArrayList<>();


    public FlashcardRepository queryFromAll(String title) {
        for(FlashcardRepository repository : allFlashcards){
            if(repository.getTitle()==title){
                return repository;
            }
        }
        return null;
    }

    public void addToAll(FlashcardRepository flashcardRepo) {
        allFlashcards.add(flashcardRepo);
    }

    public void deleteFromAll(String title) {
        for(FlashcardRepository repository : allFlashcards){
            if(repository.getTitle()==title){
                allFlashcards.remove(repository);
                break;
            }
        }
    }
}
