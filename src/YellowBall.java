import java.awt.*;

public class YellowBall extends Ball {
    public YellowBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.YELLOW);
    }
    
    public int ability(Basket b) {
        setSize(35);
        return 20;
    }
}