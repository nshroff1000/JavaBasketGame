=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: nshroff1
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=



===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections (Specifically ArrayList): Originally I was going to use Collections for a different purpose, 
  however, when I began implementing my game, I realized that I needed to find a way to maintain a list of all
  the balls on the screen at any given time. I needed to maintain this list inorder to know which balls to move down when
  I call the moveObj() method each time. In addition, this list allows me to iterate through all the balls on the screen
  and see if any of them intersect the basket. When the balls intersect the basket or miss the basket, then the ball leaves 
  the screen so I remove it from the collection. I decided to use a ArrayList to maintain the list of balls on the screen
  because it is easy to add and delete new balls in ArrayList. In addition, I couldn't use a Set for this purpose, because there
  could be two balls of the same color on the screen at the same time. Additionally, I couldn't use an array for this
  purpose since the number of balls on the screen do not stay the same at all times.

  2. I/O for high scores: I used I/O to maintain a list of the top 5 highscores. 
  My text file "highscores.txt" store the username and the score for each of the top 5 scores in sorted order by score.
  After each game, my program checks if the user's score made the top 5. If it makes the top 5, then the user is 
  prompted to type in his nickname. After he types his nickname, the highscore leaderboard shows up. If the user
  does not make the top 5, the user is told that he/she did not make the leaderboard and then the leaderboard shows up on the screen.
  I used I/O to save highscores because then no matter where you run it, the highscores will remain the same.


  3. Dynamic Dispatch - I have a Ball Abstract Class with an abstract method called "ability(Basket b)". Each of my 
  colored balls implements this method. Within this method, each of the balls calls a different method that does
  different things to the state of the game. For example, my Magenta Ball expands the size of the basket, my
  Orange Ball decreases the speed of the basket, and my Yellow Ball increases the size of the balls. In addition,
  the ability method returns a number which represents how much the ball is worth. I created an abstract class 
  for the balls because I wanted the colored balls to share general characteristics. However, I had to use 
  separate classes for each of the balls since they have unique functionality when they intersect with the basket.
 

  4.JUnit Testing (I originally has 2-D arrays in my proposal but I realized that I didn't have much use for
  it, which is why I changed my fourth concept to this) - I used JUnit to test if the ability method works for each of 
  the different balls when the ball intersects the basket. These tests involved making sure that the score is 
  changed by the correct number and that the state of the game is changed correctly. 
  For example, for my Yellow Ball test, I made sure that when the Yellow Ball intersected the basket, 
  it succesfully made the size of the future balls bigger.


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game.java: This class handles a lot of the basic graphics for my game. It initiates my GameCourt,
  creates a button for start/reset game, creates a button for the instructions (it also creates the JPanel 
  for the instructions), and the labels for the score and time left.
  
  GameCourt.java: This class handles most of the main functionality of the game. For example, it implements the method
  for reset and starting the game. It also keeps track of the timer and the score throughout the game. This class
  is also in charge of the movement of the basket/balls. In addition, this class handles adding new highscore 
  and displaying the high score leaderboard.
  
  GameObj.java: This abstract class describes an object in the game court. It keeps track of the object's position,
  size, and velocity. It also provides many convenient methods that I use in GameCourt.java such as the move() method,
  the intersects(GameObj ob) method (Used to check when ball hits basket/paddle), and the hitWall() method 
  (Used to check if ball is still in bounds).
  
  Direction.java: This enum is used in GameCourt.java to help navigation.
  
  Basket.java: A class that describes the basket object in the game. This class extends GameObj.java.
  Holds methods for setting and getting basket speed and size. Also implements the draw method.
  
  Ball.java: An abstract class that describes the ball object in the game. This class extends GameObj.java.
  Contains an abstract method ability(Basket b) which has a unique functionality in each of Ball's subclasses. 
  
  BlueBall.java: A class describing a blue ball in the game, extending Ball.java. The ability method in this class
  decreases the size of the balls and returns a score of -5.

  RedBall.java: A class describing a red ball in the game, extending Ball.java. The ability method in this class
  increases the speed of the basket and returns a score of -5.
  
  GreenBall.java: A class describing a green ball in the game, extending Ball.java. The ability method in this class
  resets the size and speed of the basket and the size of the ball to default values and returns a score of 15.
  
  MagentaBall.java: A class describing a magenta ball in the game, extending Ball.java. The ability method in this class
  increases the size of the basket and returns a score of 10.
  
  YellowBall.java: A class describing a yellow ball in the game, extending Ball.java. The ability method in this class
  increases the size of the balls and returns a score of 20.

  OrangeBall.java: A class describing a orange ball in the game, extending Ball.java. The ability method in this class
  decreases the speed of the basket and returns a score of -20.
  
  GrayBall.java: A class describing a gray ball in the game, extending Ball.java. The ability method in this class
  decreases size of the basket and returns a score of -5.

  GameTest.java: Provides all the JUnit tests for my game. Primarily used to test the ability() methods for each
  of the balls.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  Yeah, at first, I did not consider what happened when there were multiple balls on the screen.
  However, I realized that my game had to have multiple balls on the screen so I needed a way to store all the balls
  on the screen to constantly move them and keep checking to see if the ball missed/intersected the basket. This
  problem was solved by using a Collection.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  I think my design makes sense because I have an abstract class for a Ball and a separate class for a Basket. 
  Both of these extend GameObject which makes sense since both of these are objects within the GameCourt. In addition,
  I think it makes sense that each of the types of balls are different classes extending Ball.java
  since they have very different implementations for the ability method. If I had the chance, maybe I would
  consider finding a better way to change the basket velocity, because currently, the basket class changes the
  velocity by changing the velocity field in the GameCourt class. However, I think overall my design makes a lot of
  sense.



