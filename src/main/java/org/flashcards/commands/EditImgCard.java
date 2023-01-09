package org.flashcards.commands;

import org.flashcards.Card;
import org.flashcards.ImgCard;
import org.flashcards.enums.States;
import org.flashcards.repositories.ImgCardRepo;

import java.util.Scanner;

public class EditImgCard implements Command{

    Scanner scanner = new Scanner(System.in);
    private final ImgCardRepo imgCardRepo;

    private final ComHistory comHistory;

    private final Long cardId;

    boolean end = false;

    public EditImgCard(ImgCardRepo imgCardRepo, ComHistory comHistory, Long cardId) {
        this.imgCardRepo = imgCardRepo;
        this.comHistory = comHistory;
        this.cardId = cardId;
    }


    @Override
    public void execute() {
        while (!end) {
            System.out.println("Choose an option: A - change answer, I - change image, S - change state, D - change description  R - return");
            String function = scanner.nextLine();
            switch (function) {
                case "A" -> {
                    Card card = imgCardRepo.queryFromRepo(cardId);
                    String answer = scanner.nextLine();
                    Command changeAnswer = new ChgAns(comHistory, card, answer);
                    changeAnswer.execute();
                }
                case "S" -> {
                    Card card = imgCardRepo.queryFromRepo(cardId);
                    String stateString = scanner.nextLine();
                    States state = States.valueOf(stateString);
                    Command changeState = new ChgState(comHistory, card, state);
                    changeState.execute();
                }
                case "D" -> {
                    ImgCard flashcard = imgCardRepo.queryFromRepo(cardId);
                    String description = scanner.nextLine();
                    Command changeDescription = new ChgImgDesc(comHistory, flashcard, description);
                    changeDescription.execute();
                }
                case "I" -> {
                    ImgCard flashcard = imgCardRepo.queryFromRepo(cardId);
                    String description = scanner.nextLine();
                    Command changeImage = new ChgImg(comHistory, flashcard, description);
                    changeImage.execute();
                }
                case "R" -> {
                    comHistory.push(this);
                    end = true;
                }
            }
        }
    }
}
