package org.flashcards.Gui.Components;

import java.awt.*;

public class SmallCardComponent implements Rectangle{

    private final Color color;
    public SmallCardComponent(Color color) {
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRoundRect(13,409,209,119,10,10);
        g.setColor(color);
        g.fillRoundRect(13,409,209,119,10,10);
    }
}
