package org.flashcards.commands;

import org.flashcards.Card;

public class ChgAns implements Command{
    private final ComHistory comHistory;

    private final Card card;

    private final String answer;

    public ChgAns(ComHistory comHistory, Card card, String answer) {
        this.comHistory = comHistory;
        this.card = card;
        this.answer = answer;
    }

    @Override
    public void execute() {
        card.setAnswer(answer);
        comHistory.push(this);
    }
}
