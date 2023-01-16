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
        if (!isDoneRight()) {
            iterationPosition += 1;
            return list.getList().get(iterationPosition);
        }
        return null;
    }

    @Override
    public Card prev() {
        if (!isDoneLeft()) {
            iterationPosition -= 1;
            return list.getList().get(iterationPosition);
        }
        return null;
    }

    @Override
    public boolean isDoneRight() {
        return list.getList().size() <= iterationPosition + 1;
    }

    @Override
    public boolean isDoneLeft() {
        return iterationPosition <= 0;
    }
}
