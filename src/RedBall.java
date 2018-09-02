import java.awt.*;

public class RedBall extends Ball {
    public RedBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.RED);
    }
    
    public int ability(Basket b) {
        b.setSpeed(7);
        return -5;
    }
}