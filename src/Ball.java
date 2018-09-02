/**
 * Inspired by Circle.java
 */

import java.awt.*;

/**
 * A basic game object representing a ball with abilities.
 */
public abstract class Ball extends GameObj implements Comparable<Ball> {
    public static int size = 25;
    
    private Color color;

    public Ball(int courtWidth, int courtHeight, int velocity) {
        super(0, velocity, getInitialPosition(courtWidth), 0, size, size, courtWidth, courtHeight);
    }
    
    private static int getInitialPosition(int courtWidth) {
        int spawnRightBound = courtWidth - size - 1;
        int pos = (int) (Math.random() * spawnRightBound);
        return pos;
    }
    
    public void setColor(Color t) {
        color = t;
    }
    
    public static void setSize(int num) {
        size = num;
    }
    
    public static int getSize() {
        return size;
    }
    
    public int compareTo(Ball c) {
        return 0;
    }
    
    public abstract int ability(Basket b);

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillOval(this.getPx(), this.getPy(), this.getWidth(), this.getHeight());
    }
}