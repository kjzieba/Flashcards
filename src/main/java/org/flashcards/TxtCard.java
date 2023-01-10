package org.flashcards;

import javax.swing.*;

public class TxtCard extends Card {

    protected String textQuestion;

    public TxtCard(Long id, String textQuestion, String answer) {
        super(id, answer);
        this.textQuestion = textQuestion;
    }


    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    @Override
    public void action(JButton button) {
        this.flashcardState.action(button);
    }

    @Override
    public String toString() {
        return id + "|" + textQuestion + "|" + answer + "|" + flashcardState;
    }
}
