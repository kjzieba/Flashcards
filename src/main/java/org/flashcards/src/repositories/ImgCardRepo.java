package org.flashcards.src.repositories;

import org.flashcards.src.ImgCard;

import java.util.ArrayList;
import java.util.Objects;

public class ImgCardRepo implements CardsRepo{

    protected String title;

    public ArrayList<ImgCard> flashcards = new ArrayList<>();


    public ImgCard queryFromRepo(Long id) {
        for (ImgCard flashcard : flashcards) {
            if (Objects.equals(flashcard.getId(), id)) {
                return flashcard;
            }
        }
        return null;
    }

    public void addToRepo(ImgCard flashcard) {
        flashcards.add(flashcard);
    }

    public ImgCard deleteFromRepo(Long id) {

        return flashcards.remove(flashcards.indexOf(id));
    }

    public ImgCard deleteLastFromRepo() {
        return flashcards.remove(flashcards.size() - 1);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    @Override
    public String toString() {
        return "ImgCardRepo{" +
                "title='" + title + '\'' +
                ", flashcards=" + flashcards +
                '}';
    }
}
