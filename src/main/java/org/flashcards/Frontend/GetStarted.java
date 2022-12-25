package org.flashcards.Frontend;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GetStarted extends JFrame {
    private ImageIcon image;
    private JLabel label;
    private JFrame frame;
    private JButton getStartedButton, settingsButton;

    public GetStarted() {
        frame = new JFrame();
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
                frame.setIconImage(iconImage);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        frame.setTitle("Flashcards");
        frame.setSize(960, 560);
        frame.setMaximumSize(new Dimension(960, 560));
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        image = new ImageIcon("src/main/resources/img/StartScreen.png");
        label = new JLabel("", image, JLabel.CENTER);
        label.setBounds(0, 0, 960, 540);
        getButtons();

        label.add(settingsButton);
        label.add(getStartedButton);
        frame.add(label);
        frame.setVisible(true);

    }

    private void getButtons() {
        getStartedButton = new JButton();
        getStartedButton.setIcon(new ImageIcon("src/main/resources/img/getStartedButton.png"));
        getStartedButton.setBounds(400, 321, 148, 45);

        settingsButton = new JButton();
        settingsButton.setIcon(new ImageIcon("src/main/resources/img/settingsButton.png"));
        settingsButton.setBounds(400, 404, 148, 45);
    }

}
