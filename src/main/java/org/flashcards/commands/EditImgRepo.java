package org.flashcards.commands;

import org.flashcards.repositories.CardsRepo;
import org.flashcards.repositories.ImgCardRepo;
import org.flashcards.repositories.TxtCardRepo;

import java.util.ArrayList;
import java.util.Scanner;

public class EditImgRepo implements Command {
    Scanner scanner = new Scanner(System.in);
    private final ImgCardRepo imgCardRepo;

    private final ComHistory comHistory;

    private final ArrayList<CardsRepo> cardsRepo;

    private Long id = Long.parseLong("1");
    boolean end = false;

    public EditImgRepo(ImgCardRepo imgCardRepo, ComHistory comHistory, ArrayList<CardsRepo> cardsRepo) {
        this.imgCardRepo = imgCardRepo;
        this.comHistory = comHistory;
        this.cardsRepo = cardsRepo;
    }

    @Override
    public void execute() {
        try {
            cardsRepo.add((CardsRepo) imgCardRepo.clone());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
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
