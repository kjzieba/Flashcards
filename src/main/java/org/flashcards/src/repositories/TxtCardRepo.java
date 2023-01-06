package org.flashcards.src.repositories;

import org.flashcards.src.TxtCard;

import java.util.ArrayList;
import java.util.Objects;

public class TxtCardRepo implements CardsRepo{

    protected String title;

    ArrayList<TxtCard> flashcards = new ArrayList<>();



    public TxtCard queryFromRepo(Long id) {
        for (TxtCard flashcard : flashcards) {
            if (Objects.equals(flashcard.getId(), id)) {
                return flashcard;
            }
        }
        return null;
    }

    public void addToRepo(TxtCard flashcard) {
        flashcards.add(flashcard);
    }


    public TxtCard deleteFromRepo(TxtCard flashcard) {

        return flashcards.remove(flashcards.indexOf(flashcard));
    }


    public TxtCard deleteLastFromRepo() {
        return flashcards.remove(flashcards.size() - 1);
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
