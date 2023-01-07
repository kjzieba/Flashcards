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
            System.out.println("Choose an option: A - add flashcard, E - edit flashcard, D - delete flashcard, R - return");
            String function = scanner.nextLine();
            switch (function) {
                case "T" -> {
                    String title = scanner.nextLine();
                    Command changeTitle = new ChgImgTitle(comHistory, imgCardRepo, title);
                    changeTitle.execute();
                }
                case "A" -> {
                    System.out.println("Enter an Id");
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    if(imgCardRepo.flashcards.contains(imgCardRepo.queryFromRepo(id))){
                        System.out.println("Id is already taken");
                    }
                    else{
                        System.out.println("Enter an image");
                        String image = scanner.nextLine();
                        System.out.println("Enter an answer");
                        String answer = scanner.nextLine();
                        System.out.println("Enter an description");
                        String description = scanner.nextLine();
                        Command addImgCard = new AddImgCard(id, comHistory, imgCardRepo, answer, image, description);
                        addImgCard.execute();
                    }
                }
                case "D" -> {
                    System.out.println("Enter an Id");
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    Command delImgCard = new DelImgCard(comHistory,imgCardRepo,id);
                    delImgCard.execute();

                }
                case "E" -> {
                    String cardId = scanner.nextLine();
                    Long id = Long.parseLong(cardId);
                    Command editImgCard = new EditImgCard(imgCardRepo, comHistory, id);
                    editImgCard.execute();
                }
                case "R" -> {
                    comHistory.push(this);
                    end = true;
                }
            }
        }

    }

    public void changeTitle(TxtCardRepo txtCardRepo, String title) {
        txtCardRepo.setTitle(title);
    }
}
