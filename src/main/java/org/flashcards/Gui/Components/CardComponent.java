package org.flashcards.Gui.Components;

import java.awt.*;

public class CardComponent implements Rectangle{

    private final Color color;
    public CardComponent(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRoundRect(224,93,512,277,20,20);
        g.setColor(color);
        g.fillRoundRect(224,93,512,277,20,20);
    }
}
