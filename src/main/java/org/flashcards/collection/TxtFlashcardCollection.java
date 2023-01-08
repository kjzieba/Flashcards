package org.flashcards.collection;

import org.flashcards.src.FlashcardWithText;

import java.util.ArrayList;

public class TxtFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<FlashcardWithText> list;

    public TxtFlashcardCollection(String title, ArrayList<FlashcardWithText> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FlashcardWithText> getList() {
        return list;
    }

    public void setList(ArrayList<FlashcardWithText> lst) {
        this.list = lst;
    }

    public void add(FlashcardWithText f) {
        list.add(f);
    }

    public void remove(FlashcardWithText f) {
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
