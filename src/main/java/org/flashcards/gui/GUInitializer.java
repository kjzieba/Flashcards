package org.flashcards.gui;

import org.flashcards.App;
import org.flashcards.collection.TxtFlashcardCollection;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUInitializer extends JFrame implements Initializer {
    private final GetStarted getStarted;
    private final Menu menu;
    private final Add add;
    private final AddTxt addTxt;
    private final ChooseMode chooseMode;
    private final LearnMode learnMode;
    private final TestMode testMode;
    private final FlashCardsMode flashCardsMode;
    private final Result result;
    private final QuestionsAmount questionsAmount;
    private final AddImg addImg;
    private final EditTextRepo editTextRepo;
    public Long editID = 0L;
    public static Boolean flag;


    public static Color backgroundColor = new Color(41, 41, 41);
    public static Color buttonColor = new Color(67, 69, 74);
    public static Color getStartedButtonsColor = new Color(149, 149, 149);

    public GUInitializer() {

        File icon = new File("src/main/resources/img/FlashCardsIcon.png");
        BufferedImage iconImage;
        try {
            String os = System.getProperty("os.name");
            iconImage = ImageIO.read(icon);
            if (os.contains("Mac OS X")) {
                final Taskbar taskbar = Taskbar.getTaskbar();
                taskbar.setIconImage(iconImage);
            } else {
                setIconImage(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        changeFont("src/main/resources/fonts/Arbutus-Regular.ttf");

        getStarted = new GetStarted(this);
        menu = new Menu(this);
        add = new Add(this);
        addTxt = new AddTxt(this);
        chooseMode = new ChooseMode(this);
        learnMode = new LearnMode(this);
        testMode = new TestMode(this);
        flashCardsMode = new FlashCardsMode(this);
        result = new Result(this);
        questionsAmount = new QuestionsAmount(this);
        addImg = new AddImg(this);
        editTextRepo = new EditTextRepo(this);

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        setTitle("Flashcards");
        add(getStarted);
        pack();
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changePanel(Panel panel) {
        switch (panel) {
            case GetStarted -> {
                menu.setVisible(false);
                setContentPane(getStarted);
                pack();
                getStarted.setVisible(true);
            }
            case Menu -> {
                getStarted.setVisible(false);
                menu.checkCards();
                setContentPane(menu);
                pack();
                menu.setVisible(true);
            }
            case Add -> {
                menu.setVisible(false);
                setContentPane(add);
                pack();
                add.setVisible(true);
            }
            case AddTxt -> {
                add.setVisible(false);
                setContentPane(addTxt);
                pack();
                addTxt.setVisible(true);
            }
            case AddImg -> {
                add.setVisible(false);
                setContentPane(addImg);
                pack();
                addImg.setVisible(true);
            }
            case ChooseMode -> {
                addTxt.setVisible(false);
                setContentPane(chooseMode);
                pack();
                chooseMode.setVisible(true);
            }
            case LearnMode -> {
                chooseMode.setVisible(false);
                setContentPane(learnMode);
                pack();
                learnMode.setVisible(true);
                learnMode.setRepo();
                learnMode.setCard();
            }
            case TestMode -> {
                questionsAmount.setVisible(false);
                setContentPane(testMode);
                pack();
                testMode.setVisible(true);
                testMode.setList();
                testMode.setWordLabel();
                TestMode.currentQuestion = 1;
                testMode.updateLabel();
            }
            case FlashCardsMode -> {
                chooseMode.setVisible(false);
                setContentPane(flashCardsMode);
                pack();
                flashCardsMode.setVisible(true);
                flashCardsMode.setRepo();
                flashCardsMode.setCard();
            }
            case Result -> {
                chooseMode.setVisible(false);
                setContentPane(result);
                pack();
                result.setVisible(true);
                if (flag) {
                    result.setScore("Correct", "Incorrect", testMode.getRightAnswers(), testMode.getWrongAnswers());
                    testMode.setRightAnswers(0);
                    testMode.setWrongAnswers(0);
                }
                else if (!flag) {
                    result.setScore("Know", "Still Learning", learnMode.getKnowWords(), learnMode.getStillLearningWords());
                    learnMode.setKnowWords(0);
                    learnMode.setStillLearningWords(0);
                }
            }
            case QuestionsAmount -> {
                chooseMode.setVisible(false);
                setContentPane(questionsAmount);
                pack();
                questionsAmount.setVisible(true);
                QuestionsAmount.setAmount(1);
                questionsAmount.updateGuiAmount();
            }
            case EditTxtRepo -> {
                menu.setVisible(false);
                setContentPane(editTextRepo);
                pack();
                editTextRepo.setVisible(true);
                editTextRepo.setRepo();
                editTextRepo.getScrollPane();
                editTextRepo.getNameRepository();
            }
        }
    }

    @Override
    public void update(Panel panel) {
        changePanel(panel);
    }

    public enum Panel {
        GetStarted,
        Menu,
        Add,
        ChooseMode,
        FlashCardsMode,
        LearnMode,
        TestMode,
        Result,
        AddTxt,
        AddImg,
        QuestionsAmount,
        EditTxtRepo
    }

    public static void changeFont(String path) {
        try {
            GraphicsEnvironment font = GraphicsEnvironment.getLocalGraphicsEnvironment();
            font.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(path)));
        } catch (IOException | FontFormatException e) {
            System.out.println("Font not found");
            e.printStackTrace();
        }
    }

}
