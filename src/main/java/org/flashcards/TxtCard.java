package org.flashcards;

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
    public void action() {
        this.flashcardState.action();
    }

    @Override
    public String toString() {
        return id + "|" + textQuestion + "|" + answer + "|" + flashcardState;
    }
}
