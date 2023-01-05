package org.flashcards.Gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUInitializer extends JFrame implements Initializer {
    private final GetStarted getStarted;
    private final Menu menu;

    public GUInitializer() {

        File icon = new File("src/main/resources/img/FlashCardsIcon.png");
        BufferedImage iconImage;
        try {
            String os = System.getProperty("os.name");
            iconImage = ImageIO.read(icon);
            if (os.contains("Mac OS X")) {
                final Taskbar taskbar = Taskbar.getTaskbar();
                taskbar.setIconImage(iconImage);
            }
            else {
                setIconImage(iconImage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        changeFont("src/main/java/org/flashcards/Gui/fonts/Arbutus-Regular.ttf");

        getStarted = new GetStarted(this);
        menu = new Menu(this);

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
                setContentPane(menu);
                pack();
                menu.setVisible(true);
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
        Edit,
        ChooseMode,
        FlashCards,
        Learn,
        Test,
        Result,

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
