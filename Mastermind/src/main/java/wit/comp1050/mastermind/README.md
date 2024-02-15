# Wentworth Institute of Technology
## COMP1050, SP22 - Mastermind Game Project

### Description
Welcome to the world of international espionage and secret codes. *Mastermind* is a classic board game where a *CodeMaker* builds a secret code with colored pegs and a *CodeBreaker* has a number of guesses to break the
code, using feedback provide by the *CodeMaker*.

### Requirements
* The computer will be the CodeMaker
* A person will be the CodeBreaker
* You must implement this game with a JavaFX GUI
* You must use the Apache Commons configuration library to manage configuration

### History & How to Play
Instead of reinventing the wheel and explaining how to play, here are some good references that explain the game and its rules:

* [Mastermind - from Wikipedia](https://en.wikipedia.org/wiki/Mastermind_(board_game))
* [YouTube: How to Play Mastermind](https://www.google.com/search?q=mastermind+board+game+rules&oq=mastermind+board+game+rules&aqs=chrome..69i57.8596j0j1&sourceid=chrome&ie=UTF-8#kpvalbx=_nFx7XtnVG5SEytMPipGkgAY53)
* [How to Play Mastermind at WikiHow](https://www.wikihow.com/Play-Mastermind)

### Game Options
As part of this assignment, you are to provide various options for game play. The following sections describe the options.

#### Level
The game has 4 levels of play, described in the table below.

| Level         |   # Colors    |  Code Size | # Guesses |
|---------------|--------------:|-----------:|----------:|
| Novice        | 5             | 4          | 12        |
| Beginner (default)      | 6             | 4          | 10        |
| Intermediate  | 7             | 5          | 10        |
| Expert        | 8             | 6          | 8         |
<br/>

Enable the user to choose the level of play prior to starting each game. How you do this is up to you.

#### Duplicate Colors Option
* The CodeMaker can use duplicate colors (true/false) - default to *false*

#### Timed Game (Optional)
* The user can play an untimed or timed game
* The _maxTime_ value in the properties file (see below) determines if the game is untimed or timed
  * if _maxTime_ = 0, the game is untimed
  * if _maxTime_ > 0, the game is timed and the player has the value of _maxTime_ in minutes to break the computer's code

For timed games, display a countdown clock somewhere on the display.

#### Sounds
The user has the option of playing sounds for the following actions:
  * Game start
  * Code evaluation
  * Placing a peg
  * Winning the game
  * Losing the game
  * OPTIONAL: Background music playing during the game

Sounds are either all on or all off. You do not need to configure each sound separately.

#### mmind.properties 
You need to store the game options in a configuration file called *mmind.properties.* Use
the **Apache Commons Configuration 2.7 library (or higher)** to support your configuration needs. Note the use of the _.properties_ file. This is the de facto standard for Java applications.

Here is an example of the file's contents:
```
# Mastermind Properties File (mmind.properties)
enableDuplicateColorsInCode = false
playSounds = true
maxTime = 0 
```

### Creative License & Extra Credit
As long as you fulfill the requirements of the project, you are good to go. That said, if you find 
that you're completing the project quickly and want to challenge yourself to learn more, by all means 
experiment and add your own features. Some ideas to get you started:

* Implement a menu that enables the user to start a new game or exit the application
* Add a dialog box that enables the user to change the application's configuration settings
* Add a dialog or application argument where the application can accept the user's first name to make messaging more personal
* Add an About box that shows application info - author, etc. launched from a menu or button
* For timed games, implement an analog clock face vs. a digital display
* Enable saving and loading games
* Enable changing board and background colors
* Add computer-assisted guessing using an algorithm or machine-learning

### Advice
I am here to help with questions including your design, implemenation details, etc. This can be a challenging and
somewhat time-consuming project, so don't wait until the last minute to get started! Make incremental progress every
day.

Also, I suggest you get the core components of the application working, e.g., being able to create a code, score the
code against the solution, etc., before you start on the GUI. Trying to do both at the same time can make things
more confusing. 

As a general rule: _get the data structures and algorithms working before adding any GUI._ This helps
to ensure that when you add a GUI, you don't need to worry about the application's foundation. 

Have fun!

---END---