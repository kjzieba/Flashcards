package org.flashcards.collection;

import org.flashcards.ImgCard;

import java.util.ArrayList;
import java.util.Objects;

public class ImgFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<ImgCard> list;

    private final Long id;

    public ImgFlashcardCollection(String title, ArrayList<ImgCard> list, Long id) {
        this.title = title;
        this.list = list;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ImgCard> getList() {
        return list;
    }

    public void setList(ArrayList<ImgCard> lst) {
        this.list = lst;
    }

    public Long getId() {
        return id;
    }

    public void add(ImgCard f) {
        list.add(f);
    }

    public void remove(ImgCard f) {
        list.remove(f);
    }

    public ImgCard getImgFlashcardByID(Long id) {
        for (ImgCard flashcard : list) {
            if (Objects.equals(flashcard.getId(), id)) {
                return flashcard;
            }
        }
        return null;
    }

    @Override
    public Iterator createIterator() {
        return new ImgFlashcardIterator(this);
    }

    @Override
    public String toString() {
        return "I " + id.toString() + " " +
                title + " " + list;
    }
}
