import java.awt.*;

public class GrayBall extends Ball {
    public GrayBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.GRAY);
    }
    
    public int ability(Basket b) {
        b.setWidthBasket(20);
        return -15;
    }
}