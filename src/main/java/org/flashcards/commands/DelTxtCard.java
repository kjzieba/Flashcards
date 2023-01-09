package org.flashcards.commands;


import org.flashcards.collection.TxtFlashcardCollection;

public class DelTxtCard implements Command{
    private final ComHistory history;
    private final TxtFlashcardCollection txtCardRepo;
    private final Long cardId;

    public DelTxtCard(ComHistory history, TxtFlashcardCollection txtCardRepo, Long cardId) {
        this.history = history;
        this.txtCardRepo = txtCardRepo;
        this.cardId = cardId;
    }

    @Override
    public void execute() {
//        txtCardRepo.remove(cardId);
        history.push(this);
    }
}
