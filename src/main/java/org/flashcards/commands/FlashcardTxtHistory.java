package org.flashcards.commands;

import org.flashcards.MementoTxtCard;

import java.util.Stack;

public class FlashcardTxtHistory {
    private final Stack<MementoTxtCard> history = new Stack<>();

    public void push(MementoTxtCard mementoTxtCard) {
        history.push(mementoTxtCard);
    }

    public MementoTxtCard pop() {
        return history.pop();
    }

    public MementoTxtCard peek() {
        return history.peek();
    }

    public boolean isEmpty() {
        return history.isEmpty();
    }

    public void clear() {
        history.removeAllElements();
    }
}
