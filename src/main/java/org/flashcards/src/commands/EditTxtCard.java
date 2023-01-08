package org.flashcards.src.commands;

import org.flashcards.src.Card;
import org.flashcards.src.TxtCard;
import org.flashcards.src.enums.States;
import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.Scanner;

public class EditTxtCard implements Command{
    Scanner scanner = new Scanner(System.in);
    private final TxtCardRepo txtCardRepo;

    private final ComHistory comHistory;

    private final Long cardId;

    boolean end = false;

    public EditTxtCard(TxtCardRepo txtCardRepo, ComHistory comHistory, Long cardId) {
        this.txtCardRepo = txtCardRepo;
        this.comHistory = comHistory;
        this.cardId = cardId;
    }

    @Override
    public void execute() {
        while (!end) {
            System.out.println("Choose an option: A - change answer, Q - change question, S - change state,  R - return");
            String function = scanner.nextLine();
            switch (function) {
                case "A" -> {
                    Card card = txtCardRepo.queryFromRepo(cardId);
                    String answer = scanner.nextLine();
                    Command changeAnswer = new ChgAns(comHistory, card, answer);
                    changeAnswer.execute();
                }
                case "S" -> {
                    Card card = txtCardRepo.queryFromRepo(cardId);
                    String stateString = scanner.nextLine();
                    States state = States.valueOf(stateString);
                    Command changeState = new ChgState(comHistory, card, state);
                    changeState.execute();
                }
                case "Q" -> {
                    TxtCard flashcard = txtCardRepo.queryFromRepo(cardId);
                    String question = scanner.nextLine();
                    Command changeQuestion = new ChangeTxt(comHistory, flashcard, question);
                    changeQuestion.execute();
                }
                case "R" -> {
                    comHistory.push(this);
                    end = true;
                }
            }
        }
    }
}
