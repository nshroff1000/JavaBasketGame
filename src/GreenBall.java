import java.awt.*;

public class GreenBall extends Ball {
    public GreenBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.GREEN);
    }
    
    public int ability(Basket b) {
        setSize(25);
        b.setWidthBasket(40);
        b.setSpeed(5);
        return 15;
    }
}