package org.flashcards.src;

public class ImgCard extends Card {

    protected byte[] imageQuestion;
    protected String imageDescription;

    public ImgCard(Long id, String answer, byte[] imageQuestion, String imageDescription) {
        super(id, answer);
        this.imageQuestion = imageQuestion;
        this.imageDescription = imageDescription;
    }


    public byte[] getImageQuestion() {
        return imageQuestion;
    }

    public void setImageQuestion(byte[] imageQuestion) {
        this.imageQuestion = imageQuestion;
    }

    public String getImageDescription() {
        return imageDescription;
    }

    public void setImageDescription(String imageDescription) {
        this.imageDescription = imageDescription;
    }

    @Override
    public void action() {
        this.flashcardState.action();
    }
}
