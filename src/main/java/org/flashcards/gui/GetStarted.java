package org.flashcards.gui;


import javax.swing.*;
import java.awt.*;


public class GetStarted extends JPanel {

    private final Initializer initializer;

    public GetStarted(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(GUInitializer.backgroundColor);
        setLayout(null);
        getCustomTitle();
        addGetStartedButton();
//        addSettingsButton();
    }

    private void getCustomTitle() {
        GUInitializer.changeFont("src/main/resources/fonts/BungeeShade-Regular.ttf");
        JLabel title = new JLabel("FLASHCARDS");
        title.setFont(new Font("Bungee Shade", Font.PLAIN, 63));
        title.setForeground(Color.white);
        title.setBounds(215, 119, 551, 96);
        add(title);
    }

    private void addGetStartedButton() {
        JButton getStartedButton = new JButton("Get started");
        getStartedButton.setBackground(GUInitializer.getStartedButtonsColor);
        getStartedButton.setOpaque(true);
        getStartedButton.setFont(new Font("Arbutus", Font.PLAIN, 14));
        getStartedButton.setBounds(398, 321, 152, 47);
        add(getStartedButton);
        getStartedButton.addActionListener(e -> {
            initializer.update(GUInitializer.Panel.Menu);
        });
    }

//    private void addSettingsButton() {
//        JButton settingsButton = new JButton("Settings");
//        settingsButton.setBackground(GUInitializer.getStartedButtonsColor);
//        settingsButton.setOpaque(true);
//        settingsButton.setFont(new Font("Arbutus", Font.PLAIN, 14));
//        settingsButton.setBounds(398, 404, 152, 47);
//        add(settingsButton);
//        settingsButton.addActionListener(e -> {
//
//        });
//    }
}
