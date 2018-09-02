import java.awt.*;

public class OrangeBall extends Ball {
    public OrangeBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.ORANGE);
    }
    
    public int ability(Basket b) {
        b.setSpeed(3);
        return -20;
    }
}