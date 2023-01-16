package org.flashcards.collection;

import org.flashcards.TxtCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TxtFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<TxtCard> list;
    private final Long id;

    public TxtFlashcardCollection(String title, ArrayList<TxtCard> list, Long id) {
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

    public ArrayList<TxtCard> getList() {
        return list;
    }

    public void setList(ArrayList<TxtCard> lst) {
        this.list = lst;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void add(TxtCard f) {
        list.add(f);
    }

    public void remove(TxtCard f) {
        list.remove(f);
    }

    public TxtCard getTxtFlashcardByID(Long id) {
        for (TxtCard flashcard : list) {
            if (Objects.equals(flashcard.getId(), id)) {
                return flashcard;
            }
        }
        return null;
    }

    @Override
    public Iterator createIterator() {
        return new TxtFlashcardIterator(this);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        String lst = list.toString();
        String[] l = lst.split(", ");
        lst = String.join(",", l);
        return "T " + id.toString() + " " +
                title + " " + lst;
    }
}
