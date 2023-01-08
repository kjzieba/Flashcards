package org.flashcards.collection;

import org.flashcards.src.TxtCard;

import java.util.ArrayList;

public class TxtFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<TxtCard> list;

    public TxtFlashcardCollection(String title, ArrayList<TxtCard> list) {
        this.title = title;
        this.list = list;
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

    public void add(TxtCard f) {
        list.add(f);
    }

    public void remove(TxtCard f) {
        list.remove(f);
    }

    @Override
    public Iterator createIterator() {
        return new TxtFlashcardIterator(this);
    }

    @Override
    public String toString() {
        return "TxtFlashcardCollection{" +
                "title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
