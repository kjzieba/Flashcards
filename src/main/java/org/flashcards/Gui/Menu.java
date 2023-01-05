package org.flashcards.Gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {
    private final Initializer initializer;

    public Menu(Initializer initializer) {
        this.initializer = initializer;
        setPreferredSize(new Dimension(960, 560));
        setBackground(new java.awt.Color(41, 41, 41));
        setLayout(null);
        getCustomTitle();
    }

    private void getCustomTitle() {
        JLabel title = new JLabel("No flashcards yet");
        title.setFont(new Font("Arbutus", Font.PLAIN, 40));
        title.setForeground(Color.white);
        title.setBounds(80, 120, 700, 50);
        add(title);
    }

}
