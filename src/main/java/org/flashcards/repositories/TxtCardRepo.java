package org.flashcards.repositories;

import org.flashcards.TxtCard;

import java.util.ArrayList;
import java.util.Objects;

public class TxtCardRepo implements CardsRepo{

    protected String title = "Title";

    public ArrayList<TxtCard> flashcards = new ArrayList<>();


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


    public TxtCard deleteFromRepo(Long id) {

        return flashcards.remove(flashcards.indexOf(id));
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

    public ArrayList<TxtCard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<TxtCard> flashcards) {
        this.flashcards = flashcards;
    }

    @Override
    public String toString() {
        return "TxtCardRepo{" +
                "title='" + title + '\'' +
                ", flashcards=" + flashcards +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        TxtCardRepo repo = null;
        try {
            repo = (TxtCardRepo) super.clone();
        } catch (CloneNotSupportedException e) {
            repo = new TxtCardRepo();
        }
        String title = this.getTitle();
        repo.setTitle(title);
        repo.setFlashcards((ArrayList<TxtCard>) this.getFlashcards().clone());
        return repo;
    }
}
