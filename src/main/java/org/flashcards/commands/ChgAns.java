package org.flashcards.commands;

import org.flashcards.Card;

public class ChgAns implements Command{
    private final Card card;

    private final String answer;

    public ChgAns(Card card, String answer) {
        this.card = card;
        this.answer = answer;
    }

    @Override
    public void execute() {
        card.setAnswer(answer);
    }
}
