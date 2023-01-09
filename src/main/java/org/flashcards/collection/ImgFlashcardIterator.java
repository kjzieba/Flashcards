package org.flashcards.collection;

import org.flashcards.Card;

public class ImgFlashcardIterator implements Iterator {
    private final ImgFlashcardCollection list;
    private int iterationPosition;

    public ImgFlashcardIterator(ImgFlashcardCollection list) {
        this.list = list;
        this.iterationPosition = -1;
    }

    @Override
    public Card next() {
        if (!isDone()) {
            iterationPosition += 1;
            return list.getList().get(iterationPosition);
        }
        return null;
    }

    @Override
    public boolean isDone() {
        return list.getList().size() <= iterationPosition;
    }
}
