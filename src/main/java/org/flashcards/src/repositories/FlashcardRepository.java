package org.flashcards.src.repositories;

import org.flashcards.src.Flashcard;

import java.util.ArrayList;
import java.util.List;

public class FlashcardRepository {

    protected String title;

    ArrayList<Flashcard> flashcards = new ArrayList<>();


    public FlashcardRepository(String title) {
        this.title = title;
    }

    public Flashcard queryFromRepo(Flashcard flashcard) {
        return flashcards.get(flashcards.indexOf(flashcard));
    }

    public void addToRepo(Flashcard flashcard) {
        flashcards.add(flashcard);
    }

    public void deleteFromRepo(Flashcard flashcard) {
        flashcards.remove(flashcard);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
