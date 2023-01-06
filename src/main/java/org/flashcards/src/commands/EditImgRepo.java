package org.flashcards.src.commands;

import org.flashcards.src.Card;
import org.flashcards.src.ImgCard;
import org.flashcards.src.enums.States;
import org.flashcards.src.repositories.AllCards;
import org.flashcards.src.repositories.ImgCardRepo;
import org.flashcards.src.repositories.TxtCardRepo;

import java.util.Scanner;

public class EditImgRepo implements Command {
    Scanner scanner = new Scanner(System.in);
    private final ImgCardRepo imgCardRepo;

    private final ComHistory comHistory;

    private final AllCards allCards;
    boolean end = false;

    public EditImgRepo(ImgCardRepo imgCardRepo, ComHistory comHistory, AllCards allCards) {
        this.imgCardRepo = imgCardRepo;
        this.comHistory = comHistory;
        this.allCards = allCards;
    }

    @Override
    public void execute() {
        while (!end) {
            System.out.println("Choose an option");
            String function = scanner.nextLine();
            switch (function) {
                case "Change Title" -> {
                    String title = scanner.nextLine();
                    Command changeTitle = new ChgImgTitle(comHistory, imgCardRepo, title);
                    changeTitle.execute();
                }
                case "Change Answer" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    Card card = imgCardRepo.queryFromRepo(id);
                    String answer = scanner.nextLine();
                    Command changeAnswer = new ChgAns(comHistory, card, answer);
                    changeAnswer.execute();
                }
                case "Change State" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    Card card = imgCardRepo.queryFromRepo(id);
                    String stateString = scanner.nextLine();
                    States state = States.valueOf(stateString);
                    Command changeState = new ChgState(comHistory, card, state);
                    changeState.execute();
                }
                case "Change Image Description" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    ImgCard flashcard = imgCardRepo.queryFromRepo(id);
                    String description = scanner.nextLine();
                    Command changeDescription = new ChgImgDesc(comHistory, flashcard, description);
                    changeDescription.execute();
                }
                case "Change Image" -> {
                    String idString = scanner.nextLine();
                    Long id = Long.parseLong(String.valueOf(idString));
                    ImgCard flashcard = imgCardRepo.queryFromRepo(id);
                    String description = scanner.nextLine();
                    Command changeImage = new ChgImg(comHistory, flashcard, description);
                    changeImage.execute();
                }
                case "Save" -> {
                    allCards.addToAll(imgCardRepo);
                }
            }
        }

    }

    public void changeTitle(TxtCardRepo txtCardRepo, String title) {
        txtCardRepo.setTitle(title);
    }
}
