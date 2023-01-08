package org.flashcards.collection;

import org.flashcards.src.FlashcardWithImage;

import java.util.ArrayList;

public class ImgFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<FlashcardWithImage> list;

    public ImgFlashcardCollection(String title, ArrayList<FlashcardWithImage> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<FlashcardWithImage> getList() {
        return list;
    }

    public void setList(ArrayList<FlashcardWithImage> lst) {
        this.list = lst;
    }

    public void add(FlashcardWithImage f) {
        list.add(f);
    }

    public void remove(FlashcardWithImage f) {
        list.remove(f);
    }

    @Override
    public Iterator createIterator() {
        return new ImgFlashcardIterator(this);
    }

    @Override
    public String toString() {
        return "ImgFlashcardCollection{" +
                "title='" + title + '\'' +
                ", list=" + list +
                '}';
    }
}
