import java.awt.*;

/**
 * A basic game object starting in the bottom middle of the game court. It is displayed as a
 * brown rectangle.
 */
public class Basket extends GameObj {
    public static final int HEIGHT = 10;
    public static final int WIDTH = 40;
    public static final int INIT_VEL_X = 0;
    public static final int INIT_VEL_Y = 0;

    public Basket(int courtWidth, int courtHeight) {
        super(INIT_VEL_X, INIT_VEL_Y, courtWidth/2 - 20, courtHeight - 10, WIDTH, HEIGHT, courtWidth, courtHeight);
    }
    
    public void setWidthBasket(int newWidth) {
        setWidth(newWidth);
    }
    
    public int getWidthBasket() {
        return getWidth();
    }    
    
    public void setSpeed(int newSpeed) {
        GameCourt.basket_velocity = newSpeed;
    }
    
    public int getSpeed() {
        return GameCourt.basket_velocity;
    }    

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(102,51,0));
        g.fillRect(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}