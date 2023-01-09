package org.flashcards.collection;

import org.flashcards.ImgCard;

import java.util.ArrayList;

public class ImgFlashcardCollection implements FlashcardCollectionInterface {
    private String title;
    private ArrayList<ImgCard> list;

    public ImgFlashcardCollection(String title, ArrayList<ImgCard> list) {
        this.title = title;
        this.list = list;
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

    public void add(ImgCard f) {
        list.add(f);
    }

    public void remove(ImgCard f) {
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
