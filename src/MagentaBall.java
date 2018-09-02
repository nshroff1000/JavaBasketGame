import java.awt.*;

public class MagentaBall extends Ball {
    public MagentaBall(int courtWidth, int courtHeight, int velocity) {
        super(courtWidth, courtHeight, velocity);
        this.setColor(Color.MAGENTA);
    }
    
    public int ability(Basket b) {
        b.setWidthBasket(60);
        return 10;
    }
}