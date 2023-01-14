package org.flashcards;

public class MementoImgCard {

    private Long id;

    private byte[] image;
    private String answer;

    public MementoImgCard(Long id, byte[] image, String answer) {
        this.id = id;
        this.image = image;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
