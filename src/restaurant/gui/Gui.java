package restaurant.gui;

import java.awt.*;

public interface Gui {

    public void updatePosition();
    public void draw(Graphics2D g);
    public boolean isPresent();

}
