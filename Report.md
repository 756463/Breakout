# What I Changed from Original AI-generated Code
I added many post-AI functions to my game, such as integrating my own login screen that saves your score and time along with an account system. All of this information is saved to a text file, which are used for future instances of the game running. I also made the score system contain an actual score rather than just completion time and created a combo multiplier to add variety. Finally, I added a screen that appears between logging in and playing the game that describes rules and controls.
# How my Program Meets the Requirements
### Collision Detection
My game has two moving objects, those being the paddle controlled by the player and the ball bouncing around. Additionally, when a brick collides with the ball, it is destroyed, and when the ball collides with the paddle, it is bounced back.
### Buttons and Mouse Events
There are many interactive elements in my game, such as the login screen many text fields and buttons, the rules page where you click to continue, and the paddle which is controlled by the player using the A and D keys.
### Abstract Classes and Inheritance
The GameObject class is an abstract class that extends 3 concrete subclasses, Ball, Brick, and Paddle. This helps all sprites in my game function smoothly with structured positions and sizes.
### GUI Design + Files I/O
My login screen uses colours that emulate old arcade games and is structured in an oderly fashion. The game itself is colourful as well, with each row of bricks having a unique colour. The login screen tracks users through a username and password system, which is used to save a player's score and time, saving the information to a text file.
