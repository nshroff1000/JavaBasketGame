import java.awt.*;

public class BlueBall extends Ball {
    public BlueBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.BLUE);
    }
    
    public int ability(Basket b) {
        setSize(10);
        return -5;
    }
}