import static org.junit.Assert.*;
import org.junit.*;

import org.junit.Test;

public class GameTest {
    
    @Test
    public void testBlueBallAbility() {
        int score = 0;
        Ball testBall = new BlueBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(Ball.getSize() == 10);
        Ball.setSize(25);
        assertTrue(score == -5);
    }
    
    @Test
    public void testYellowBallAbility() {
        int score = 0;
        Ball testBall = new YellowBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(Ball.getSize() == 35);
        Ball.setSize(25);
        assertTrue(score == 20);
    }    
    
    @Test
    public void testGreenBallAbility() {
        int score = 0;
        Ball testBall = new GreenBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        Ball.setSize(10);
        testBasket.setWidthBasket(10);
        testBasket.setSpeed(10);
        score += testBall.ability(testBasket);
        assertTrue(Ball.getSize() == 25);
        assertTrue(testBasket.getWidthBasket() == 40);
        assertTrue(testBasket.getSpeed() == 5);
        assertTrue(score == 15);
    }   
    
    @Test
    public void testRedBallAbility() {
        int score = 0;
        Ball testBall = new RedBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(testBasket.getSpeed() == 7);
        testBasket.setSpeed(5);
        assertTrue(score == -5);
    } 
    
    @Test
    public void testOrangeBallAbility() {
        int score = 0;
        Ball testBall = new OrangeBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(testBasket.getSpeed() == 3);
        testBasket.setSpeed(5);
        assertTrue(score == -20);
    } 
    
    @Test
    public void testMagentaBallAbility() {
        int score = 0;
        Ball testBall = new MagentaBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(testBasket.getWidthBasket() == 60);
        assertTrue(score == 10);
    } 
    
    @Test
    public void testGrayBallAbility() {
        int score = 0;
        Ball testBall = new GrayBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBall.ability(testBasket);
        assertTrue(testBasket.getWidthBasket() == 20);
        assertTrue(score == -15);
    }  
    
    @Test
    public void testBlueAndYellowBallAbility() {
        int score = 0;
        Ball testBallOne = new BlueBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallTwo = new YellowBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBallOne.ability(testBasket);
        assertTrue(Ball.getSize() == 10);
        score += testBallTwo.ability(testBasket);
        assertTrue(Ball.getSize() == 35);
        Ball.setSize(25);
        assertTrue(score == 15);
    }  
    
    @Test
    public void testMagentaAndGrayBallAbility() {
        int score = 0;
        Ball testBallOne = new GrayBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallTwo = new MagentaBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        assertTrue(testBasket.getWidthBasket() == 40);
        score += testBallOne.ability(testBasket);
        assertTrue(testBasket.getWidthBasket() == 20);
        score += testBallTwo.ability(testBasket);        
        assertTrue(testBasket.getWidthBasket() == 60);
        assertTrue(score == -5);
    }
    
    @Test
    public void testRedAndOrangeBallAbility() {
        int score = 0;
        Ball testBallOne = new OrangeBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallTwo = new RedBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        assertTrue(testBasket.getSpeed() == 5);
        score += testBallOne.ability(testBasket);
        assertTrue(testBasket.getSpeed() == 3);
        score += testBallTwo.ability(testBasket);        
        assertTrue(testBasket.getSpeed() == 7);
        testBasket.setSpeed(5);
        assertTrue(score == -25);
    }    
    
    @Test
    public void testBlueGreenAndRedBallAbility() {
        int score = 0;
        Ball testBallOne = new BlueBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallTwo = new RedBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallThree = new GreenBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBallOne.ability(testBasket);
        assertTrue(Ball.getSize() == 10);
        score += testBallTwo.ability(testBasket);
        assertTrue(testBasket.getSpeed() == 7);
        score += testBallThree.ability(testBasket);
        assertTrue(Ball.getSize() == 25);        
        assertTrue(testBasket.getSpeed() == 5); 
        assertTrue(testBasket.getWidthBasket() == 40); 
        assertTrue(score == 5);
    } 
    
    @Test
    public void testMagentaGreenAndOrangeBallAbility() {
        int score = 0;
        Ball testBallOne = new MagentaBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallTwo = new OrangeBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Ball testBallThree = new GreenBall(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT, GameCourt.ball_velocity);
        Basket testBasket = new Basket(GameCourt.COURT_WIDTH, GameCourt.COURT_HEIGHT);
        score += testBallOne.ability(testBasket);
        assertTrue(testBasket.getWidthBasket() == 60);
        score += testBallTwo.ability(testBasket);
        assertTrue(testBasket.getSpeed() == 3);
        score += testBallThree.ability(testBasket);
        assertTrue(Ball.getSize() == 25);        
        assertTrue(testBasket.getSpeed() == 5);
        assertTrue(testBasket.getWidthBasket() == 40);         
        assertTrue(score == 5);
    }     
    
}