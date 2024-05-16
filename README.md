# Project Title
Mancala Game

## Description

In this project, I have expanded upon my previous text-based Mancala game by incorporating a Graphical User Interface (GUI) to enhance the user experience. The implementation of the GUI involves the use of various software development techniques, including the utilization of lambda notation for Action Listeners, different Layout Managers, and essential Swing classes such as JFrame, JPanel, JLabel, JOptionPane, JFileChooser, and JMenuBar.

# Object-Oriented Principles and Refactoring
To maintain code clarity and adhere to Object-Oriented Programming (OOP) principles, I have refactored the existing solution. The refactoring process involved removing duplicate code and ensuring that each method performs a single responsibility. To guide this refactoring, I created a responsibility matrix for core methods in classes such as Board, MancalaGame, and TextUI. The matrix helped identify methods that needed refactoring, leading to improved encapsulation and code readability.

# Persistence
I implemented the ability to save and load the game state and user profiles using serialization. The Player class was modified to include a UserProfile member variable, capturing details such as the user's name, the number of Kalah and Ayo games played, and the number of wins for each game. A new class, Saver, was created with methods to save and load objects, providing a mechanism for persisting game states and user profiles. All files are saved in an "assets" subfolder of the main project directory.

# Second Rule Set
The project now supports two rule sets for the Mancala game: Kalah and Ayo. To achieve this, I refactored the Board class to separate the data structure and game rules. Two subclasses, KalahBoard and AyoBoard, were created to represent the game boards for each rule set. Additionally, abstract classes like GameRules, MancalaDataStructure, and the Countable interface were utilized to encapsulate game rules and ensure clean separation of concerns. Javadoc comments and exceptions were employed for effective documentation and error handling.

# Graphical User Interface (GUI) Development
The most significant enhancement in this project is the introduction of a GUI to facilitate an interactive gaming experience. The GUI allows users to play either Kalah or Ayo, offering features such as:

# Quitting one game and starting another without exiting the application.
Saving and loading in-progress games and user profiles.
Keeping a record of each user's game statistics, including wins, losses, and games played.
Clicking on pits to take turns, with the game preventing incorrect inputs without crashing.
Providing options to play again or return to the main screen at the end of a game.
Notifying players when the game is over and indicating the winner or a tie.
The GUI development adheres to best practices, including the use of lambda notation for Action Listeners, multiple Layout Managers, and separation of the user interface from the game logic. The implementation leverages essential Swing classes and excludes the use of software GUI builders, ensuring a clean and well-structured codebase. The PositionAwareButton class is included for handling button positions in a JPanel grid.

Overall, this project represents a comprehensive and well-structured implementation of a Mancala game with a GUI, showcasing proficiency in OOP principles, code refactoring, persistence, and GUI development.

## Getting Started


### Dependencies

* Prerequisites for this program:
    gradle should be installed on the system.


### Executing program

* Open a command line terminal

* to build the program:
```
gradle build

```

* to run the program:
```
java -jar build/libs/GameUI.jar

```


## Limitations

There are no limitations or errors in the program.

## Author Information

* Name: Megan D Costa
* email address: mdcosta@uoguelph.ca

## Development History

* 01/12
      Completed A4

* 30/12
      finished A4

* 29/11
      added README



## Acknowledgments

* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)


