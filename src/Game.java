/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("CATCH THEM ALL!");
        frame.setLocation(300, 300);

        final JPanel main_bottom = new JPanel(new BorderLayout());
        frame.add(main_bottom, BorderLayout.SOUTH);
        
        final JPanel status_panel = new JPanel();
        main_bottom.add(status_panel, BorderLayout.NORTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);
                
        final JPanel instruction_panel = new JPanel();
        main_bottom.add(instruction_panel, BorderLayout.SOUTH);
        
        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        final JLabel score = new JLabel("Score: " + 0);
        control_panel.add(score);
        
        final JLabel timeLeft = new JLabel("Time Left: " + 60);

        // Main playing area
        final GameCourt court = new GameCourt(status, score, timeLeft);
        frame.add(court, BorderLayout.CENTER);

        final JButton reset = new JButton("Start/Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        
        final JButton instr = new JButton("Instructions");
        instr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                    instructions(), "Instructions", JOptionPane.PLAIN_MESSAGE);
                court.requestFocusInWindow();
            }
        });        
        instruction_panel.add(instr);
        
        control_panel.add(reset);
        control_panel.add(timeLeft);

        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        court.start_screen();
    }

    public JPanel instructions() {
        final JPanel instruct = new JPanel();
        instruct.setLayout(new BoxLayout(instruct, BoxLayout.Y_AXIS));
        final JLabel desc = new JLabel("<html>The goal of this game is to use the brown paddle/basket to <br>" +
                                       "catch the dropping balls and earn as many points. The basket <br>" + 
                                       "is controlled by the left and right arrow keys on your keyboard. <br>" + 
                                       "However you have to be careful when you catch the balls because <br>" +
                                       "each of the colored balls are worth a different number of points <br>" +
                                       "and have different abilities as you will see below.<br></html>");
        final JLabel balls_desc = new JLabel("<html><br><u>List of all Balls</u></html>");
        final JLabel blue = new JLabel("<html><br><br><span style='color:blue'>Blue Ball</span>: -5 points, Balls decrease from default size by 60%</html>");
        final JLabel red = new JLabel("<html><br><span style='color:red'>Red Ball</span>: -5 points, Basket speed increases by 40% from default speed</html>");
        final JLabel orange = new JLabel("<html><br><span style='color:orange'>Orange Ball</span>: -20 points, Basket speed decreases by 40% from default speed</html>");
        final JLabel magenta = new JLabel("<html><br><span style='color:#ff00ff'>Magenta Ball</span>: +10 points, Basket size increases by 50% from default size</html>");
        final JLabel gray = new JLabel("<html><br><span style='color:gray'>Gray Ball</span>: -15 points, Basket size decreases by 50% from default size</html>");
        final JLabel yellow = new JLabel("<html><br><span style='color:yellow'>Yellow Ball</span>: +20 points, Balls increase from default size by 40%</html>");
        final JLabel green = new JLabel("<html><br><span style='color:green'>Green Ball</span>: +15 points, Basket size, basket speed, and ball size reset to default values</html>");
        instruct.add(desc);  
        instruct.add(balls_desc); 
        instruct.add(blue); 
        instruct.add(green); 
        instruct.add(magenta); 
        instruct.add(red); 
        instruct.add(orange); 
        instruct.add(yellow); 
        instruct.add(gray); 
        return instruct;
    }
    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
    }
}