/**
 * CIS 120 Game HW
 * (c) University of Pennsylvania
 * @version 2.1, Apr 2017
 */

import java.io.*;
import java.util.Collection;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact with one another. Take
 * time to understand how the timer interacts with the different methods and how it repaints the GUI
 * on every tick().
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

    // the state of the game logic
    private Basket basket;
    private Collection<Ball> balls;
    private int score;
    private String fileName = "files/highscores.txt";
    private String[] ballList = {"blue", "red", "green", "gray", "magenta", "yellow", "orange"};
    
    public boolean countdown = false;
    public boolean playing = false;
    private JLabel status; 
    private JLabel gameScore;
    private JLabel timeLeft;
    
    // Game constants
    public static final int COURT_WIDTH = 300;
    public static final int COURT_HEIGHT = 300;
    public static int basket_velocity = 5;
    public static int ball_velocity = 4;

    // Update interval for timer, in milliseconds
    public static int INTERVAL = 20;
    public static int TOTAL_TIME = 0;

    public GameCourt(JLabel status, JLabel gameScore, JLabel timeLeft) {
        // creates border around the court area, JComponent method
        setBorder(BorderFactory.createLineBorder(Color.BLACK));


        Timer timer = new Timer(INTERVAL, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(TOTAL_TIME % 700 == 0 && playing) {
                    createBall();
                }
                moveObj();
                TOTAL_TIME += INTERVAL;
            }
        });
        timer.start();

        setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    basket.setVx(-basket_velocity);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    basket.setVx(basket_velocity);
                }
            }

            public void keyReleased(KeyEvent e) {
                basket.setVx(0);
                basket.setVy(0);
            }
        });

        this.status = status;
        this.gameScore = gameScore;
        this.timeLeft = timeLeft;
    }

    /**
     * (Re-)set the game to its initial state.
     */
    public void reset() {
        basket = new Basket(COURT_WIDTH, COURT_HEIGHT);
        balls = new ArrayList<Ball>();
        Ball.setSize(25);
        basket_velocity = 5;
        score = 0;
        playing = false;
        TOTAL_TIME = 0;
        countdown = true;

        status.setText("Game Begins in 3");
        gameScore.setText("Score: " + 0);
        timeLeft.setText("Time Left: " + 60);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }
    
    /**
     * Called when the Game is initially run
     */    
    public void start_screen() {
        basket = new Basket(COURT_WIDTH, COURT_HEIGHT);
        balls = new ArrayList<Ball>();
        
        status.setText("Press Start to Begin");
        gameScore.setText("Score: " + 0);
        timeLeft.setText("Time Left: " + 60);

        // Make sure that this component has the keyboard focus
        requestFocusInWindow();
    }    
    
    public int getScore() {
        return score;
    }

    /**
     * Moves the balls downwards. Also checks to see if any of the balls intersects the basket.
     * If so, the ball's ability method is called. Additionally, it continuously updates time left and score. 
     * Ends the game after 60 seconds.
     */
    public void moveObj() {
        if (playing) {
            basket.move();
            Collection<Ball> removedBalls = new ArrayList<Ball>(); 
            for(Ball s: balls) {
                s.move();
                timeLeft.setText("Time Left: " + ((60000 - TOTAL_TIME)/1000 + 1));
                if(s.intersects(basket)) {
                    int num = s.ability(basket);
                    score += num;
                    status.setText("Caught Ball: " + num + " points");
                    gameScore.setText("Score: " + score);
                    removedBalls.add(s);
                } else if(s.hitWall()) {
                    status.setText("Missed Ball");
                    removedBalls.add(s);
                }
            }
            for(Ball s: removedBalls) {
                balls.remove(s);
            }
            
            if(TOTAL_TIME >= 60000) {
                timeLeft.setText("Time Left: " + 0);
                playing = false;
                status.setText("GAME OVER!");
                try {
                    checkHighscore(score);
                    displayHighscore();
                } catch(Exception e){
                    System.out.println("Error while getting score. " + e);
                }
            }

            // update the display
            repaint();
        }
        else if(countdown) {
            if(TOTAL_TIME >= 3000) {
                status.setText("Gooo!");
                playing = true;
                countdown = false;
                TOTAL_TIME = 0;
            } else if(TOTAL_TIME >= 2000) {
                status.setText("Game Begins in 1");
            } else if(TOTAL_TIME >= 1000) {
                status.setText("Game Begins in 2");
            } else {
                status.setText("Game Begins in 3");
            }
            repaint();
        }
    }
    
    /**
     * Adds a random ball to the game.
     */
    public void createBall() {
        Ball curr = createRandomBall();
        balls.add(curr);
    }
    
    /**
     * Picks a random ball from the list and instantiates it with COURT_WIDTH, COURT_HEIGHT, and ball_velocity
     */
    public Ball createRandomBall() {
        int i = (int) (ballList.length * Math.random());
        if(ballList[i].equals("blue")) {
            return new BlueBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("red")) {
            return new RedBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("green")) {
            return new GreenBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("yellow")) {
            return new YellowBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("gray")) {
            return new GrayBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("orange")) {
            return new OrangeBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else if(ballList[i].equals("magenta")) {
            return new MagentaBall(COURT_WIDTH, COURT_HEIGHT, ball_velocity);
        } else {
            return null;
        }
    }

    /**
     * Checks to see if the given score is a new highscore. If so, then the user is prompted for his/her name.
     * If not, then the user is told that he/she does not have a new highscore.
    */
    public void checkHighscore(int num) throws IOException {
        BufferedReader readScores = new BufferedReader(new FileReader(fileName));
        String highscore = "";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> allScores = new ArrayList<Integer>();
        while((highscore = readScores.readLine()) != null) {
            String[] temp = highscore.split(",");
            names.add(temp[0]);
            allScores.add(Integer.parseInt(temp[1]));
        }
        
        BufferedWriter writeScores = new BufferedWriter(new FileWriter(fileName));      
        if(allScores.isEmpty()){
            writeScores.write(enterNickname()+","+num+"\n");
            readScores.close();
            writeScores.close();
            return;
        }
        if(allScores.get(allScores.size() - 1) > num && allScores.size() == 5) {
            JOptionPane.showMessageDialog(null,
                "Unfortunately, you did not make the Leaderboard", "SORRY", JOptionPane.PLAIN_MESSAGE);
        } else if(allScores.get(allScores.size() - 1) > num) {
            allScores.add(num);
            names.add(enterNickname());
        } else{
            for(int i = 0; i < allScores.size(); i++) {
                if(allScores.get(i) <= num) {
                    allScores.add(i, num);
                    names.add(i, enterNickname());
                    break;
                }
            }
        }
        if(allScores.size() > 5) {
            names.remove(5);
            allScores.remove(5);
        }
        for(int i = 0; i < allScores.size(); i++) {
            writeScores.write(names.get(i)+","+allScores.get(i)+"\n");
        }
        readScores.close();
        writeScores.close();
    }    
    
     /**
     * Prompts the user to enter a nickname in a JOptionPane.
     */
    public String enterNickname() {
        String nickname = JOptionPane.showInputDialog("<html>Congratulations! You have made it to the leaderboard. "
                + "<br> Please enter a username</html>");
        return nickname;
    }
    
     /**
     * Display the highscore leaderboard as a JOptionPane.
     */
    public void displayHighscore() throws IOException {
        BufferedReader readScores = new BufferedReader(new FileReader(fileName));
        String highscore = "";
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> allScores = new ArrayList<Integer>();
        while((highscore = readScores.readLine()) != null) {
            String[] temp = highscore.split(",");
            names.add(temp[0]);
            allScores.add(Integer.parseInt(temp[1]));
        }
        readScores.close();
        JOptionPane.showMessageDialog(null,
                scores(names, allScores), "LEADERBOARD", JOptionPane.PLAIN_MESSAGE);
    } 
    
     /**
     * Returns a JPanel which is used to display the information on the Leaderboard.
     */
    public JPanel scores(ArrayList<String> names, ArrayList<Integer> num) {
        String[] columnNames = {"Rank", "Name", "Score"};
        Object[][] data = new Object[names.size()][3];
        for(int i = 0; i < names.size(); i++){
            for(int j = 0; j < 3; j++) {
                if(j == 0){
                    data[i][j] = i+1;
                }
                if(j == 1){
                    data[i][j] = names.get(i);
                }
                if(j==2){
                    data[i][j] = num.get(i);
                }
            }
        }
        JPanel highScores = new JPanel();
        JTable scoresTable = new JTable(data, columnNames);
        JScrollPane scTable = new JScrollPane(scoresTable);
        highScores.add(scTable);
        return highScores;
    }    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        basket.draw(g);
        for(Ball s: balls) {
            s.draw(g);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(COURT_WIDTH, COURT_HEIGHT);
    }
}