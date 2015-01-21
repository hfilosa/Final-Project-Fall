Final-Project
=============
Ultimate Chess

Henry Filosa & Alice Xue

A repository for the final project. Our project is chess in the terminal window. It can replicate all the features of chess such as castling, pawn promotion, en passants and certifying that moves do not put a player in check. Due to the complexity of identifying checkmate, we cannot verify if a checkmate is present in the game. Our game also has eight different colors for the users to play with (please note that colors are not available for Windows OS, so the game is not optimal for Windows users). 

12/22 - Started laying the groundwork of the project by creating class files for every type of piece. Our board will be represented by an array of these classes.

12/28 - Instead of a class for every piece we have condensed it to a single class called "piece". This holds a piece's name, its color and a method to display it in that color using ANSI escape code. This does not work on windows, so our program is a Linux & Mac only release. Started the board class wich will hold the representation of the chess board and methods for moving pieces.

12/29 - The Driver class of the program is now operation and allows the user to select their color. It also has a help menu. 

12/30 - Laid the basis for a turn system and a basic move authenticator. It checks that a player is selecting their own piece to move and is moving it to a location not occupied by their own piece. While incomplete, it will make checking the moves in more detail easier later. 

12/31 - Added a method that checks if a players piece is in check. The method find the players king and then searches in certain locations for certain pieces. For exaple it will search vertically starting from the king and if it finds an enemy rook that is not blocked by another piece the player is in check.

1/1 - Added movement methods for the pieces that are based off the code for the method that ascertains check. The move of a piece will be illegal if it is blocked by another piece of if it is not allowed to move in the way a user wants. For example, a rook cannot move diagonnaly. Added into the Driver turn function so players can now move their pieces around legally.

1/3 - Streamlined and combined some methods. This project has shown how important it is to strike a blance between dense methods with many inputs and a ton of superfluous methods. For ecample, the player turn methods have been condensed from two methods into one, shortening the Driver code by ~100 lines and making it much easier to edit.

1/4 - Added a movement method for the king and fixed a major bug with the check program and a few other methods. Moves are now executed and then if the player is in check as a result the move is undone. 

1/5 - Players can now castle their king. This method checks that the pieces have not moved and are not blocked. As a result every piece now has a boolean to track if they have moved yet. We have started work on a method to allow en passants.

1/6 - En passants are now in the game. When a pawn moves forward 2 spaces on its first move an invisible piece called "pas" is created behind it. The "pas" piece is removed at the start of the players next turn, but while it is there the enemy player may attack the "pas" piece and capture the pawn in front of it. We have also started a system to track pieces that a player has lost.

1/7 - We are wrestling with a bug on the capture tracker. The tracker seems to register moving to an empty space as capturing an enemy piece. 

1/8 - The solution to the capture tracker still eludes us.

1/9 - For unknown reasons the capture tracker now works. Now, when the board is printed, captured pieces are displayed to the right.

1/10 - Added a method wich checks if a players pawn has reached the opposite side of the board on his turn. The player is then given the option to promote it. The method was thoroughly tested and has no apparant issues. With the implementation of this method, our  game now has all the features of chess except ascertaining checkmate. However, determining checkmate is outside the scope of this project and requires a much greater investment of time and code. Future work will focus on improving the interface and the visual aspects of the board. 

1/12 - Copied a method from stack overflow to clear the screen. This improves the look of the program and makes it easier to understand.

1/13 - Added player names. This makes it easier to keep track of who's turn it is and makes the game more personal.

1/14 - Finished name implementation and improved victory screen.

1/15 - Fixed bugs with the castling methods and error messages when entering moves. Pawns captured by en Passant are now added to the loss display.

1/20 - Fixed bugs concerning capturing for rooks, queens, and bishops after we found them testing a game.
