package org.flashcards.commands;

import org.flashcards.MementoImgCard;

import java.util.Stack;

public class FlashcardImgHistory {
    private final Stack<MementoImgCard> history = new Stack<>();

    public void push(MementoImgCard mementoImgCard) {
        history.push(mementoImgCard);
    }

    public MementoImgCard pop() {
        return history.pop();
    }

    public MementoImgCard peek() {
        return history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public void clear() {
        history.removeAllElements();
    }
}
