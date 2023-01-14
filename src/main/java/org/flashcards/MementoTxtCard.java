package org.flashcards;

public class MementoTxtCard {

    private Long id;
    private String textQuestion;
    private String answer;

    public MementoTxtCard(Long id, String textQuestion, String answer) {
        this.id = id;
        this.textQuestion = textQuestion;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextQuestion() {
        return textQuestion;
    }

    public void setTextQuestion(String textQuestion) {
        this.textQuestion = textQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
