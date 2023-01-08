package org.flashcards.Gui;

import org.flashcards.src.GuiApp;

import javax.swing.*;
import java.awt.*;


public class GetStarted extends JPanel {

    private JButton getStartedButton, settingsButton;
    private final Initializer initializer;

    public GetStarted(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
        addGetStartedButton();
        addSettingsButton();
    }

    private void getCustomTitle() {
        GUInitializer.changeFont("src/main/java/org/flashcards/Gui/fonts/BungeeShade-Regular.ttf");
        JLabel title = new JLabel("FLASHCARDS");
        title.setFont(new Font("Bungee Shade", Font.PLAIN, 63));
        title.setForeground(Color.white);
        title.setBounds(215, 119, 551, 96);
        add(title);
    }

    private void addGetStartedButton() {
        getStartedButton = new JButton("Get started");
        getStartedButton.setBackground(new java.awt.Color(149, 149, 149));
        getStartedButton.setOpaque(true);
        getStartedButton.setFont(new Font("Arbutus", Font.PLAIN, 14));
        getStartedButton.setBounds(398, 321, 152, 47);
        add(getStartedButton);
        getStartedButton.addActionListener(e -> {
                initializer.update(GUInitializer.Panel.Menu);
        });
    }

    private void addSettingsButton() {
        settingsButton = new JButton("Settings");
        settingsButton.setBackground(new java.awt.Color(149, 149, 149));
        settingsButton.setOpaque(true);
        settingsButton.setFont(new Font("Arbutus", Font.PLAIN, 14));
        settingsButton.setBounds(398, 404, 152, 47);
        add(settingsButton);
        settingsButton.addActionListener(e -> {

        });
    }

}
